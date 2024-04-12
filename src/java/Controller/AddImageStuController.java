/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ImgDAO;
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
import javax.servlet.http.Part;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AddImageStuController", urlPatterns = {"/AddImageStuController"})
@MultipartConfig
public class AddImageStuController extends HttpServlet {

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
            Part fileImg = request.getPart("imageStudio");
            InputStream fileContent = fileImg.getInputStream();
            byte[] data = new byte[(int) fileImg.getSize()];
            fileContent.read(data);
            String base64Data = Base64.getEncoder().encodeToString(data);
            ImgDAO dao = new ImgDAO();
            String studioID = request.getParameter("studioID");
            String imageName = "Hinh service studio " + studioID ;
            String imageRole = "SV";
            String userID = null;
            String serivceID = null;
            String serivceDetailID = null;
            boolean status = true;
            dao.addImage(imageName, base64Data, imageRole, studioID, userID, serivceID, serivceDetailID, status);
            url = SUCCESS;
            request.setAttribute("MSG", "Thêm ảnh thành công!");

        } catch (Exception e) {
            log("Error at AddImageStuController: " + e.toString());
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
