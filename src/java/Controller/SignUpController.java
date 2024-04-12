/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author ASUS
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/SignUpController"})
public class SignUpController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "signup.jsp";
    private static final String SUCCESS = "HomeController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            String userAccount = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmedPassword = request.getParameter("confirmedpassword");
            String fullname = request.getParameter("fullName");
            String birthday = request.getParameter("birthDate");
            String gender = request.getParameter("gender");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String districtId = request.getParameter("district");
            String cityId = request.getParameter("city");
            String roleId = request.getParameter("roleid");
            String msg = "";

            UserDTO us = UserDAO.getUserByUserAccount(userAccount);

            HttpSession session = request.getSession();
            UserDTO currUser = (UserDTO) session.getAttribute("User");

            boolean checkValid = true;

            if (us != null) {
                request.setAttribute("ERROR_USERNAME", "* Tên tài khoản đã tồn tại!");
                url = ERROR;
                checkValid = false;
            }

            if (!password.equals(confirmedPassword)) {
                request.setAttribute("ERROR_PASSWORD", "* Mật khẩu không giống nhau!");
                url = ERROR;
                checkValid = false;
            }

            if (UserDAO.emailIsUnique(email) == false) {
                request.setAttribute("ERROR_EMAIL", "* Email đã tồn tại!");
                url = ERROR;
                checkValid = false;
            }

            if (UserDAO.phoneIsUnique(phoneNumber) == false) {
                request.setAttribute("ERROR_PHONE", "* Số điện thoại đã tồn tại!");
                url = ERROR;
                checkValid = false;
            }

            if (checkValid) {
                //create account
                String userId; //create id
                do {
                    userId = UserDAO.getRandomUserId(roleId);
                } while (UserDAO.getUserByUserId(userId) != null);

                if (currUser != null) {
                    if (currUser.getStudioId() != null) {
                        UserDAO.createUser(userId, userAccount, password, roleId, fullname, birthday, gender, phoneNumber, email, cityId, districtId, true, currUser.getStudioId());
                    } else {
                        UserDAO.createUser(userId, userAccount, password, roleId, fullname, birthday, gender, phoneNumber, email, cityId, districtId, true, null);
                    }
                } else {
                    us = UserDAO.createUser(userId, userAccount, password, roleId, fullname, birthday, gender, phoneNumber, email, cityId, districtId, true, null);
                    session.setAttribute("User", us);
                }
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at SignUpController: " + e.toString());
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
