/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BookingDAO;
import DAO.StudioDAO;
import DTO.BookingDTO;
import DTO.ImgDTO;
import DTO.RatingDTO;
import DTO.ServiceSizeDTO;
import DTO.SlotDTO;
import DTO.StudioDTO;
import DTO.StudioServiceDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "BookServiceController", urlPatterns = {"/BookServiceController"})
public class BookServiceController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "studioInformation.jsp";
    private static final String SUCCESS = "studioBookingForm.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String studioID = request.getParameter("studioID");
            StudioDTO studio = new StudioDTO();
            BookingDAO bookDao = new BookingDAO();
            StudioDAO stuDao = new StudioDAO();
            studio = stuDao.getStuInfor(studioID);
            if (studio != null) {
                request.setAttribute("STUDIO", studio);

                ImgDTO imgAvatar = stuDao.getAvatarStu(studioID);
                request.setAttribute("IMG_AVATAR", imgAvatar);

                List<ImgDTO> serviceImageList = stuDao.getServiceImageList(studioID);
                request.setAttribute("IMG_SERVICE_LIST", serviceImageList);

                RatingDTO rating = stuDao.getRatingStudio(studioID);
                request.setAttribute("RATING", rating);

                List<StudioServiceDTO> service_list = stuDao.getService(studioID);
                request.setAttribute("SERVICE_LIST", service_list);

                List<StudioServiceDTO> service_detail_normal_list = stuDao.getServiceDetailNormal(studioID);
                request.setAttribute("SERVICE_DETAIL_NORMAL_LIST", service_detail_normal_list);

                List<StudioServiceDTO> service_detail_color_list = stuDao.getServiceDetailColor(studioID);
                request.setAttribute("SERVICE_DETAIL_COLOR_LIST", service_detail_color_list);

                List<ServiceSizeDTO> service_size = stuDao.getServiceSize();
                request.setAttribute("SERVICE_SIZE_LIST", service_size);
                
                List<BookingDTO> cmtFeedbackList = bookDao.getCmtFeebackStudio(studioID);
                request.setAttribute("CMT_FEEDBACK_LIST", cmtFeedbackList);


                String serviceID[] = request.getParameterValues("serviceID");
                String serviceDetailID[] = request.getParameterValues("serviceDetailID");
                String serviceSizeID[] = request.getParameterValues("serviceSizeID");
                String amount[] = request.getParameterValues("amount");

                List<StudioServiceDTO> listServiceBooking = new ArrayList<>();

                boolean checkBookingService = true;
                for (int i = 0; i < 4; i++) {
                    if (!serviceID[i].equals("") && !serviceDetailID[i].equals("") && !serviceSizeID[i].equals("") && (!amount[i].equals("") || !amount[i].equals("0"))) {
                        StudioServiceDTO temp = bookDao.getServiceBooking(studioID, serviceID[i], serviceDetailID[i], serviceSizeID[i], amount[i]);
                        listServiceBooking.add(temp);
                    }
                }

                if (listServiceBooking.size() > 0) {
                    session.setAttribute("LIST_SERVICE_BOOKING", listServiceBooking);
                    request.setAttribute("IMG_AVATAR", imgAvatar);
                    request.setAttribute("IMG_SERVICE_LIST", serviceImageList);
                    request.setAttribute("STUDIO", studio);
                    List<SlotDTO> slotList = stuDao.getSlotListStudio(studioID);
                    request.setAttribute("SLOT_LIST", slotList);
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE_ERROR", "Bạn chưa nhập đủ thông tin cần thiết");
                    url = ERROR;
                }

            }

        } catch (Exception e) {
            log("Error at BookServiceController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
