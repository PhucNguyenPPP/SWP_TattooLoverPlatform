/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ServiceDAO;
import DAO.StudioDAO;
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
@WebServlet(name = "priceListAddController", urlPatterns = {"/priceListAddController"})
public class priceListAddController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "StudioManagerInfoController";
    private static final String SUCCESS = "StudioManagerInfoController";

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
            List<ServiceSizeDTO> sizeList = (List<ServiceSizeDTO>) session.getAttribute("SIZE_LIST");
            String serviceID = (String) session.getAttribute("serviceID");
            String serviceDetailID = (String) session.getAttribute("serviceDetailID");
            for (ServiceSizeDTO size : sizeList) {
                String studioServiceID = ServiceDAO.generateStudioServiceID();
                int price = Integer.parseInt(request.getParameter(size.getServiceSizeID()));
                serviceDao.addPriceList(user.getId(), studioID, serviceID, serviceDetailID, size.getServiceSizeID(), price, studioServiceID);
            }
//                INSERT INTO Studio_Service(studioStaffID,studioID,serviceID,serviceDetailID,serviceSizeID,price,status) VALUE(,)

        } catch (Exception e) {
            log("Error ar PriceListAddController: " + e.toString());
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
