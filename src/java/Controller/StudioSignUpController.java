/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ImgDAO;
import DAO.SlotDAO;
import DAO.StudioDAO;
import DAO.UserDAO;
import DTO.StudioDTO;
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
 * @author ACER
 */
@WebServlet(name = "StudioSignUpController", urlPatterns = {"/StudioSignUpController"})
@MultipartConfig
public class StudioSignUpController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String USER_NAME_ERROR = "usernameError";
    private static final String CONFIRM_PASSWORD_ERROR = "confirmPasswordError";
    private static final String PHONE_NUMBER_ERROR = "phoneNumberError";
    private static final String EMAIL_ERROR = "emailError";
    private static final String TIME_ERROR = "timeError";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = "studiosignup.jsp";
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmedpassword = request.getParameter("confirmedpassword");
            String fullname = request.getParameter("fullname");
            String studioName = request.getParameter("studioName");
            String phoneNumber = request.getParameter("phone");
            String email = request.getParameter("email");
<<<<<<< HEAD
            String birthday = request.getParameter("birthDate");
            String gender = request.getParameter("gender");
=======
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
            String city = request.getParameter("city");
            String district = request.getParameter("district");
            String address = request.getParameter("address");
            String hourStart = request.getParameter("hourstart");
            String hourEnd = request.getParameter("hourend");
            String minuteStart = request.getParameter("minutestart");
            String minuteEnd = request.getParameter("minuteend");
            String timeStart = hourStart + ":" + minuteStart;
            String timeEnd = hourEnd + ":" + minuteEnd;
            String roleID = request.getParameter("roleid");
            int timePerSlot = Integer.parseInt(request.getParameter("timePerSlot"));
            Part avatar = request.getPart("avatar");
            String msg = "";
            boolean flag = false;

            int intHourStart = Integer.parseInt(hourStart);
            int intHourEnd = Integer.parseInt(hourEnd);
            int intMinuteStart = Integer.parseInt(minuteStart);
            int intMinuteEnd = Integer.parseInt(minuteEnd);

            UserDTO existingUser = UserDAO.getUserByUserAccount(username);

            if (existingUser == null) {
                if (!UserDAO.emailIsUnique(email) && !flag) {
                    msg = "Email đã được đăng ký";
                    request.setAttribute(EMAIL_ERROR, msg);
                    flag = true;
                } else if (!UserDAO.phoneIsUnique(phoneNumber)) {
                    msg = "Số điện thoại này đã được đăng ký";
                    request.setAttribute(PHONE_NUMBER_ERROR, msg);
                    flag = true;
                } else if (intHourStart > intHourEnd && intMinuteStart > intMinuteEnd) {
                    msg = "Thời gian mở cửa phải trước thời gian đóng cửa";
                    request.setAttribute(TIME_ERROR, msg);
                    flag = true;
                } else if (password.equals(confirmedpassword)) {
                    HttpSession session = request.getSession();
                    String userID;
                    do {
                        userID = UserDAO.getRandomUserId(roleID);
                    } while (UserDAO.getUserByUserId(userID) != null);

<<<<<<< HEAD
                    UserDTO newUser = UserDAO.createUser(userID, username, password, roleID, fullname, birthday, gender, phoneNumber, email, city, district, true, null);
=======
                    UserDTO newUser = UserDAO.createUser(userID, username, password, roleID, fullname, null, null, phoneNumber, email, city, district, true, null);
                    session.setAttribute("User", newUser);
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722

                    String studioID = StudioDAO.getNewStudioId();
                    StudioDTO stu = new StudioDTO(studioID, userID, studioName, address, city, district, phoneNumber, email, true, timeStart, timeEnd, null, timePerSlot);
                    boolean check = StudioDAO.addStudio(stu);

                    //input avatar cua studio vao database
                    InputStream fileContent = avatar.getInputStream();
                    byte[] data = new byte[(int) avatar.getSize()];
                    fileContent.read(data);
                    String base64Data = Base64.getEncoder().encodeToString(data);
                    ImgDAO dao = new ImgDAO();
                    String imageName = "Avatar studio " + studioName;
                    String imageRole = "AVA";
                    String imageStudioID = studioID;
                    String imageUserID = null;
                    String imageSerivceID = null;
                    String imageSerivceDetailID = null;
                    boolean status = true;
                    dao.addImage(imageName, base64Data, imageRole, imageStudioID, imageUserID, imageSerivceID, imageSerivceDetailID, status);
                    if (!check) {
                        msg = "Error";
                        flag = true;
                    } else {
                        boolean updateCheck = UserDAO.updateStudioIDForUser(studioID, newUser);
                        if (!updateCheck) {
                            msg = "Error";
                            flag = true;
                        } else {
                            while (intHourStart < intHourEnd) {
                                timeStart = intHourStart + ":" + minuteStart;
                                intHourStart = intHourStart + timePerSlot;
                                if (intHourStart > intHourEnd) {

                                } else {
                                    if (intHourStart == intHourEnd) {
                                        if (intMinuteStart > intMinuteEnd) {

                                        } else {
                                            timeEnd = intHourStart + ":" + minuteStart;
                                            SlotDAO.addStudioSlot(studioID, timeStart, timeEnd);
                                        }
                                    } else {
                                        timeEnd = intHourStart + ":" + minuteStart;
                                        SlotDAO.addStudioSlot(studioID, timeStart, timeEnd);
                                    }
                                }

                            }
                        }
                    }
<<<<<<< HEAD
                    UserDTO newUser2 = UserDAO.getUserByUserId(userID);
                    session.setAttribute("User", newUser2);
=======

>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
                    url = "HomeController";
                } else {
                    msg = "Mật khẩu không trùng khớp";
                    request.setAttribute(CONFIRM_PASSWORD_ERROR, msg);
                    flag = true;
                }
            } else {
                msg = "Tên đăng nhập đã tồn tại";
                request.setAttribute(USER_NAME_ERROR, msg);
                flag = true;
            }

            if (flag) {
                url = "studiosignup.jsp";
            }
        } catch (Exception e) {
            log("Error at StudioSignUpController" + e.toString());
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
