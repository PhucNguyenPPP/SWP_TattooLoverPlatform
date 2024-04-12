/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
@WebServlet(name = "EditUserProfileController", urlPatterns = {"/EditUserProfileController"})
public class EditUserProfileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static String ERROR = "home.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = ERROR;
            HttpSession session = request.getSession();
            UserDTO us = (UserDTO) session.getAttribute("User");
            String userName = request.getParameter("username");
            String birthday = request.getParameter("birthday");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            String fullname = request.getParameter("fullname");
            String city = request.getParameter("city");
            String district = request.getParameter("district");
            String phoneNumber = request.getParameter("phoneNumber");
            
            us.setBirthday(birthday);
            us.setEmail(email);
            us.setCity(city);
            us.setGender(gender);
            us.setDistrict(district);
            us.setFullname(fullname);
            us.setPhoneNumber(phoneNumber);
            String msg;
            boolean check = UserDAO.updateUserInfo(us);
            if(check){
                msg = "Chỉnh sửa thành công";
                session.setAttribute("User", us);
                url = "MainController?action=userprofile";
            } else {
                msg = "Error at EditUserProfileController";
                url = "home.jsp";
            }
            request.setAttribute("msg", msg);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception e){
            log("Error at EditUserProfileController" + e.toString());
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
