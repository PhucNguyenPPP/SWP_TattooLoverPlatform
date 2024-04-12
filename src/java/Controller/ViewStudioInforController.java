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
import DTO.StudioDTO;
import DTO.StudioServiceDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ViewStudioInforController", urlPatterns = {"/ViewStudioInforController"})
public class ViewStudioInforController extends HttpServlet {

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
    private static final String SUCCESS = "studioInformation.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            String studioID = request.getParameter("studioID");
            StudioDTO studio = new StudioDTO();
            StudioDAO stuDao = new StudioDAO();
            BookingDAO bookDao = new BookingDAO();
            studio = stuDao.getStuInfor(studioID);
            if (studio != null) {
                request.setAttribute("STUDIO", studio);
                RatingDTO rating = stuDao.getRatingStudio(studioID);
                request.setAttribute("RATING", rating);
                
                ImgDTO imgAvatar = stuDao.getAvatarStu(studioID);
                request.setAttribute("IMG_AVATAR", imgAvatar);
                
                List<ImgDTO> serviceImageList = stuDao.getServiceImageList(studioID);
                request.setAttribute("IMG_SERVICE_LIST", serviceImageList);
                
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
                
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error ar ViewStudioInforController: " + e.toString());
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
