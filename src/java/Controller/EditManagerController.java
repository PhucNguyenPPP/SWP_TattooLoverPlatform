/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.StudioDAO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
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
@WebServlet(name = "editManagerController", urlPatterns = {"/editManagerController"})
public class EditManagerController extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        request.setCharacterEncoding("utf-8");
        try {
            HttpSession session = request.getSession();
            UserDTO user=(UserDTO)session.getAttribute("User");
            String studioID=user.getStudioId();
            String phoneNum = request.getParameter("phoneNumber");
            int time1slot = Integer.parseInt(request.getParameter("time1slot"));
            String timeStart = request.getParameter("timeStart");
            String description = (String)request.getParameter("description");
            String email = request.getParameter("Email");
            String timeEnd = request.getParameter("timeEnd");
            StudioDAO stu = new StudioDAO();
            boolean UpdateStu=stu.UpdateStudio(studioID, phoneNum, timeStart, timeEnd,email, time1slot, description);
        } catch (Exception e) {
            log("Error ar EditManagerController: " + e.toString());
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
