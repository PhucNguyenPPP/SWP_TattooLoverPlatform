/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BookingDAO;
import DAO.ServiceDAO;
import DAO.StudioDAO;
import DTO.BookingDTO;
import DTO.ImgDTO;
import DTO.RatingDTO;
import DTO.ServiceDTO;
import DTO.ServiceDetailDTO;
import DTO.ServiceSizeDTO;
import DTO.StudioDTO;
import DTO.StudioServiceDTO;
import DTO.UserDTO;
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
import javax.websocket.Session;

/**
 *
 * @author laptop
 */
@WebServlet(name = "StudioManagerInfoController", urlPatterns = {"/StudioManagerInfoController"})
public class StudioManagerInfoController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "studioInfoManager.jsp";
    private static final String SUCCESS = "studioInfoManager.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO)session.getAttribute("User");
            String studioID = user.getStudioId();
            StudioDTO studio = new StudioDTO();
            StudioDAO stuDao = new StudioDAO();
            BookingDAO bookDao = new BookingDAO();
            ServiceDAO serviceDao = new ServiceDAO();
            studio = stuDao.getStuInfor(studioID);
            if (studio != null) {
                session.setAttribute("STUDIO", studio);
                RatingDTO rating = stuDao.getRatingStudio(studioID);
                session.setAttribute("RATING", rating);
                
                ImgDTO imgAvatar;
                imgAvatar = stuDao.getAvatarStu(studioID);
                session.setAttribute("IMG_AVATAR", imgAvatar);
                
                List<ImgDTO> serviceImageList = stuDao.getServiceImageList(studioID);
                session.setAttribute("IMG_SERVICE_LIST", serviceImageList);
                
                List<StudioServiceDTO> service_list = stuDao.getService(studioID);
                session.setAttribute("SERVICE_LIST", service_list);
                
                List<StudioServiceDTO> service_detail_normal_list = stuDao.getServiceDetailNormal(studioID);
                session.setAttribute("SERVICE_DETAIL_NORMAL_LIST", service_detail_normal_list);
                session.setAttribute("SERVICE_DETAIL_NORMAL_LIST_SIZE", service_detail_normal_list.size());
                
                List<StudioServiceDTO> service_detail_color_list = stuDao.getServiceDetailColor(studioID);
                session.setAttribute("SERVICE_DETAIL_COLOR_LIST", service_detail_color_list);
                
                List<ServiceSizeDTO> service_size = stuDao.getServiceSize();
                session.setAttribute("SERVICE_SIZE_LIST", service_size);
                
                List<BookingDTO> cmtFeedbackList = bookDao.getCmtFeebackStudio(studioID);
                session.setAttribute("CMT_FEEDBACK_LIST", cmtFeedbackList);
                
                List<ServiceDetailDTO> serviceDeList = serviceDao.getServiceDetail();
                int serviceDeListSize = serviceDeList.size();
                session.setAttribute("SER_DE_LIST", serviceDeList);
                session.setAttribute("SER_DE_LIST_SIZE", serviceDeListSize); 
                
                List<ServiceDetailDTO> notNormalServiceDeList = new ArrayList<>();
                for (ServiceDetailDTO serviceDetailDTO : serviceDeList) {
                    boolean isNot = true;
                    for (StudioServiceDTO serviceDetailDTO1 : service_detail_normal_list) {
                        if(serviceDetailDTO.getServiceDetailID().equals(serviceDetailDTO1.getServiceDetailID())){
                            isNot=false;
                            break;
                        }
                    }
                    if(isNot){
                        notNormalServiceDeList.add(serviceDetailDTO);
                    }
                }
                List<ServiceDetailDTO> notColorServiceDeList = new ArrayList<>();
                for (ServiceDetailDTO serviceDetailDTO : serviceDeList) {
                    boolean isNot = true;
                    for (StudioServiceDTO serviceDetailDTO1 : service_detail_color_list) {
                        if(serviceDetailDTO.getServiceDetailID().equals(serviceDetailDTO1.getServiceDetailID())){
                            isNot=false;
                            break;
                        }
                    }
                    if(isNot){
                        notColorServiceDeList.add(serviceDetailDTO);
                    }
                }
                session.setAttribute("NOT_SER_DE_NORMAL_LIST", notNormalServiceDeList);
                session.setAttribute("NOT_SER_DE_COLOR_LIST", notColorServiceDeList);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error ar StudioManagerInfoController: " + e.toString());
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
