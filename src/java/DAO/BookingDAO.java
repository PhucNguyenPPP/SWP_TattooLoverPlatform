/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.BookingDTO;
import DTO.BookingDetailDTO;
import DTO.StudioServiceDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class BookingDAO {

    private static final String GET_SERVICE_BOOKING = "SELECT distinct ssv.studioServiceID, "
            + "s.serviceName, sd.serviceDetailName, ss.serviceSizeName, price, ssv.status "
            + "FROM Studio_Service ssv, Service s, Service_Detail sd, Service_Size ss "
            + "WHERE studioID = ? "
            + "AND ssv.serviceID = ? "
            + "AND ssv.serviceDetailID = ? "
            + "AND ssv.serviceSizeID= ? "
            + "AND ssv.serviceID = s.serviceID "
            + "AND ssv.serviceDetailID = sd.serviceDetailID "
            + "AND ssv.serviceSizeID = ss.serviceSizeID";

    private static final String GET_BOOKING_SERVICE_AMOUNT = "SELECT count(bs.bookingID) as amountBookingService "
            + "FROM Booking b, Booking_StudioService bs "
            + "WHERE b.bookingID = bs.bookingID "
            + "AND bookingDate = ? "
            + "AND studioID = ? "
            + "AND slotID = ? "
            + "AND bs.status = 1";

    private static final String CHECK_BOOKING_ID = "SELECT bookingID FROM Booking";

    private static final String INSERT_BOOKING = "INSERT INTO Booking "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String CHECK_BOOKINGSTUSERVICE_ID = "SELECT bookingStuServiceID FROM Booking_StudioService";

    private static final String INSERT_BOOKING_SERVICE = "INSERT INTO Booking_StudioService "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String BOOKING_LIST = "SELECT bookingID, b.studioID, b.fullName, b.phoneNumber, "
            + "s.studioName, b.slotID, sl.timeStart, sl.timeEnd, b.totalPrice, b.description, "
            + "currentDate, bookingDate, b.rating, commentation, b.status "
            + "FROM Booking b, Slot sl, Studio s "
            + "WHERE userID=? "
            + "AND b.slotID = sl.slotID "
            + "AND b.studioID = s.studioID "
            + "ORDER BY currentDate DESC";
    private static final String GET_BOOKING = "SELECT bookingID, b.userID, u.fullname, b.studioID, b.fullName, b.phoneNumber, "
            + "s.studioName, b.slotID, sl.timeStart, sl.timeEnd, b.totalPrice, b.description, "
            + "currentDate, bookingDate, b.rating, commentation, b.status "
            + "FROM Booking b, Slot sl, Studio s, [User] u "
            + "WHERE bookingID=? "
            + "AND b.slotID = sl.slotID "
            + "AND b.studioID = s.studioID "
            + "AND b.userID = u.userID";
    private static final String GET_BOOKING_DETAIL_INPROGRESS = "SELECT distinct bs.bookingStuServiceID, s.serviceName, "
            + "ss.serviceSizeName, sd.serviceDetailName, ssv.price, bs.amount "
            + "FROM  Booking_StudioService bs, Studio_Service ssv, [User] u, "
            + "Service s, Service_Detail sd, Service_Size ss "
            + "WHERE bs.bookingID = ? "
            + "AND bs.studioServiceID = ssv.studioServiceID "
            + "AND ssv.serviceID = s.serviceID "
            + "AND ssv.serviceDetailID = sd.serviceDetailID "
            + "AND ssv.serviceSizeID = ss.serviceSizeID";
    
     private static final String GET_BOOKING_DETAIL = "SELECT distinct bs.bookingStuServiceID, s.serviceName, "
            + "ss.serviceSizeName, sd.serviceDetailName, ssv.price, bs.amount, "
            + "bs.artistID, u.fullname "
            + "FROM  Booking_StudioService bs, Studio_Service ssv, [User] u, "
            + "Service s, Service_Detail sd, Service_Size ss "
            + "WHERE bs.bookingID = ? "
            + "AND bs.studioServiceID = ssv.studioServiceID "
            + "AND ssv.serviceID = s.serviceID "
            + "AND ssv.serviceDetailID = sd.serviceDetailID "
            + "AND ssv.serviceSizeID = ss.serviceSizeID "
            + "AND bs.artistID = u.userID";

    private static final String INSERT_FEEDBACK = "UPDATE Booking "
            + "SET rating = ?, "
            + "commentation = ? "
            + "WHERE bookingID = ?";

    private static final String GET_CMT_FEEDBACK_STU = "SELECT bookingID, u.fullname, b.rating, b.commentation "
            + "FROM Booking b, [User] u "
            + "WHERE b.userID = u.userID "
            + "AND b.studioID = ? "
            + "AND b.status = 'Hoàn thành' "
            + "AND b.rating != 0";

    private static final String GET_BOOKINGLIST_BY_STU_ID = "SELECT b.bookingID, "
            + "b.userID, u.fullname, b.studioID, s.studioName, b.slotID, "
            + "sl.timeStart, sl.timeEnd, b.totalPrice, b.description, b.currentDate, "
            + "b.bookingDate, b.rating, b.commentation, b.status "
            + "FROM Booking b, [User] u, Studio s, Slot sl "
            + "WHERE b.studioID = ? "
            + "AND b.userID = u.userID "
            + "AND b.studioID = s.studioID "
            + "AND b.slotID = sl.slotID "
            + "ORDER BY currentDate DESC";

    private static final String ASSIGN_ARTIST = "UPDATE Booking_StudioService "
            + "SET artistID = ? "
            + "WHERE bookingStuServiceID = ?";

    private static final String SET_INSPECTED_BOOKING = "UPDATE Booking "
            + "SET status = ?, "
            + "studioStaffID = ? "
            + "WHERE bookingID = ?";
    
    private static final String SET_FINISHED_BOOKING = "UPDATE Booking "
            + "SET status = ?, "
            + "studioStaffID = ? "
            + "WHERE bookingID = ?";
    
    private static final String GET_BOOKINGLIST_ARTIST = "SELECT b.bookingID, "
            + "b.userID, u.fullname, b.slotID, sl.timeStart, sl.timeEnd, b.currentDate, "
            + "b.bookingDate, b.status "
            + "FROM Booking b, [User] u, Slot sl, Booking_StudioService bs "
            + "WHERE bs.artistID = ? "
            + "AND b.bookingID = bs.bookingID "
            + "AND b.userID = u.userID "
            + "AND b.slotID = sl.slotID  "
            + "ORDER BY currentDate DESC";

    public StudioServiceDTO getServiceBooking(String studioID, String serviceID, String serviceDetailID, String serviceSizeID, String sAmount) throws SQLException {
        StudioServiceDTO stuService = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SERVICE_BOOKING);
                ptm.setString(1, studioID);
                ptm.setString(2, serviceID);
                ptm.setString(3, serviceDetailID);
                ptm.setString(4, serviceSizeID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String studioServiceID = rs.getString("studioServiceID");
                    String serviceName = rs.getString("serviceName");
                    String serviceDetailName = rs.getString("serviceDetailName");
                    String serviceSizeName = rs.getString("serviceSizeName");
                    int price = rs.getInt("price");
                    int amount = Integer.parseInt(sAmount);
                    boolean status = rs.getBoolean("status");
                    if (status == true) {
                        stuService = (new StudioServiceDTO(studioServiceID, studioID,
                                serviceID, serviceName, serviceDetailID,
                                serviceDetailName, serviceSizeID, serviceSizeName,
                                null, price, amount, status));
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

            return stuService;
        }
    }


    /*check xem trong ngày đó slot đó số đơn đặt hàng là bao nhiêu*/
    public int getAmountBookingServiceInSlot(String studioID, String slotID, String bookDate) throws SQLException {
        int amount = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BOOKING_SERVICE_AMOUNT);
                ptm.setString(1, bookDate);
                ptm.setString(2, studioID);
                ptm.setString(3, slotID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    amount = rs.getInt("amountBookingService");
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
    public static String bookingID = "";

    public static boolean insertBooking(String userID, String fullName, String phoneNumber, String studioID, String slotID,
            int totalPrice, String description, String currentDate, String bookingDate) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                Random rd = new Random();

                boolean bookingIDDuplicated;
                do {
                    bookingIDDuplicated = false;
                    int number_booking = rd.nextInt(1000);
                    bookingID = "B" + number_booking;
                    ptm = conn.prepareStatement(CHECK_BOOKING_ID);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        String bookingID_check = rs.getString("bookingID");
                        if (bookingID.equals(bookingID_check)) {
                            bookingIDDuplicated = true;
                            break;
                        }
                    }
                } while (bookingIDDuplicated == true);

                ptm = conn.prepareStatement(INSERT_BOOKING);
                ptm.setString(1, bookingID);
                ptm.setString(2, userID);
                ptm.setNString(3, fullName);
                ptm.setString(4, phoneNumber);
                ptm.setString(5, studioID);
                ptm.setString(6, null);
                ptm.setString(7, slotID);
                ptm.setInt(8, totalPrice);
                ptm.setString(9, description);
                ptm.setString(10, currentDate);
                ptm.setString(11, bookingDate);
                ptm.setInt(12, 0);
                ptm.setString(13, null);
                ptm.setString(14, "Đang xử lý");
                check = ptm.executeUpdate() > 0 ? true : false;
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
        return check;
    }

    public static String getBookingID() {
        return bookingID;
    }

    public boolean insertBookingService(String bookingID, String studioServiceID, int amount) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();

            Random rd = new Random();
            boolean bookingStuServiceIDDuplicated;
            String bookingStuServiceID = "";
            do {
                bookingStuServiceIDDuplicated = false;
                int number = rd.nextInt(10000);
                bookingStuServiceID = "BBS" + number;
                ptm = conn.prepareStatement(CHECK_BOOKINGSTUSERVICE_ID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookingStuServiceID_check = rs.getString("bookingStuServiceID");
                    if (bookingStuServiceID.equals(bookingStuServiceID_check)) {
                        bookingStuServiceIDDuplicated = true;
                        break;
                    }
                }
            } while (bookingStuServiceIDDuplicated == true);

            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_BOOKING_SERVICE);
                ptm.setString(1, bookingStuServiceID);
                ptm.setString(2, bookingID);
                ptm.setString(3, studioServiceID);
                ptm.setString(4, null);
                ptm.setInt(5, amount);
                ptm.setBoolean(6, true);
                check = ptm.executeUpdate() > 0 ? true : false;
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
        return check;
    }

    public List<BookingDTO> getListBookingUser(String userID) throws SQLException {
        List<BookingDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(BOOKING_LIST);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("bookingID");
                    String studioID = rs.getString("studioID");
                    String fullName = rs.getString("fullName");
                    String phoneNumber = rs.getString("phoneNumber");
                    String studioName = rs.getString("studioName");
                    String slotID = rs.getString("slotID");

                    DateFormat format = new SimpleDateFormat("H:mm");
                    Time ftimeStart = rs.getTime("timeStart");
                    String timeStart = format.format(ftimeStart);

                    Time ftimeEnd = rs.getTime("timeEnd");
                    String timeEnd = format.format(ftimeEnd);

                    int totalPrice = rs.getInt("totalPrice");
                    String description = rs.getString("description");

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date fCurrentDate = rs.getDate("currentDate");
                    String currentDate = formatter.format(fCurrentDate);

                    Date fBookingDate = rs.getDate("bookingDate");
                    String bookingDate = formatter.format(fBookingDate);

                    int rating = rs.getInt("rating");
                    String commentation = rs.getString("commentation");
                    String status = rs.getString("status");
                    list.add(new BookingDTO(bookingID, userID, null, fullName, phoneNumber, studioID, studioName, null, slotID, timeStart, timeEnd, totalPrice, description, currentDate, bookingDate, rating, commentation, status));
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

    /*lay 1 booking bằng bookingID*/
    public BookingDTO getBooking(String bookingID) throws SQLException {
        BookingDTO booking = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(GET_BOOKING);
                ptm.setString(1, bookingID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String userFullName = rs.getString("fullname");
                    String fullName = rs.getString("fullName");
                    String phoneNumber = rs.getString("phoneNumber");

                    String studioID = rs.getString("studioID");
                    String studioName = rs.getString("studioName");
                    String slotID = rs.getString("slotID");

                    DateFormat format = new SimpleDateFormat("H:mm");
                    Time ftimeStart = rs.getTime("timeStart");
                    String timeStart = format.format(ftimeStart);

                    Time ftimeEnd = rs.getTime("timeEnd");
                    String timeEnd = format.format(ftimeEnd);

                    int totalPrice = rs.getInt("totalPrice");
                    String description = rs.getString("description");

                    SimpleDateFormat formatterCurrentDate = new SimpleDateFormat("dd-MM-yyyy | H:mm");
                    SimpleDateFormat formatterBookDate = new SimpleDateFormat("dd-MM-yyyy");
                    Date fCurrentDate = rs.getTimestamp("currentDate");
                    String currentDate = formatterCurrentDate.format(fCurrentDate);

                    Date fBookingDate = rs.getDate("bookingDate");
                    String bookingDate = formatterBookDate.format(fBookingDate);

                    int rating = rs.getInt("rating");
                    String commentation = rs.getString("commentation");
                    String status = rs.getString("status");
                    booking = new BookingDTO(bookingID, userID, userFullName, fullName, phoneNumber, studioID, studioName, null, slotID, timeStart, timeEnd, totalPrice, description, currentDate, bookingDate, rating, commentation, status);
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
        return booking;
    }

    public List<BookingDetailDTO> getListBookingDetailInProgess(String bookingID) throws SQLException {
        List<BookingDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BOOKING_DETAIL_INPROGRESS);
                ptm.setString(1, bookingID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookingStuServiceID = rs.getString("bookingStuServiceID");
                    String serviceName = rs.getString("serviceName");
                    String serviceDeatailName = rs.getString("serviceDetailName");
                    String serviceSizeName = rs.getString("serviceSizeName");
                    int price = rs.getInt("price");
                    int amount = rs.getInt("amount");

                    list.add(new BookingDetailDTO(bookingStuServiceID, bookingID, null, null, serviceName, null, serviceDeatailName, null, serviceSizeName, price, amount, null, null));
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
    
    public List<BookingDetailDTO> getListBookingDetail(String bookingID) throws SQLException {
        List<BookingDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BOOKING_DETAIL);
                ptm.setString(1, bookingID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookingStuServiceID = rs.getString("bookingStuServiceID");
                    String serviceName = rs.getString("serviceName");
                    String serviceDeatailName = rs.getString("serviceDetailName");
                    String serviceSizeName = rs.getString("serviceSizeName");
                    int price = rs.getInt("price");
                    int amount = rs.getInt("amount");
                    String artistID = rs.getString("artistID");
                    String artistName = rs.getString("fullname");

                    list.add(new BookingDetailDTO(bookingStuServiceID, bookingID, null, null, serviceName, null, serviceDeatailName, null, serviceSizeName, price, amount, artistID, artistName));
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

    public boolean insertFeedback(String rate, String comment, String bookingID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_FEEDBACK);
                ptm.setString(1, rate);
                ptm.setString(2, comment);
                ptm.setString(3, bookingID);
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

    public List<BookingDTO> getCmtFeebackStudio(String studioID) throws SQLException {
        List<BookingDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CMT_FEEDBACK_STU);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("bookingID");
                    String userFullName = rs.getString("fullname");
                    int rating = rs.getInt("rating");
                    String commentation = rs.getString("commentation");
                    list.add(new BookingDTO(bookingID, null, userFullName, null, null, studioID, null, null, null, null, null, 0, null, null, null, rating, commentation, null));
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

    public List<BookingDTO> getBookingListStu(String studioID) throws SQLException {
        List<BookingDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BOOKINGLIST_BY_STU_ID);
                ptm.setString(1, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("bookingID");
                    String userID = rs.getString("userID");
                    String userFullName = rs.getString("fullname");
                    String studioName = rs.getString("studioName");
                    String slotID = rs.getString("slotID");

                    DateFormat format = new SimpleDateFormat("H:mm");
                    Time ftimeStart = rs.getTime("timeStart");
                    String timeStart = format.format(ftimeStart);

                    Time ftimeEnd = rs.getTime("timeEnd");
                    String timeEnd = format.format(ftimeEnd);

                    int totalPrice = rs.getInt("totalPrice");
                    String description = rs.getString("description");

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date fCurrentDate = rs.getDate("currentDate");
                    String currentDate = formatter.format(fCurrentDate);

                    Date fBookingDate = rs.getDate("bookingDate");
                    String bookingDate = formatter.format(fBookingDate);

                    int rating = rs.getInt("rating");
                    String commentation = rs.getString("commentation");
                    String status = rs.getString("status");
                    list.add(new BookingDTO(bookingID, userID, userFullName, null, null, studioID, studioName, null, slotID, timeStart, timeEnd, totalPrice, description, currentDate, bookingDate, rating, commentation, status));
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

    public boolean assignArtist(String bookingStudioServiceID, String artistID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ASSIGN_ARTIST);
                ptm.setString(1, artistID);
                ptm.setString(2, bookingStudioServiceID);
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

    public boolean setInspectedBooking(String bookingID, String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SET_INSPECTED_BOOKING);
                ptm.setNString(1, "Đã duyệt");
                ptm.setNString(2, userID);
                ptm.setString(3, bookingID);
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

    public boolean setFinishedBooking(String bookingID, String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SET_FINISHED_BOOKING);
                ptm.setNString(1, "Hoàn thành");
                ptm.setNString(2, userID);
                ptm.setString(3, bookingID);
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

    public List<BookingDTO> getBookingListArtist(String artistID) throws SQLException {
         List<BookingDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BOOKINGLIST_ARTIST);
                ptm.setString(1, artistID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookingID = rs.getString("bookingID");
                    String userID = rs.getString("userID");
                    String userFullName = rs.getString("fullname");
                    String slotID = rs.getString("slotID");

                    DateFormat format = new SimpleDateFormat("H:mm");
                    Time ftimeStart = rs.getTime("timeStart");
                    String timeStart = format.format(ftimeStart);

                    Time ftimeEnd = rs.getTime("timeEnd");
                    String timeEnd = format.format(ftimeEnd);

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    Date fCurrentDate = rs.getDate("currentDate");
                    String currentDate = formatter.format(fCurrentDate);

                    Date fBookingDate = rs.getDate("bookingDate");
                    String bookingDate = formatter.format(fBookingDate);

                    String status = rs.getString("status");
                    list.add(new BookingDTO(bookingID, userID, userFullName, null, null, null, null, null, slotID, timeStart, timeEnd, 0, null, currentDate, bookingDate, 0, null, status));
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
}
