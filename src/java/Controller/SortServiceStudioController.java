/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CityDAO;
import DAO.ServiceDAO;
import DAO.StudioDAO;
import DTO.CityDTO;
import DTO.ImgDTO;
import DTO.RatingDTO;
import DTO.ServiceDTO;
import DTO.ServiceDetailDTO;
import DTO.StudioDTO;
import DTO.StudioServiceDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "SortServiceStudioController", urlPatterns = {"/SortServiceStudioController"})
public class SortServiceStudioController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "studioList.jsp";
    private static final String SUCCESS = "studioList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String url = ERROR;
        try {
            String serviceID = request.getParameter("service");
            String serviceDetailID = request.getParameter("serviceDetail");
            String cityID = request.getParameter("city");
            String rating = request.getParameter("feedback");
            String studioSearch = request.getParameter("searchStudio");
            
            request.setAttribute("SERVICE_ID", serviceID);
            request.setAttribute("SERVICE_DETAIL_ID", serviceDetailID);
            request.setAttribute("CITY_ID", cityID);
            request.setAttribute("FEEDBACK", rating);

            StudioDAO studioDao = new StudioDAO();
            /*lay list studio khi sort bang service*/
            List<StudioDTO> listStudio = studioDao.getSortStudioList(serviceID, serviceDetailID, cityID, rating, studioSearch);

            ServiceDAO serviceDao = new ServiceDAO();
            /*lay tat ca service cho filter*/
            List<ServiceDTO> listService = serviceDao.getService();
            request.setAttribute("LIST_SERVICE", listService);

            /*lay tat ca service detail cho filter*/
            List<ServiceDetailDTO> listServiceDetail = serviceDao.getServiceDetail();
            request.setAttribute("LIST_SERVICE_DETAIL", listServiceDetail);

            CityDAO cityDao = new CityDAO();
            /*lay tat ca city cho city*/
            List<CityDTO> listCity = cityDao.getCityList();
            request.setAttribute("LIST_CITY", listCity);

            if (listStudio.size() > 0) {
                request.setAttribute("LIST_STUDIO", listStudio);

                /*lay tat ca service cua 1 Studio*/
                List<StudioServiceDTO> listSeriveStu = new ArrayList<>();
                /*lay tat ca servicedetail cua 1 Studio*/
                List<StudioServiceDTO> listServiceDetailStu = new ArrayList<>();
                /*lay service gia thap nhat cua 1 studio*/
                List<StudioServiceDTO> listMinPrice = new ArrayList<>();
                /*lay service gia cao nhat cua 1 studio*/
                List<StudioServiceDTO> listMaxPrice = new ArrayList<>();
                /*lay avatar cua 1 studio*/
                List<ImgDTO> listAvatar = new ArrayList<>();

                List<RatingDTO> listRating = new ArrayList<>();

                for (StudioDTO s : listStudio) {
                    String studioID = s.getId();
                    listSeriveStu.addAll(studioDao.getService(studioID));
                    listServiceDetailStu.addAll(studioDao.getServiceDetail(studioID));
                    listMinPrice.addAll(studioDao.getMinPrice(studioID));
                    listMaxPrice.addAll(studioDao.getMaxPrice(studioID));
                    listAvatar.addAll(studioDao.getAvatarStuList(studioID));
                    listRating.addAll(studioDao.getRatingStudioList(studioID));
                }

                request.setAttribute("LIST_SERVICE_STUDIO_INFOR", listSeriveStu);
                request.setAttribute("LIST_SERVICE_DETAIL_STUDIO_INFOR", listServiceDetailStu);
                request.setAttribute("LIST_MIN_PRICE", listMinPrice);
                request.setAttribute("LIST_MAX_PRICE", listMaxPrice);
                request.setAttribute("LIST_AVATAR", listAvatar);
                request.setAttribute("LIST_RATING", listRating);

                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Không tìm thấy tiệm xăm phù hợp");
                url = ERROR;
            }
        } catch (Exception e) {
            log("Error ar SortServiceStudioController: " + e.toString());
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
