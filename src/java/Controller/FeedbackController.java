/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BookingDAO;
import DAO.NotificationDAO;
import DAO.StudioDAO;
import DTO.BookingDTO;
import DTO.BookingDetailDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "FeedbackController", urlPatterns = {"/FeedbackController"})
public class FeedbackController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "BookingDetailController";
    private static final String SUCCESS = "BookingDetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            String bookingID = request.getParameter("bookingID");
            BookingDAO bookDao = new BookingDAO();
            StudioDAO stuDao = new StudioDAO();
            String rate = request.getParameter("rate");
            String comment = request.getParameter("comment");
            String studioID = request.getParameter("studioID");
            String managerID = stuDao.getManagerIDByStudioID(studioID);

            if (rate != null) {
                if (comment != null) {
                    boolean checkFeedback = bookDao.insertFeedback(rate, comment, bookingID);
                    if (checkFeedback == true) {
                        request.setAttribute("MESSAGE_PROCESS", "Đánh giá thành công");
                        /*Tạo đánh giá*/
                        NotificationDAO noticeDao = new NotificationDAO();
                        LocalDateTime today = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        String currentDate = formatter.format(today);
                        String noticeDescriptionStu = "Lịch hẹn " + bookingID + " đã được đánh giá " + rate + " sao.";
                        boolean checkInsertNoticeStu = noticeDao.insertNoticeAfterCreateBooking(managerID, currentDate, noticeDescriptionStu);
                        if (checkInsertNoticeStu) {
                            url = SUCCESS;
                        }
                    }
                } else {
                    request.setAttribute("ERROR_COMMENT", "* Vui lòng nhập đánh giá!");
                    url = ERROR;
                }
            } else {
                request.setAttribute("ERROR_RATING", "* Vui lòng chọn số sao!");
                url = ERROR;
            }

        } catch (Exception e) {
            log("Error at FeedbackController: " + e.toString());
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
