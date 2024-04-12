/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.UserDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class UserDAO {

    private static final String DELETE_BOOKING_TL = "UPDATE Booking_StudioService SET status = 0 WHERE bookingID = ?; "
            + "UPDATE Booking SET status = N'Đã hủy' WHERE bookingID = ?;";

    private static final String USER_LIST_STU = "SELECT [userId], userAcccount, "
            + "fullname, birthday, gender, roleID, phoneNumber, d.districtName, "
            + "c.cityName, u.studioID FROM [User] u, City c, District d "
            + "WHERE u.cityID = c.cityID "
            + "AND d.districtID = u.districtID "
            + "AND u.studioID = ? "
            + "AND u.status = 1";

    private static final String SEARCH_ACCOUNT_STU = "SELECT [userId], userAcccount, "
            + "fullname, birthday, gender, roleID, phoneNumber, d.districtName, c.cityName "
            + "FROM [User] u, City c, District d "
            + "WHERE u.cityID = c.cityID "
            + "AND d.districtID = u.districtID "
            + "AND fullName like ? "
            + "AND u.studioID = ? "
            + "AND u.status = 1";

    private static final String USER_LIST = "SELECT [userId], userAcccount, "
            + "fullname, birthday, gender, roleID, phoneNumber, d.districtName, "
            + "c.cityName FROM [User] u, City c, District d "
            + "WHERE u.cityID = c.cityID "
            + "AND d.districtID = u.districtID "
            + "AND u.status = 1";

    private static final String SEARCH_ACCOUNT_SYSTEM = "SELECT [userId], userAcccount, "
            + "fullname, birthday, gender, roleID, phoneNumber, d.districtName, c.cityName "
            + "FROM [User] u, City c, District d "
            + "WHERE u.cityID = c.cityID "
            + "AND d.districtID = u.districtID "
            + "AND fullName like ? "
            + "AND u.status = 1";
<<<<<<< HEAD

    private static final String DELETE_USER = "UPDATE [User] "
            + "SET [User].status=0"
            + " WHERE [User].userID=?";
    private static final String UPDATE_PASSWORD_BY_EMAIL = "UPDATE [User] "
            + "SET password = ? "
            + "WHERE email = ?";
=======
    
    private static final String DELETE_USER = "UPDATE [User] " 
                                               + "SET [User].status=0"
                                                + " WHERE [User].userID=?";
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722

    public static UserDTO getUserByUserAccount(String userAccount) throws Exception {
        UserDTO us = null;
        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "select [userID],[userAcccount],[password],[roleID],[fullname],[birthday],[gender],[phoneNumber]"
                    + ",[email],[cityID],[districtID],[status],[studioID] from [dbo].[User] where [userAcccount] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userAccount);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String id = rs.getString("userID");
                    String thisUserAccount = rs.getString("userAcccount");
                    String password = rs.getString("password");
                    String roleId = rs.getString("roleID");
                    String fullname = rs.getNString("fullname");
                    String birthday = rs.getString("birthday");
                    String gender = rs.getString("gender");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    String city = rs.getString("cityID");
                    String district = rs.getString("districtID");
                    boolean status = rs.getBoolean("status");
                    String studioId = rs.getString("studioID");
                    if (status == true) {
                        us = new UserDTO(id, thisUserAccount, password, roleId, fullname, birthday, gender, phoneNumber, email, city, district, status, studioId);
                    }
<<<<<<< HEAD

=======
                    
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
                }
            }
            cn.close();
        }
        return us;
    }

    public static UserDTO getUserByUserId(String userId) throws Exception {
        UserDTO us = null;
        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "select [userID],[userAcccount],[password],[roleID],[fullname],[birthday],[gender],[phoneNumber]"
                    + ",[email],[cityID],[districtID],[status],[studioID] from [dbo].[User] where [userID] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userId);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String id = rs.getString("userID");
                    String thisUserAccount = rs.getString("userAcccount");
                    String password = rs.getString("password");
                    String roleId = rs.getString("roleID");
                    String fullname = rs.getString("fullname");
                    String birthday = rs.getString("birthday");
                    String gender = rs.getString("gender");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
<<<<<<< HEAD
                    String city = rs.getString("cityID");
                    String district = rs.getString("districtID");
=======
                    String city = rs.getString("city");
                    String district = rs.getString("district");
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
                    byte status = rs.getByte("status");
                    boolean thisStatus = true;
                    if (status == 1) {
                        thisStatus = true;
                    }
                    String studioId = rs.getString("studioID");
                    us = new UserDTO(id, thisUserAccount, password, roleId, fullname, birthday, gender, phoneNumber, email, city, district, thisStatus, studioId);
                }
            }
            cn.close();
        }
        return us;
    }

    public static UserDTO createUser(String Id, String userAccount, String password, String roleId, String fullname, String birthday, String gender, String phoneNumber, String email, String city, String district, boolean status, String studioId) throws Exception {
        UserDTO us = new UserDTO(Id, userAccount, password, roleId, fullname, birthday, gender, phoneNumber, email, city, district, status, studioId);
        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "Insert Into [dbo].[User]([userID],[userAcccount],[password],[roleID],[fullname],[birthday],[gender],[phoneNumber],[email],[cityID],[districtID],[status],[studioID]) "
                    + "Values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, Id);
            pst.setString(2, userAccount);
            pst.setString(3, password);
            pst.setString(4, roleId);
            pst.setString(5, fullname);
            pst.setString(6, birthday);
            pst.setString(7, gender);
            pst.setString(8, phoneNumber);
            pst.setString(9, email);
            pst.setString(10, city);
            pst.setString(11, district);
            pst.setInt(12, 1);
            pst.setString(13, studioId);
            pst.executeUpdate();
            cn.close();
        }
        return us;
    }

    public static String getRandomUserId(String roleId) {
        String userId;
        Random rand = new Random();
        int int_random = rand.nextInt(1000000000);
        String followInt = String.format("%06d", int_random);
        userId = roleId + followInt;
        return userId;
    }

    public static boolean emailIsUnique(String email) throws Exception {
        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "Select [email] from [dbo].[User] where [email] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String checkEmail = rs.getString("email");
                    if (checkEmail.equals(email)) {
                        return false;
                    }
                }
            }
            cn.close();
        }
        return true;
    }

    public static boolean phoneIsUnique(String phone) throws Exception {
        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "Select [phoneNumber] from [dbo].[User] where [phoneNumber] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, phone);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String checkPhone = rs.getString("phoneNumber");
                    if (checkPhone.equals(phone)) {
                        return false;
                    }
                }
            }
            cn.close();
        }
        return true;
    }

    public boolean deleteBookingTL(String bookingID) throws SQLException {
        boolean checkDelete = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_BOOKING_TL);
                ptm.setString(1, bookingID);
                ptm.setString(2, bookingID);
                checkDelete = ptm.executeUpdate() > 0 ? true : false;
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
        return checkDelete;
    }

    public List<UserDTO> getListAccountStu(String sId) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(USER_LIST_STU);
                ptm.setString(1, sId);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("userID");
                    String user = rs.getString("userAcccount");
                    String fullname = rs.getString("fullname");

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
                    Date birthday = rs.getDate("birthday");
                    String birthdays = formatter.format(birthday);

                    String gender = rs.getString("gender");
                    String roleId = rs.getString("roleId");
                    String phoneNumber = rs.getString("phoneNumber");
                    String District = rs.getString("districtName");
                    String city = rs.getString("cityName");
                    String studioId = rs.getString("studioID");
                    list.add(new UserDTO(id, user, null, roleId, fullname, birthdays, gender, phoneNumber, null, city, District, true, studioId));

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

    public List<UserDTO> searchAccountStu(String search, String studioID) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_ACCOUNT_STU);
                ptm.setNString(1, "%" + search + "%");
                ptm.setString(2, studioID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("userID");
                    String user = rs.getString("userAcccount");
                    String fullname = rs.getString("fullname");

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
                    Date birthday = rs.getDate("birthday");
                    String birthdays = formatter.format(birthday);

                    String gender = rs.getString("gender");
                    String roleId = rs.getString("roleId");
                    String phoneNumber = rs.getString("phoneNumber");
                    String District = rs.getString("districtName");
                    String city = rs.getString("cityName");

                    list.add(new UserDTO(id, user, null, roleId, fullname, birthdays, gender, phoneNumber, null, city, District, true, null));
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

    public List<UserDTO> getListAccountSystem() throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(USER_LIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("userID");
                    String user = rs.getString("userAcccount");
                    String fullname = rs.getString("fullname");

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
                    Date birthday = rs.getDate("birthday");
                    String birthdays = formatter.format(birthday);

                    String gender = rs.getString("gender");
                    String roleId = rs.getString("roleId");
                    String phoneNumber = rs.getString("phoneNumber");
                    String District = rs.getString("districtName");
                    String city = rs.getString("cityName");

                    list.add(new UserDTO(id, user, null, roleId, fullname, birthdays, gender, phoneNumber, null, city, District, true, null));
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

    public List<UserDTO> searchAccountSystem(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_ACCOUNT_SYSTEM);
                ptm.setNString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("userID");
                    String user = rs.getString("userAcccount");
                    String fullname = rs.getString("fullname");

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
                    Date birthday = rs.getDate("birthday");
                    String birthdays = formatter.format(birthday);

                    String gender = rs.getString("gender");
                    String roleId = rs.getString("roleId");
                    String phoneNumber = rs.getString("phoneNumber");
                    String District = rs.getString("districtName");
                    String city = rs.getString("cityName");

                    list.add(new UserDTO(id, user, null, roleId, fullname, birthdays, gender, phoneNumber, null, city, District, true, null));
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

    public static boolean updateUserInfo(UserDTO us) throws Exception {
        boolean check = false;

        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "Update [dbo].[User] Set [fullname] = ?, [birthday] = ?, [gender] = ?,"
                    + " [phoneNumber] = ?, [email] = ?, [cityID] = ?, [districtID] = ? Where [userID] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, us.getFullname());
            pst.setString(2, us.getBirthday());
            pst.setString(3, us.getGender());
            pst.setString(4, us.getPhoneNumber());
            pst.setString(5, us.getEmail());
            pst.setString(6, us.getCity());
            pst.setString(7, us.getDistrict());
            pst.setString(8, us.getId());
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                check = true;
            }
            cn.close();
        }

        return check;
    }

    public static boolean updateStudioIDForUser(String studioID, UserDTO us) throws Exception {
        boolean success = false;

        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "Update [dbo].[User] set [studioID] = ? where [userID] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, studioID);
            pst.setString(2, us.getId());
            int rowAffect = pst.executeUpdate();

            if (rowAffect > 0) {
                success = true;
            }
            cn.close();
        }

        return success;
    }
<<<<<<< HEAD

    public boolean deleteUser(String userId) throws SQLException {
        boolean check = false;
=======
    
    public boolean deleteUser(String userId) throws SQLException {
        boolean check=false;
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_USER);
                ptm.setNString(1, userId);
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
            return check;
        }
    }

<<<<<<< HEAD
    public boolean updatePasswordByEmail(String email, String password) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PASSWORD_BY_EMAIL);
                ptm.setString(1, password);
                ptm.setString(2, email);
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
            return check;
        }
    }

   
=======
>>>>>>> 46860fd9c8195799510ba7b99c8b013849670722
}
