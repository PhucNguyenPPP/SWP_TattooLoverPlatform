/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.NotificationDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class NotificationDAO {

    private static final String INSERT_NOTICE_CREATE_BOOKING = "INSERT INTO Notification "
            + "VALUES (?, ?, ?, ?)";
    private static final String GET_NOTICE_LIST = "SELECT * FROM Notification "
            + "WHERE userID = ? "
            + "ORDER BY currentDate DESC";
      private static final String INSERT_NOTICE_DELETE_BOOKING = "INSERT INTO Notification "
            + "VALUES (?, ?, ?, ?)";
    public boolean insertNoticeAfterCreateBooking(String userID, String currentDate, String noticeDescription) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_NOTICE_CREATE_BOOKING);
                ptm.setString(1, userID);
                ptm.setString(2, noticeDescription);
                ptm.setString(3, currentDate);
                ptm.setBoolean(4, true);
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

    public List<NotificationDTO> getNoticeList(String userID) throws SQLException {
        List<NotificationDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_NOTICE_LIST);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String description = rs.getString("description");
                    SimpleDateFormat formatterCurrentDate = new SimpleDateFormat("dd-MM-yyyy H:mm");     
                    Date fCurrentDate = rs.getTimestamp("currentDate");
                    String currentDate = formatterCurrentDate.format(fCurrentDate);
                    list.add(new NotificationDTO(userID, null, description, currentDate, null));
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

    public boolean insertAfterDelete(String userID, String noticeDescription, String currentDate) throws SQLException {
         boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_NOTICE_DELETE_BOOKING);
                ptm.setString(1, userID);
                ptm.setString(2, noticeDescription);
                ptm.setString(3, currentDate);
                ptm.setBoolean(4, true);
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
