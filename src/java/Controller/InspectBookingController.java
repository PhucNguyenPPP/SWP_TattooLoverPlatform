/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BookingDAO;
import DAO.NotificationDAO;
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
@WebServlet(name = "InspectBookingController", urlPatterns = {"/InspectBookingController"})
public class InspectBookingController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "BookingDetailManagerController";
    private static final String SUCCESS = "BookListStuController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO us = (UserDTO) session.getAttribute("User");
            String userID = us.getId();
            String bookingStudioServiceID[] = request.getParameterValues("bookingStudioServiceID");
            String bookingID = request.getParameter("bookingID");
            String artistID[] = request.getParameterValues("artistID");
            String tattooLoverId = request.getParameter("tattooLoverID");
            String studioName = request.getParameter("studioName");
            boolean checkArtistDuplicated = false;

            for (int i = 0; i < artistID.length; i++) {
                for (int j = i + 1; j < artistID.length; j++) {
                    if (artistID[i].equals(artistID[j])) {
                        checkArtistDuplicated = true;
                    }
                }
            }

            if (checkArtistDuplicated == false) {
                BookingDAO bookDao = new BookingDAO();
                boolean checkAssign = false;
                for (int i = 0; i < bookingStudioServiceID.length; i++) {
                    checkAssign = bookDao.assignArtist(bookingStudioServiceID[i], artistID[i]);
                }

                if (checkAssign == true) {
                    boolean checkUpdateBooking = bookDao.setInspectedBooking(bookingID, userID);
                    if (checkUpdateBooking == true) {
                        /*Tạo thông báo*/
                        NotificationDAO noticeDao = new NotificationDAO();
                        LocalDateTime today = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        String currentDate = formatter.format(today);
                        String noticeDescriptionTL = "Lịch hẹn " + bookingID + " tại " + studioName + " đã được duyệt.";
                        boolean checkInsertNoticeTL = noticeDao.insertNoticeAfterCreateBooking(tattooLoverId, currentDate, noticeDescriptionTL);
                        if (checkInsertNoticeTL) {
                            url = SUCCESS;
                        }
                    }
                }
            } else {
                request.setAttribute("ERROR", "Chỉ chọn một thợ xăm cho một dịch vụ");
                url = ERROR;
            }
        } catch (Exception e) {
            log("Error at InspectBookingController: " + e.toString());
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
