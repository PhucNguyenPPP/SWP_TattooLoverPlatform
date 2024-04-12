/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CityDAO;
import DAO.DistrictDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
@MultipartConfig
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String HOME = "homepage";
    private static final String HOME_CONTROLLER = "HomeController";
    private static final String HOME_PAGE = "home.jsp";
    private static final String LOGIN = "login";
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String LOGIN_SERVLET = "loginservlet";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT_SERVLET = "logoutservlet";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String SIGNUP = "signup";
    private static final String SIGNUP_PAGE = "signup.jsp";
    private static final String SIGNUP_SERVLET = "signupservlet";
    private static final String SIGN_CONTROLLER = "SignUpController";
    private static final String STUDIO_SIGNUP = "studiosignup";
    private static final String STUDIO_SIGNUP_PAGE = "studiosignup.jsp";
    private static final String STUDIO_SIGNUP_SERVLET = "studiosignupservlet";
    private static final String STUDIO_SIGNUP_CONTROLLER = "StudioSignUpController";
    private static final String GETSTUINFOR = "getStuInfor";
    private static final String GETSTUINFOR_CONTROLLER = "GetStuInforController";
    private static final String SEARCHSTU = "SearchStudio";
    private static final String SEARCHSTU_CONTROLLER = "SearchStudioController";
    private static final String VIEWSTUDIO = "ViewStudio";
    private static final String VIEWSTUDIO_CONTROLLER = "ViewStudioInforController";
    private static final String SORTSERVICE_STUDIO = "SortServiceStudio";
    private static final String SORTSERVICE_STUDIO_CONTROLLER = "SortServiceStudioController";
    private static final String STUDIO_SERVICE = "ViewStudioService";
    private static final String STUDIO_SERVICE_CONTROLLER = "ViewStudioServiceController";
    private static final String BOOK_SERVICE = "BookService";
    private static final String BOOK_SERVICE_CONTROLLER = "BookServiceController";
    private static final String BOOK_FORM = "BookForm";
    private static final String BOOK_FORM_CONTROLLER = "BookFormController";
    private static final String BOOK_LIST = "BookList";
    private static final String BOOK_LIST_CONTROLLER = "BookListController";
    private static final String DETAIL_BOOKING = "DetailBooking";
    private static final String DETAIL_BOOKING_CONTROLLER = "BookingDetailController";
    private static final String FEEDBACK = "Feedback";
    private static final String FEEDBACK_CONTROLLER = "FeedbackController";
    private static final String NOTIFICATION = "Notification";
    private static final String NOTIFICATION_CONTROLLER = "NotificationController";
    private static final String DELETE_BOOKING_TL = "DeleteBookingTL";
    private static final String DELETE_BOOKING_TL_CONTROLLER = "DeleteBookingController";
    private static final String BOOK_LIST_STU = "BookListStu";
    private static final String BOOK_LIST_STU_CONTROLLER = "BookListStuController";
    private static final String BOOK_LIST_ARTIST = "BookListArtist";
    private static final String BOOK_LIST_ARTIST_CONTROLLER = "BookListArtistController";
    private static final String DELETE_BOOKING_STU = "DeleteBookingStu";
    private static final String DELETE_BOOKING_STU_CONTROLLER = "DeleteBookingStuController";
    private static final String BOOKING_DETAIL_MANAGER = "DetailBookingStu";
    private static final String BOOKING_DETAIL_MANAGER_CONTROLLER = "BookingDetailManagerController";
    private static final String BOOKING_DETAIL_ARTIST = "DetailBookingArtist";
    private static final String BOOKING_DETAIL_ARTIST_CONTROLLER = "BookingDetailArtistController";
    private static final String VIEWACCOUNT_STU = "ViewAccountStu";
    private static final String VIEWACCOUNT_STU_CONTROLLER = "AccountViewStuController";
    private static final String SEARCH_ACCOUNT_STU = "SearchAccountStu";
    private static final String SEARCH_ACCOUNT_STU_CONTROLLER = "SearchAccountStuController";
    private static final String VIEWACCOUNT_SYSTEM = "ViewAccountSystem";
    private static final String VIEWACCOUNT_SYSTEM_CONTROLLER = "AccountViewSystemController";
    private static final String SEARCH_ACCOUNT_SYSTEM = "SearchAccountSystem";
    private static final String SEARCH_ACCOUNT_SYSTEM_CONTROLLER = "SearchAccountSystemController";
    private static final String USER_INFO = "userprofile";
    private static final String VIEW_USER_INFO = "ViewUserInfoController";
    private static final String EDIT_USER_SERVLET = "edituserservlet";
    private static final String EDIT_USER_CONTROLLER = "EditUserProfileController";
    private static final String INSPECT_BOOKING = "Inspect";
    private static final String INSPECT_BOOKING_CONTROLLER = "InspectBookingController";
    private static final String FINISH_BOOKING = "FinishBooking";
    private static final String FINISH_BOOKING_CONTROLLER = "FinishBookingController";
    private static final String STUDIOMANAGERINFO = "StudioManagerInfo";
    private static final String STUDIOMANAGERINFO_CONTROLLER = "StudioManagerInfoController";
    private static final String EDITMANAGER = "editManager";
    private static final String EDITMANAGER_CONTROLLER = "editManagerController";
    private static final String PRICE_LIST = "priceList";
    private static final String PRICELIST_CONTROLLER = "priceListController";
    private static final String PRICELISTADD = "priceListAdd";
    private static final String PRICELISTADD_CONTROLLER = "priceListAddController";
    private static final String DELSERVICE = "delService";
    private static final String DELSERVICE_CONTROLLER = "delServiceController";
    private static final String EDITPRICE = "editPriceList";
    private static final String EDITPRICE_CONTROLLER = "editPriceListController";
    private static final String EDIT_PRICE_CONFIRM = "editPriceConfirm";
    private static final String EDIT_PRICE_CONFIRM_CONTROLLER = "editPriceConfirmController";
    private static final String ADD_IMAGE_STU = "addImageStu";
    private static final String ADD_IMAGE_CONTROLLER = "AddImageStuController";
    private static final String VIEWSERVICE = "ViewService";
    private static final String VIEWSERVICE_CONTROLLER = "ServiceListController";
    private static final String SEARCH_SERVICE = "SearchService";
    private static final String SEARCH_SERVICE_CONTROLLER = "SearchServiceController";
    private static final String SEARCH_SERVICE_DETAIL = "SearchServiceDetail";
    private static final String SEARCH_SERVICE_DETAIL_CONTROLLER = "SearchServiceDetailController";
    private static final String SEARCH_SERVICE_SIZE = "SearchServiceSize";
    private static final String SEARCH_SERVICE_SIZE_CONTROLLER = "SearchServiceSizeController";
    private static final String CREATE_SERVICE = "CreateService";
    private static final String CREATE_SERVICE_PAGE = "CreateService.jsp";
    private static final String CREATE_SERVICE_DETAIL = "CreateServiceDetail";
    private static final String CREATE_SERVICE_DETAIL_PAGE = "CreateServiceDetail.jsp";
    private static final String CREATE_SERVICE_SIZE = "CreateServiceSize";
    private static final String CREATE_SERVICE_SIZE_PAGE = "CreateServiceSize.jsp";
    private static final String SERVICE_CREATE = "ServiceCreate";
    private static final String CREATE_SERVICE_CONTROLLER = "CreateServiceController";
    private static final String SERVICE_DETAIL_CREATE = "ServiceDetailCreate";
    private static final String CREATE_SERVICE_DETAIL_CONTROLLER = "CreateServiceDetailController";
    private static final String SERVICE_SIZE_CREATE = "ServiceSizeCreate";
    private static final String CREATE_SERVICE_SIZE_CONTROLLER = "CreateServiceSizeController";
    private static final String DELETE_SERVICE = "DeleteServiceSystem";
    private static final String DELETE_SERVICE_CONTROLLER = "DeleteServiceSystemController";
    private static final String DELETE_SERVICE_DETAIL = "DeleteServiceDetailSystem";
    private static final String DELETE_SERVICE_DETAIL_CONTROLLER = "DeleteServiceDetailSystemController";
    private static final String DELETE_SERVICE_SIZE = "DeleteServiceSizeSystem";
    private static final String DELETE_SERVICE_SIZE_CONTROLLER = "DeleteServiceSizeSystemController";
    private static final String DELETE_USER = "DeleteUser";
    private static final String DELETE_USER_CONTROLLER = "DeleteUserController";
    private static final String DELETE_USER_MANAGER = "DeleteUserManager";
    private static final String DELETE_USER_MANAGER_CONTROLLER = "DeleteUserManagerController";
    private static final String DELETE_STAFF_STU = "DeleteStaffStu";
    private static final String DELETE_STAFF_STU_CONTROLLER = "DeleteStaffStuController";
