/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ServiceDAO;
import DTO.ServiceDTO;
import DTO.ServiceDetailDTO;
import DTO.ServiceSizeDTO;
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
 * @author hieu09097248
 */
@WebServlet(name = "SearchServiceController", urlPatterns = {"/SearchServiceController"})

public class SearchServiceController extends HttpServlet {

    private static final String ERROR = "AdminServicePage.jsp";
    private static final String SUCCESS = "AdminServicePage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            String search = request.getParameter("searchService");
            ServiceDAO dao = new ServiceDAO();
            List<ServiceDTO> listService = dao.searchService(search);

            List<ServiceDetailDTO> serviceDetialList = dao.getListServiceDetail();
            List<ServiceSizeDTO> serviceSizeList = dao.getListServiceSize();
            request.setAttribute("SERVICE_LIST_DETAIL", serviceDetialList);
            request.setAttribute("SERVICE_SIZE", serviceSizeList);
            if (listService.size() > 0) {
                request.setAttribute("SERVICE_LIST", listService);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("error at SearchServiceController: " + e.toString());
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
