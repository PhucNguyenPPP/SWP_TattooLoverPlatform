/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.NotificationDAO;
import DAO.StudioDAO;
import DAO.UserDAO;
import DTO.StudioDTO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "DeleteBookingStuController", urlPatterns = {"/DeleteBookingStuController"})
public class DeleteBookingStuController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "BookListStuController";
    private static final String SUCCESS = "BookListStuController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            String bookingID = request.getParameter("bookingID");
            HttpSession session = request.getSession();
            String studioID = request.getParameter("studioID");
            StudioDAO stuDao = new StudioDAO();
            StudioDTO studio = new StudioDTO();
            studio = stuDao.getStuInfor(studioID);
            UserDAO dao = new UserDAO();
            UserDTO us = (UserDTO) session.getAttribute("User");
            String userID = us.getId();
            String tattooLoverID = request.getParameter("tattooLoverID");
            boolean checkDelete = dao.deleteBookingTL(bookingID);
            if (checkDelete) {
                LocalDateTime today = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                String currentDate = formatter.format(today);
                NotificationDAO noticeDao = new NotificationDAO();
                String noticeDescriptionTL = "Lịch hẹn " + bookingID + " đã bị hủy bởi " + studio.getName();
                String noticeDescriptionStu = "Lịch hẹn " + bookingID + " đã bị hủy";
                boolean checkInsertDeleteNoticeTL = noticeDao.insertAfterDelete(tattooLoverID, noticeDescriptionTL, currentDate);
                boolean checkInsertDeleteNoticeStu = noticeDao.insertAfterDelete(userID, noticeDescriptionStu, currentDate);
                if ( checkInsertDeleteNoticeStu == true && checkInsertDeleteNoticeTL) {
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at DeleteBookingStuController: " + e.toString());
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
