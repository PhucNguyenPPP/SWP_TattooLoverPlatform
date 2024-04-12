/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ImgDAO;
import DAO.ServiceDAO;
import DTO.ServiceDTO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author hieu09097248
 */
@WebServlet(name = "CreateServiceController", urlPatterns = {"/CreateServiceController"})
@MultipartConfig
public class CreateServiceController extends HttpServlet {

    private static final String ERROR = "CreateService.jsp";
    private static final String SUCCESS = "ServiceListController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO us = (UserDTO) session.getAttribute("User");
            String staffID = us.getId();
            String nameService = request.getParameter("name");
            Part avatar = request.getPart("avatar");
            ServiceDAO dao = new ServiceDAO();
            boolean checkNameDuplicated = dao.checkServiceNameDuplicated(nameService);

            if (checkNameDuplicated == false) {
                boolean checkInsert = dao.createService(staffID, nameService);
                String serviceID = dao.getServiceID();
                InputStream fileContent = avatar.getInputStream();
                byte[] data = new byte[(int) avatar.getSize()];
                fileContent.read(data);
                String base64Data = Base64.getEncoder().encodeToString(data);
                ImgDAO imgDao = new ImgDAO();
                String imageName = "Avatar " + nameService;
                String imageRole = "AVA";
                String imageStudioID = null;
                String imageUserID = null;
                String imageSerivceID = serviceID;
                String imageSerivceDetailID = null;
                boolean status = true;
                imgDao.addImage(imageName, base64Data, imageRole, imageStudioID, imageUserID, imageSerivceID, imageSerivceDetailID, status);
                if (checkInsert) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("ERROR", "*Tên dịch vụ đã tồn tại!");
            }
        } catch (Exception e) {
            log("Error at BookingListServlet: " + e.toString());
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
