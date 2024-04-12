/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
@WebServlet(name = "SendOTPController", urlPatterns = {"/SendOTPController"})
public class SendOTPController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static String ERROR = "ForgetPassword.jsp";
    private static String SUCCESS = "ForgetPassword.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String emailPhuc = "nguyenphucdt36@gmail.com";
            String emailReceived = request.getParameter("email");
            String password = "gijm ddir olzh udqd";
            HttpSession mySession = request.getSession();
            UserDAO userDao = new UserDAO();
            boolean checkEmail = userDao.emailIsUnique(emailReceived);
            if (!checkEmail) {
                int otpValue = 0;
                Random rd = new Random();
                otpValue = rd.nextInt(999999);

                // Get the session object
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.socketFactory.port", "587");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.starttls.enable", "true");

                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailPhuc, password);// Put your email
                    }
                });

                // compose message
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(emailReceived));// change accordingly
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceived));
                message.setSubject("Xin chào ");
                message.setText("Mã OTP: " + otpValue);
                // send message
                Transport.send(message);

                request.setAttribute("EMAIL_MSG", "OTP đã được gửi đến email của bạn!");

                mySession.setAttribute("OTP", otpValue);
                mySession.setAttribute("EMAIL", emailReceived);
                url = SUCCESS;

            } else {
                request.setAttribute("EMAIL_MSG", "* Email không tồn tại!");
                url = ERROR;
            }
        } catch (Exception e) {
            log("ERROR at SendOTPController: " + e.toString());
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
