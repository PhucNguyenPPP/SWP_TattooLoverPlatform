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

/**
 *
 * @author laptop
 */
@WebServlet(name = "priceListController", urlPatterns = {"/priceListController"})
public class priceListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "priceList.jsp";
    private static final String SUCCESS = "priceList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("User");
            String studioID = user.getStudioId();
            StudioDTO studio = new StudioDTO();
            StudioDAO stuDao = new StudioDAO();
            ServiceDAO serviceDao = new ServiceDAO();
            studio = stuDao.getStuInfor(studioID);
            String serviceID = request.getParameter("serviceID");
            session.setAttribute("serviceID", serviceID);
            String serviceDetailID = request.getParameter("itemID");
            session.setAttribute("serviceDetailID", serviceDetailID);
            String serviceName = serviceDao.getServiceNameByID(serviceID);
            request.setAttribute("serviceName", serviceName);
            String serviceDetailName = serviceDao.getServiceDetailNameByID(serviceDetailID);
            request.setAttribute("serviceDetailName", serviceDetailName);
            if (studio != null) {
                List<ServiceSizeDTO> sizeList = new ArrayList<>();
                sizeList = serviceDao.getSizeList();
                session.setAttribute("SIZE_LIST", sizeList);

                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error ar PriceListController: " + e.toString());
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
