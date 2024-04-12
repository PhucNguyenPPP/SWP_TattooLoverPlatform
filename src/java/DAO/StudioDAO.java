/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BookingDTO;
import DTO.ImgDTO;
import DTO.RatingDTO;
import DTO.ServiceSizeDTO;
import DTO.SlotDTO;
import DTO.StudioDTO;
import DTO.StudioServiceDTO;
import DTO.UserDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class StudioDAO {

    private static final String GET_STUDIO_INFOR = "SELECT managerID, studioName, "
            + "studioAddress, c.cityName, d.districtName, studioPhoneNumber, "
            + "studioEmail, status, timeStart, timeEnd, description, "
            + "studioSlotTime "
            + "FROM Studio s, City c, District d "
            + "WHERE s.cityID = c.cityID "
            + "AND s.districtID = d.districtID "
            + "AND studioID= ?";

    private static final String GET_STUDIO_SERVICE = "SELECT distinct ss.serviceID, "
            + "s.serviceName, ss.status "
            + "FROM Studio_Service ss, Service s "
            + "WHERE ss.studioID=? "
            + "AND ss.serviceID = s.serviceID";

    private static final String GET_STUDIO_SERVICE_DETAIL = "SELECT distinct ss.serviceDetailID, sd.serviceDetailName, ss.status "
            + "FROM Studio_Service ss, Service_Detail sd "
            + "WHERE ss.studioID=? "
            + "AND ss.serviceDetailID = sd.serviceDetailID ";

    private static final String GET_MIN_PRICE = "SELECT MIN(price) as minPrice "
            + "FROM Studio_Service ss "
            + "WHERE ss.studioID =? "
            + "AND ss.status = 1 "
            + "GROUP BY ss.studioID";

    private static final String GET_MAX_PRICE = "SELECT MAX(price) as maxPrice "
            + "FROM Studio_Service ss "
            + "WHERE ss.studioID =? "
            + "AND ss.status = 1 "
            + "GROUP BY ss.studioID";

    private static final String GET_AVATAR = "SELECT * "
            + "FROM Image "
            + "WHERE role = 'AVA' "
            + "AND studioID=?";

    private static final String GET_SERVICE_IMG = "SELECT * "
            + "FROM Image "
            + "WHERE role = 'SV' "
            + "AND studioID=?";

    private static final String GET_STUDIO_SERVICE_DETAIL_NORMAL = "SELECT distinct ss.serviceID, "
            + "s.serviceName, ss.serviceDetailID, sd.serviceDetailName, ss.status "
            + "FROM Studio_Service ss, Service s, Service_Detail sd WHERE ss.studioID=? "
            + "AND ss.serviceID = 'SV001' "
            + "AND ss.serviceID = s.serviceID "
            + "AND ss.serviceDetailID = sd.serviceDetailID";

    private static final String GET_STUDIO_SERVICE_DETAIL_COLOR = "SELECT distinct ss.serviceID, "
            + "s.serviceName, ss.serviceDetailID, sd.serviceDetailName, ss.status "
            + "FROM Studio_Service ss, Service s, Service_Detail sd WHERE ss.studioID=? "
            + "AND ss.serviceID = 'SV002' "
            + "AND ss.serviceID = s.serviceID "
            + "AND ss.serviceDetailID = sd.serviceDetailID";

    private static final String GET_SERVICE_SIZE = "SELECT * FROM Service_Size";

    private static final String GET_STUDIO_SLOT = "SELECT * FROM Slot WHERE studioID = ?";

    private static final String SEARCH_STUDIO_BYNAME = "SELECT studioID, managerID, "
            + "studioName, studioAddress, c.cityName, d.districtName, studioPhoneNumber, "
            + "studioEmail, status, timeStart, timeEnd, description, studioSlotTime "
            + "FROM Studio s, City c, District d "
            + "WHERE s.cityID = c.cityID "
            + "AND s.districtID = d.districtID "
            + "AND studioName like ?";

    private static final String SEARCH_STUDIO_BY_SERVICE = "SELECT distinct s.studioID, managerID, "
            + "studioName, studioAddress, c.cityName, d.districtName, studioPhoneNumber, "
            + "studioEmail, s.status, timeStart, timeEnd, description, studioSlotTime "
            + "FROM Studio s, Studio_Service ss, City c, District d "
            + "WHERE s.studioID = ss.studioID "
            + "AND s.cityID = c.cityID "
            + "AND s.districtID = d.districtID "
            + "AND ss.serviceID = ? "
            + "AND ss.status = 1";

    private static final String SEARCH_STUDIO_BY_SERVICE_DETAIL = "SELECT distinct s.studioID, managerID, "
            + "studioName, studioAddress, c.cityName, d.districtName, studioPhoneNumber, "
            + "studioEmail, s.status, timeStart, timeEnd, description, studioSlotTime "
            + "FROM Studio s, Studio_Service ss, City c, District d "
            + "WHERE s.studioID = ss.studioID "
            + "AND s.cityID = c.cityID "
            + "AND s.districtID = d.districtID "
            + "AND ss.serviceDetailID = ? "
            + "AND ss.status = 1";

    private static final String GET_AMOUNT_ARTIST = "SELECT count(u.userID) as amountArtist "
            + "FROM [User] u  "
            + "where u.roleID = 'SA' "
            + "AND u.studioID = ? "
            + "AND u.status = 1 ";
    private static final String GET_RATING_STUDIO = "SELECT ROUND(AVG(CAST(rating AS FLOAT)), 1) AS rating "
            + "FROM Booking "
            + "WHERE studioID = ? "
            + "AND status = N'Hoàn thành' "
            + "AND rating != 0";

    private static final String GET_STUID_BY_USERID = "SELECT studioID FROM [User] WHERE userID=? AND status = 1";

    private static final String GET_ARTIST_AVAILABLE = "SELECT u.userID, u.fullname "
            + "FROM [User] u "
            + "WHERE u.roleID = 'SA' "
            + "AND u.studioID = ? "
            + "AND u.status = 1 "
            + "AND u.userID not in ("
            + "SELECT artistID "
            + "FROM Booking b, Booking_StudioService bs "
            + "WHERE b.bookingID = bs.bookingID "
            + "AND b.studioID = ? "
            + "AND b.bookingDate = ? "
            + "AND slotID = ? "
            + "AND bs.status = 1"
            + "AND bs.artistID != null)";

    private static final String UPDATE_STUDIO = "UPDATE [Studio] SET studioPhoneNumber=?,studioEmail=?, timeStart=?, timeEnd=?, studioSlotTime=?, description=? WHERE studioID=?";
    private static final String GET_MANAGERID_BY_STUDIOID = "SELECT s.managerID "
            + "FROM Studio s, [User] u "
            + "WHERE s.managerID = u.userID "
            + "AND s.studioID = ? "
            + "AND u.status = 1";

    private static final String DELETE_STUDIO = "UPDATE Studio SET status = 0 WHERE managerID = ?";

    /*lấy full thông tin của studio bằng studioID*/
    public StudioDTO getStuInfor(String studioID) throws SQLException {
        StudioDTO studio = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STUDIO_INFOR);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String managerID = rs.getString("managerID");
                    String studioName = rs.getString("studioName");
                    String studioAddress = rs.getString("studioAddress");
                    String city = rs.getString("cityName");
                    String district = rs.getString("districtName");
                    String studioPhoneNumber = rs.getString("studioPhoneNumber");
                    String studioEmail = rs.getString("studioEmail");
                    boolean status = rs.getBoolean("status");

                    DateFormat format = new SimpleDateFormat("H:mm");

                    Time ftimeStart = rs.getTime("timeStart");
                    String timeStart = format.format(ftimeStart);

                    Time ftimeEnd = rs.getTime("timeEnd");
                    String timeEnd = format.format(ftimeEnd);

                    String description = rs.getString("description");
                    int studioSlotTime = rs.getInt("studioSlotTime");
                    if (status == true) {
                        studio = new StudioDTO(studioID, managerID, studioName, studioAddress,
                                city, district, studioPhoneNumber, studioEmail, status, timeStart, timeEnd, description, studioSlotTime);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return studio;
    }

    /*lấy tất cả dịch vụ mà studio có bằng studioID*/
    public List<StudioServiceDTO> getService(String studioID) throws SQLException {
        List<StudioServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STUDIO_SERVICE);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceID = rs.getString("serviceID");
                    String serviceName = rs.getString("serviceName");
                    boolean status = rs.getBoolean("status");
                    if (status == true) {
                        list.add(new StudioServiceDTO(null, studioID, serviceID, serviceName, null, null, null, null, null, 0, 0, status));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public String getManagerIDByStudioID(String studioID) throws SQLException {
        String managerID = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_MANAGERID_BY_STUDIOID);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    managerID = rs.getString("managerID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return managerID;
    }

    /*lấy tất cả chi tiết dịch vụ mà studio có bằng studioID*/
    public List<StudioServiceDTO> getServiceDetail(String studioID) throws SQLException {
        List<StudioServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STUDIO_SERVICE_DETAIL);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceDetailID = rs.getString("serviceDetailID");
                    String serviceDetailName = rs.getString("serviceDetailName");
                    boolean status = rs.getBoolean("status");
                    if (status == true) {
                        list.add(new StudioServiceDTO(null, studioID, null, null, serviceDetailID, serviceDetailName, null, null, null, 0, 0, status));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    /*lấy giá dịch vụ thấp nhất của studio bằng studioID*/
    public List<StudioServiceDTO> getMinPrice(String studioID) throws SQLException {
        List<StudioServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_MIN_PRICE);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int minPrice = rs.getInt("minPrice");

                    list.add(new StudioServiceDTO(null, studioID, null, null, null, null, null, null, null, minPrice, 0, true));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    /*lấy giá dịch vụ cao nhất của studio bằng studioID*/
    public List<StudioServiceDTO> getMaxPrice(String studioID) throws SQLException {
        List<StudioServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_MAX_PRICE);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int maxPrice = rs.getInt("maxPrice");
                    list.add(new StudioServiceDTO(null, studioID, null, null, null, null, null, null, null, maxPrice, 0, true));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    /*lấy list avatar của studio bằng studioID*/
    public List<ImgDTO> getAvatarStuList(String studioID) throws SQLException {
        List<ImgDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_AVATAR);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String imgID = rs.getString("imgID");
                    String imgName = rs.getString("imgName");
                    String imgLink = rs.getString("imgLink");
                    String role = rs.getString("role");
                    list.add(new ImgDTO(imgID, imgName, imgLink, role, studioID, null, null, null));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    /*lấy list avatar của 1 studio bằng studioID*/
    public ImgDTO getAvatarStu(String studioID) throws SQLException {
        ImgDTO img = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_AVATAR);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String imgID = rs.getString("imgID");
                    String imgName = rs.getString("imgName");
                    String imgLink = rs.getString("imgLink");
                    String role = rs.getString("role");
                    img = new ImgDTO(imgID, imgName, imgLink, role, studioID, null, null, null);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return img;
    }

    /*lấy list ảnh service của 1 studio bằng studioID*/
    public List<ImgDTO> getServiceImageList(String studioID) throws SQLException {
        List<ImgDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SERVICE_IMG);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String imgID = rs.getString("imgID");
                    String imgName = rs.getString("imgName");
                    String imgLink = rs.getString("imgLink");
                    String role = rs.getString("role");
                    list.add(new ImgDTO(imgID, imgName, imgLink, role, studioID, null, null, null));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    /*lấy full chi tiết dịch vụ ứng với dịch vụ xăm thường mà studio có bằng studioID*/
    public List<StudioServiceDTO> getServiceDetailNormal(String studioID) throws SQLException {
        List<StudioServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STUDIO_SERVICE_DETAIL_NORMAL);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceID = rs.getString("serviceID");
                    String serviceName = rs.getString("serviceName");
                    String serviceDetailID = rs.getString("serviceDetailID");
                    String serviceDetailName = rs.getString("serviceDetailName");
                    boolean status = rs.getBoolean("status");
                    if (status == true) {
                        list.add(new StudioServiceDTO(null, null, serviceID, serviceName, serviceDetailID, serviceDetailName, null, null, null, 0, 0, status));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    /*lấy full chi tiết dịch vụ ứng với dịch vụ màu thường mà studio có bằng studioID*/
    public List<StudioServiceDTO> getServiceDetailColor(String studioID) throws SQLException {
        List<StudioServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STUDIO_SERVICE_DETAIL_COLOR);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceID = rs.getString("serviceID");
                    String serviceName = rs.getString("serviceName");
                    String serviceDetailID = rs.getString("serviceDetailID");
                    String serviceDetailName = rs.getString("serviceDetailName");
                    boolean status = rs.getBoolean("status");
                    if (status == true) {
                        list.add(new StudioServiceDTO(null, null, serviceID, serviceName, serviceDetailID, serviceDetailName, null, null, null, 0, 0, status));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    /*lấy full size*/
    public List<ServiceSizeDTO> getServiceSize() throws SQLException {
        List<ServiceSizeDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SERVICE_SIZE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceSizeID = rs.getString("serviceSizeID");
                    String systemStaffID = rs.getString("systemStaffID");
                    String serviceSizeName = rs.getString("serviceSizeName");
                    boolean status = rs.getBoolean("status");
                    if (status == true) {
                        list.add(new ServiceSizeDTO(serviceSizeID, systemStaffID, serviceSizeName, status));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    /*lay slot làm việc của studio bàng studioID*/
    public List<SlotDTO> getSlotListStudio(String studioID) throws SQLException {
        List<SlotDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STUDIO_SLOT);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String slotID = rs.getString("slotID");
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                    String timeStart = formatter.format(rs.getTime("timeStart"));
                    String timeEnd = formatter.format(rs.getTime("timeEnd"));
                    boolean status = rs.getBoolean("status");
                    if (status == true) {
                        list.add(new SlotDTO(slotID, studioID, timeStart, timeEnd, status));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    /*lấy danh sách studio chứa ký tự search*/
    public List<StudioDTO> getListStudio(String search) throws SQLException {
        List<StudioDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_STUDIO_BYNAME);
                ptm.setNString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String studioID = rs.getString("studioID");
                    String managerID = rs.getString("managerID");
                    String studioName = rs.getString("studioName");
                    String studioAddress = rs.getString("studioAddress");
                    String city = rs.getString("cityName");
                    String district = rs.getString("districtName");
                    String studioPhoneNumber = rs.getString("studioPhoneNumber");
                    String studioEmail = rs.getString("studioEmail");
                    boolean status = rs.getBoolean("status");

                    DateFormat format = new SimpleDateFormat("H:mm");

                    Time ftimeStart = rs.getTime("timeStart");
                    String timeStart = format.format(ftimeStart);

                    Time ftimeEnd = rs.getTime("timeEnd");
                    String timeEnd = format.format(ftimeEnd);

                    String description = rs.getString("description");
                    int studioSlotTime = rs.getInt("studioSlotTime");
                    if (status == true) {
                        list.add(new StudioDTO(studioID, managerID, studioName, studioAddress,
                                city, district, studioPhoneNumber, studioEmail, status, timeStart, timeEnd, description, studioSlotTime));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return list;
    }

    /*sort danh sách studio theo các option về dịch vụ khác nhau*/
    public List<StudioDTO> getSortStudioList(String serviceID, String serviceDetailID, String cityID, String sRating, String studioSearch) throws SQLException {
        List<StudioDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sort = "SELECT distinct s.studioID, managerID, studioName, studioAddress, c.cityName, d.districtName, studioPhoneNumber, studioEmail, s.status, timeStart, timeEnd, description, studioSlotTime FROM Studio s, City c, District d, Studio_Service ss WHERE s.cityID = c.cityID AND s.districtID = d.districtID";
                if (serviceID != null) {
                    sort = sort + " AND ss.studioID = s.studioID AND ss.serviceID=" + "'" + serviceID + "'";
                }

                if (serviceDetailID != null && serviceID != null) {
                    sort = sort + " AND ss.serviceDetailID=" + "'" + serviceDetailID + "'";
                } else if (serviceDetailID != null && serviceID == null) {
                    sort = sort + " AND ss.studioID = s.studioID AND ss.serviceDetailID=" + "'" + serviceDetailID + "'";
                }

                if (cityID != null) {
                    sort = sort + " AND s.cityID=" + "'" + cityID + "'";
                }
                
                if (studioSearch.length() > 0) {
                    sort = sort + " AND studioName like " + "'%" + studioSearch + "%'";
                }

                if (sRating != null) {
                    int rating = Integer.parseInt(sRating);
                    sort = sort + " AND s.studioID in (SELECT b.studioID FROM Booking b, Studio s WHERE b.studioID = s.studioID AND b.rating > 0 GROUP BY b.studioID HAVING AVG(b.rating) >= " + rating + ")";
                }

                ptm = conn.prepareStatement(sort);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String studioID = rs.getString("studioID");
                    String managerID = rs.getString("managerID");
                    String studioName = rs.getString("studioName");
                    String studioAddress = rs.getString("studioAddress");
                    String city = rs.getString("cityName");
                    String district = rs.getString("districtName");
                    String studioPhoneNumber = rs.getString("studioPhoneNumber");
                    String studioEmail = rs.getString("studioEmail");
                    boolean status = rs.getBoolean("status");

                    DateFormat format = new SimpleDateFormat("H:mm");

                    Time ftimeStart = rs.getTime("timeStart");
                    String timeStart = format.format(ftimeStart);

                    Time ftimeEnd = rs.getTime("timeEnd");
                    String timeEnd = format.format(ftimeEnd);

                    String description = rs.getString("description");
                    int studioSlotTime = rs.getInt("studioSlotTime");
                    if (status == true) {
                        list.add(new StudioDTO(studioID, managerID, studioName, studioAddress,
                                city, district, studioPhoneNumber, studioEmail, status, timeStart, timeEnd, description, studioSlotTime));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    /*lấy danh sách các studio có dịch vụ A bằng serviceID*/
    public List<StudioDTO> getListStudioByService(String serviceID) throws SQLException {
        List<StudioDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_STUDIO_BY_SERVICE);
                ptm.setString(1, serviceID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String studioID = rs.getString("studioID");
                    String managerID = rs.getString("managerID");
                    String studioName = rs.getString("studioName");
                    String studioAddress = rs.getString("studioAddress");
                    String city = rs.getString("cityName");
                    String district = rs.getString("districtName");
                    String studioPhoneNumber = rs.getString("studioPhoneNumber");
                    String studioEmail = rs.getString("studioEmail");
                    boolean status = rs.getBoolean("status");

                    DateFormat format = new SimpleDateFormat("H:mm");

                    Time ftimeStart = rs.getTime("timeStart");
                    String timeStart = format.format(ftimeStart);

                    Time ftimeEnd = rs.getTime("timeEnd");
                    String timeEnd = format.format(ftimeEnd);

                    String description = rs.getString("description");
                    int studioSlotTime = rs.getInt("studioSlotTime");
                    if (status == true) {
                        list.add(new StudioDTO(studioID, managerID, studioName, studioAddress,
                                city, district, studioPhoneNumber, studioEmail, status, timeStart, timeEnd, description, studioSlotTime));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return list;
    }

    /*lấy danh sách các studio có chi tiết dịch vụ A bằng serviceID*/
    public List<StudioDTO> getListStudioByServiceDetail(String serviceID) throws SQLException {
        List<StudioDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_STUDIO_BY_SERVICE_DETAIL);
                ptm.setString(1, serviceID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String studioID = rs.getString("studioID");
                    String managerID = rs.getString("managerID");
                    String studioName = rs.getString("studioName");
                    String studioAddress = rs.getString("studioAddress");
                    String city = rs.getString("cityName");
                    String district = rs.getString("districtName");
                    String studioPhoneNumber = rs.getString("studioPhoneNumber");
                    String studioEmail = rs.getString("studioEmail");
                    boolean status = rs.getBoolean("status");

                    DateFormat format = new SimpleDateFormat("H:mm");

                    Time ftimeStart = rs.getTime("timeStart");
                    String timeStart = format.format(ftimeStart);

                    Time ftimeEnd = rs.getTime("timeEnd");
                    String timeEnd = format.format(ftimeEnd);

                    String description = rs.getString("description");
                    int studioSlotTime = rs.getInt("studioSlotTime");
                    if (status == true) {
                        list.add(new StudioDTO(studioID, managerID, studioName, studioAddress,
                                city, district, studioPhoneNumber, studioEmail, status, timeStart, timeEnd, description, studioSlotTime));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return list;
    }

    /*Tìm số artist của 1 studio bằng studioID*/
    public int checkAmountArtistStudio(String studioID) throws SQLException {
        int amount = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_AMOUNT_ARTIST);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    amount = rs.getInt("amountArtist");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

            return amount;
        }
    }

    public List<RatingDTO> getRatingStudioList(String studioID) throws SQLException {
        List<RatingDTO> ratingList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_RATING_STUDIO);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    float rating = rs.getFloat("rating");
                    ratingList.add(new RatingDTO(studioID, rating));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

            return ratingList;
        }
    }

    public RatingDTO getRatingStudio(String studioID) throws SQLException {
        RatingDTO ratingStu = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_RATING_STUDIO);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    float rating = rs.getFloat("rating");
                    ratingStu = new RatingDTO(studioID, rating);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

            return ratingStu;
        }
    }

    public String getStuIDByUserID(String userID) throws SQLException {
        String studioID = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STUID_BY_USERID);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    studioID = rs.getString("studioID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

            return studioID;
        }
    }

    public List<UserDTO> getArtistAvailableList(String studioID, String bookDate, String slotID) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ARTIST_AVAILABLE);
                ptm.setString(1, studioID);
                ptm.setString(2, studioID);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = dateFormat.parse(bookDate);
                dateFormat.applyPattern("yyyy-MM-dd");
                String formattedDate = dateFormat.format(date);

                ptm.setString(3, formattedDate);
                ptm.setString(4, slotID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String artistID = rs.getString("userID");
                    String artistName = rs.getString("fullname");
                    list.add(new UserDTO(artistID, null, null, null, artistName, null, null, null, null, null, null, true, null));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

            return list;
        }
    }

    public static StudioDTO getStudioByID(String studioID) throws SQLException {
        StudioDTO studio = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STUDIO_INFOR);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String managerID = rs.getString("managerID");
                    String studioName = rs.getString("studioName");
                    String studioAddress = rs.getString("studioAddress");
                    String city = rs.getString("cityName");
                    String district = rs.getString("districtName");
                    String studioPhoneNumber = rs.getString("studioPhoneNumber");
                    String studioEmail = rs.getString("studioEmail");
                    boolean status = rs.getBoolean("status");
                    int rating = rs.getInt("rating");

                    DateFormat format = new SimpleDateFormat("H:mm");

                    Time ftimeStart = rs.getTime("timeStart");
                    String timeStart = format.format(ftimeStart);

                    Time ftimeEnd = rs.getTime("timeEnd");
                    String timeEnd = format.format(ftimeEnd);

                    String description = rs.getString("description");
                    int availablePerSlot = rs.getInt("timePerSlot");
                    if (status == true) {
                        studio = new StudioDTO(city, managerID, studioName, studioAddress, city, district, studioPhoneNumber, studioEmail, status, timeStart, timeEnd, description, availablePerSlot);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return studio;
    }

    public static String getRandomStudioId() {
        String studioId;
        Random rand = new Random();
        int int_random = rand.nextInt(1000000000);
        String followInt = String.format("%06d", int_random);
        studioId = "ST" + followInt;
        return studioId;
    }

    public static String getNewStudioId() throws Exception {
        String studioID;
        do {
            studioID = getRandomStudioId();
        } while (getStudioByID(studioID) != null);
        return studioID;
    }

    public static boolean addStudio(StudioDTO studio) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;

        try {
            conn = DBUtils.getConnection();

            String insertSQL = "INSERT INTO Studio (studioID, managerID, studioName, studioAddress, cityID, districtID, studioPhoneNumber, studioEmail, [status], timeStart, timeEnd, description, studioSlotTime) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(insertSQL);

            pstmt.setString(1, studio.getId());
            pstmt.setString(2, studio.getMangerID());
            pstmt.setString(3, studio.getName());
            pstmt.setString(4, studio.getAddress());
            pstmt.setString(5, studio.getCity());
            pstmt.setString(6, studio.getDistrict());
            pstmt.setString(7, studio.getPhoneNumber());
            pstmt.setString(8, studio.getEmail());
            pstmt.setBoolean(9, studio.isStatus());
            pstmt.setString(10, studio.getTimeStart());
            pstmt.setString(11, studio.getTimeEnd());
            pstmt.setString(12, studio.getDescription());
            pstmt.setInt(13, studio.getStudioSlotTime());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return success;
    }

    //    "UPDATE [Studio] SET studioPhoneNumber=?,studioEmail=?, timeStart=?, timeEnd=?, studioSlotTime=?, decription=? WHERE studioID=?";
    public boolean UpdateStudio(String studioID, String phoneNum, String timeStart, String timeEnd, String email, int time1slot, String decription) throws SQLException {
        RatingDTO ratingStu = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean isUpdate = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_STUDIO);
                ptm.setString(1, phoneNum);
                ptm.setString(2, email);
                ptm.setString(3, timeStart);
                ptm.setString(4, timeEnd);
                ptm.setInt(5, time1slot);
                ptm.setString(6, decription);
                ptm.setString(7, studioID);
                isUpdate = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return isUpdate;
    }

    public boolean deleteStudio(String userId) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_STUDIO);
                ptm.setString(1, userId);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

}
