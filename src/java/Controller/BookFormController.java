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
import DTO.ImgDTO;
import DTO.RatingDTO;
import DTO.SlotDTO;
import DTO.StudioDTO;
import DTO.StudioServiceDTO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "BookFormController", urlPatterns = {"/BookFormController"})
public class BookFormController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "studioBookingForm.jsp";
    private static final String SUCCESS = "studioBookingForm.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String studioID = request.getParameter("studioID");
            StudioDTO studio = new StudioDTO();
            StudioDAO stuDao = new StudioDAO();
            BookingDAO bookDao = new BookingDAO();
            studio = stuDao.getStuInfor(studioID);
            if (studio != null) {
                request.setAttribute("STUDIO", studio);

                ImgDTO imgAvatar = stuDao.getAvatarStu(studioID);
                request.setAttribute("IMG_AVATAR", imgAvatar);

                List<ImgDTO> serviceImageList = stuDao.getServiceImageList(studioID);
                request.setAttribute("IMG_SERVICE_LIST", serviceImageList);

                RatingDTO rating = stuDao.getRatingStudio(studioID);
                request.setAttribute("RATING", rating);

                List<SlotDTO> slotList = stuDao.getSlotListStudio(studioID);
                request.setAttribute("SLOT_LIST", slotList);

                UserDTO us = (UserDTO) session.getAttribute("User");

                if (us != null && us.getRoleId().equals("TL")) {
                    List<StudioServiceDTO> listServiceBooking = (List<StudioServiceDTO>) session.getAttribute("LIST_SERVICE_BOOKING");
                    if (listServiceBooking.isEmpty() == false) {
                        String userID = us.getId();
                        String fullName = request.getParameter("fullName");
                        String phoneNumber = request.getParameter("phoneNumber");
                        String description = request.getParameter("description");
                        LocalDateTime today = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        String currentDate = formatter.format(today);
                        String bookDate = request.getParameter("bookDate");
                        String slotID = request.getParameter("slot");
                        int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));

                        boolean checkValid = true;
                        if (phoneNumber.matches("[0]{1}[0-9]{9}") == false) {
                            request.setAttribute("MESSAGE_ERROR_PHONE", "* Số điện thoại không hợp lệ!");
                            checkValid = false;
                        }

                        int amountBookingService = bookDao.getAmountBookingServiceInSlot(studioID, slotID, bookDate);
                        int amountArtist = stuDao.checkAmountArtistStudio(studioID);
                        if ((amountArtist-amountBookingService) < listServiceBooking.size()) {
                            request.setAttribute("MESSAGE_ERROR_SLOT", "* Khung thời gian bạn chọn đã hết chỗ!");
                            checkValid = false;
                        }

                        if (checkValid == true) {
                            boolean checkInsertBooking = bookDao.insertBooking(userID, fullName, phoneNumber, studioID, slotID, totalPrice, description, currentDate, bookDate);
                            if (checkInsertBooking == true) {
                                String bookingID = bookDao.getBookingID();
                                for (StudioServiceDTO o : listServiceBooking) {
                                    boolean checkInsertBookingService = bookDao.insertBookingService(bookingID, o.getStudioServiceID(), o.getAmount());
                                    if (checkInsertBookingService == true) {
                                        request.setAttribute("MESSAGE_PROCESS", "Đặt lịch thành công");
                                        session.removeAttribute("LIST_SERVICE_BOOKING");
                                        url = SUCCESS;
                                    } else {
                                        request.setAttribute("MESSAGE_PROCESS", "Đặt lịch thất bại");
                                        url = ERROR;
                                        break;
                                    }
                                }
                                /*Xóa danh sách dịch vụ sau khi đã add vào database*/
                                listServiceBooking.clear();
                                session.removeAttribute("LIST_SERVICE_BOOKING");
                                /*Tạo thông báo*/
                                NotificationDAO noticeDao = new NotificationDAO();
                                String noticeDescriptionTL = "Lịch hẹn " + bookingID + " tại " + studio.getName() + " đang được xử lý.";
                                String noticeDescriptionStu = us.getFullname() + " đã đặt lịch hẹn " + bookingID;
                                boolean checkInsertNoticeTL = noticeDao.insertNoticeAfterCreateBooking(userID, currentDate, noticeDescriptionTL);
                                boolean checkInsertNoticeStu = noticeDao.insertNoticeAfterCreateBooking(studio.getMangerID(), currentDate, noticeDescriptionStu);
                                if (checkInsertNoticeTL == true && checkInsertNoticeStu) {
                                    url = SUCCESS;
                                }
                            }
                        } else {
                            request.setAttribute("MESSAGE_PROCESS", "Đặt lịch thất bại!");
                            url = ERROR;
                        }
                    } else {
                        request.setAttribute("MESSAGE_PROCESS", "Bạn chưa chọn bất kì dịch vụ nào!");
                        url = ERROR;
                    }

                } else {
                    request.setAttribute("MESSAGE_PROCESS", "Vui lòng đăng nhập để có thể đặt lịch hẹn!");
                    url = ERROR;
                }
            }

        } catch (Exception e) {
            log("Error at BookFormController: " + e.toString());
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