<<<<<<< HEAD
    private static final String FORGET_PASSWORD = "ForgetPassword";
    private static final String FORGET_PASSWORD_PAGE = "ForgetPassword.jsp";
    private static final String SEND_OTP = "SendOTP";
    private static final String SEND_OTP_CONTROLLER = "SendOTPController";
    private static final String CHECK_OTP = "checkOTP";
    private static final String CHECK_OTP_CONTROLLER= "CheckOTPController";
    private static final String GET_NEW_PASSWORD = "getNewPassword";
    private static final String GET_NEW_PASSWORD_CONTROLLER= "GetNewPasswordController";
=======
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String url = HOME_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = HOME_CONTROLLER;
            } else if (action.equals(HOME)) {
                url = HOME_CONTROLLER;
            } else if (action.equals(LOGIN)) {
                url = LOGIN_PAGE;
            } else if (action.equals(LOGIN_SERVLET)) {
                url = LOGIN_CONTROLLER;
            } else if (action.equals(LOGOUT_SERVLET)) {
                url = LOGOUT_CONTROLLER;
            } else if (action.equals(SIGNUP)) {
<<<<<<< HEAD
=======
                session.setAttribute("cityList", CityDAO.getCityListStatic());
                session.setAttribute("HCMDistrictList", DistrictDAO.getDistrictListByCityID("CT001"));
                session.setAttribute("HNDistrictList", DistrictDAO.getDistrictListByCityID("CT002"));
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
                url = SIGNUP_PAGE;
            } else if (action.equals(SIGNUP_SERVLET)) {
                url = SIGN_CONTROLLER;
            } else if (action.equals(STUDIO_SIGNUP)) {
<<<<<<< HEAD
=======
                session.setAttribute("cityList", CityDAO.getCityListStatic());
                session.setAttribute("HCMDistrictList", DistrictDAO.getDistrictListByCityID("CT001"));
                session.setAttribute("HNDistrictList", DistrictDAO.getDistrictListByCityID("CT002"));
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
                url = STUDIO_SIGNUP_PAGE;
            } else if (action.equals(STUDIO_SIGNUP_SERVLET)) {
                url = STUDIO_SIGNUP_CONTROLLER;
            } else if (action.equals(GETSTUINFOR)) {
                url = GETSTUINFOR_CONTROLLER;
            } else if (action.equals(SEARCHSTU)) {
                url = SEARCHSTU_CONTROLLER;
            } else if (action.equals(VIEWSTUDIO)) {
                url = VIEWSTUDIO_CONTROLLER;
            } else if (action.equals(SORTSERVICE_STUDIO)) {
                url = SORTSERVICE_STUDIO_CONTROLLER;
            } else if (action.equals(STUDIO_SERVICE)) {
                url = STUDIO_SERVICE_CONTROLLER;
            } else if (action.equals(BOOK_SERVICE)) {
                url = BOOK_SERVICE_CONTROLLER;
            } else if (action.equals(BOOK_FORM)) {
                url = BOOK_FORM_CONTROLLER;
            } else if (action.equals(BOOK_LIST)) {
                url = BOOK_LIST_CONTROLLER;
            } else if (action.equals(DETAIL_BOOKING)) {
                url = DETAIL_BOOKING_CONTROLLER;
            } else if (action.equals(FEEDBACK)) {
                url = FEEDBACK_CONTROLLER;
            } else if (action.equals(NOTIFICATION)) {
                url = NOTIFICATION_CONTROLLER;
            } else if (action.equals(DELETE_BOOKING_TL)) {
                url = DELETE_BOOKING_TL_CONTROLLER;
            } else if (action.equals(BOOK_LIST_STU)) {
                url = BOOK_LIST_STU_CONTROLLER;
            } else if (action.equals(BOOK_LIST_ARTIST)) {
                url = BOOK_LIST_ARTIST_CONTROLLER;
            } else if (action.equals(DELETE_BOOKING_STU)) {
                url = DELETE_BOOKING_STU_CONTROLLER;
            } else if (action.equals(BOOKING_DETAIL_MANAGER)) {
                url = BOOKING_DETAIL_MANAGER_CONTROLLER;
            } else if (action.equals(BOOKING_DETAIL_ARTIST)) {
                url = BOOKING_DETAIL_ARTIST_CONTROLLER;
            } else if (action.equals(VIEWACCOUNT_STU)) {
                url = VIEWACCOUNT_STU_CONTROLLER;
            } else if (action.equals(SEARCH_ACCOUNT_STU)) {
                url = SEARCH_ACCOUNT_STU_CONTROLLER;
            } else if (action.equals(VIEWACCOUNT_SYSTEM)) {
                url = VIEWACCOUNT_SYSTEM_CONTROLLER;
            } else if (action.equals(SEARCH_ACCOUNT_SYSTEM)) {
                url = SEARCH_ACCOUNT_SYSTEM_CONTROLLER;
            } else if (action.equals(USER_INFO)) {
                url = VIEW_USER_INFO;
            } else if (action.equals(EDIT_USER_SERVLET)) {
                url = EDIT_USER_CONTROLLER;
            } else if (action.equals(INSPECT_BOOKING)) {
                url = INSPECT_BOOKING_CONTROLLER;
            } else if (action.equals(FINISH_BOOKING)) {
                url = FINISH_BOOKING_CONTROLLER;
            } else if (action.equals(STUDIOMANAGERINFO)) {
                url = STUDIOMANAGERINFO_CONTROLLER;
            } else if (action.equals(EDITMANAGER)) {
                url = EDITMANAGER_CONTROLLER;
            } else if (action.equals(PRICE_LIST)) {
                url = PRICELIST_CONTROLLER;
            } else if (action.equals(PRICELISTADD)) {
                url = PRICELISTADD_CONTROLLER;
            } else if (action.equals(DELSERVICE)) {
                url = DELSERVICE_CONTROLLER;
            } else if (action.equals(EDITPRICE)) {
                url = EDITPRICE_CONTROLLER;
            } else if (action.equals(EDIT_PRICE_CONFIRM)) {
                url = EDIT_PRICE_CONFIRM_CONTROLLER;
            } else if (action.equals(ADD_IMAGE_STU)) {
                url = ADD_IMAGE_CONTROLLER;
            } else if (action.equals(VIEWSERVICE)) {
                url = VIEWSERVICE_CONTROLLER;
            } else if (action.equals(SEARCH_SERVICE)) {
                url = SEARCH_SERVICE_CONTROLLER;
            } else if (action.equals(SEARCH_SERVICE_DETAIL)) {
                url = SEARCH_SERVICE_DETAIL_CONTROLLER;
            } else if (action.equals(SEARCH_SERVICE_SIZE)) {
                url = SEARCH_SERVICE_SIZE_CONTROLLER;
            } else if (action.equals(CREATE_SERVICE)) {
                url = CREATE_SERVICE_PAGE;
            } else if (action.equals(SERVICE_CREATE)) {
                url = CREATE_SERVICE_CONTROLLER;
            } else if (action.equals(CREATE_SERVICE_DETAIL)) {
                url = CREATE_SERVICE_DETAIL_PAGE;
            } else if (action.equals(SERVICE_DETAIL_CREATE)) {
                url = CREATE_SERVICE_DETAIL_CONTROLLER;
            } else if (action.equals(CREATE_SERVICE_SIZE)) {
                url = CREATE_SERVICE_SIZE_PAGE;
            } else if (action.equals(SERVICE_SIZE_CREATE)) {
                url = CREATE_SERVICE_SIZE_CONTROLLER;
            } else if (action.equals(DELETE_SERVICE)) {
                url = DELETE_SERVICE_CONTROLLER;
            } else if (action.equals(DELETE_SERVICE_DETAIL)) {
                url = DELETE_SERVICE_DETAIL_CONTROLLER;
            } else if (action.equals(DELETE_SERVICE_SIZE)) {
                url = DELETE_SERVICE_SIZE_CONTROLLER;
            } else if (action.equals(DELETE_USER)) {
                url = DELETE_USER_CONTROLLER;
            } else if (action.equals(DELETE_USER_MANAGER)) {
                url = DELETE_USER_MANAGER_CONTROLLER;
            } else if (action.equals(DELETE_STAFF_STU)) {
                url = DELETE_STAFF_STU_CONTROLLER;
<<<<<<< HEAD
            } else if(action.equals(FORGET_PASSWORD)){
               url = FORGET_PASSWORD_PAGE;
            } else if (action.equals(SEND_OTP)) {
                url = SEND_OTP_CONTROLLER;
            } else if (action.equals(CHECK_OTP)){
                url = CHECK_OTP_CONTROLLER;
            } else if (action.equals(GET_NEW_PASSWORD)){
                url = GET_NEW_PASSWORD_CONTROLLER;
=======
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
            }
        } catch (Exception e) {
            log("Error ar MainController: " + e.toString());
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
