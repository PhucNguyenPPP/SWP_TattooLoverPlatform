/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ImgDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class ImgDAO {

    private static String IMAGE_LIST = "select * from Image i WHERE i.serviceID in (SELECT serviceID FROM Service WHERE status=1) or \n"
            + "i.serviceDetailID in (SELECT serviceDetailID FROM Service_Detail WHERE status=1)";

    private static final String CHECK_IMG_ID = "SELECT imgId FROM Image";
    
    private static final String ADD_IMG = "INSERT INTO Image VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public List<ImgDTO> getImgService() throws SQLException {
        List<ImgDTO> imgList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(IMAGE_LIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String imgLink = rs.getString("imgLink");
                    String serviceID = rs.getString("serviceID");
                    String role = rs.getString("role");
                    String serviceDetailID = rs.getString("serviceDetailID");
                    if (role.equalsIgnoreCase("AVA")) {
                        if (serviceID != null) {
                            imgList.add(new ImgDTO(null, null, imgLink, role, null, null, serviceID, null));
                        } else if (serviceDetailID != null) {
                            imgList.add(new ImgDTO(null, null, imgLink, role, null, null, null, serviceDetailID));
                        }
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
        return imgList;
    }

    public void addImage(String imageName, String base64Data, String imageRole, String studioID, String userID, String serivceID, String serivceDetailID, boolean status) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();

            Random rd = new Random();
            String imgID = "";
            boolean imgIDDuplicated;
            do {
                imgIDDuplicated = false;
                int number = rd.nextInt(10000);
                imgID = "IMG" + number;
                ptm = conn.prepareStatement(CHECK_IMG_ID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String imgID_check = rs.getString("imgId");
                    if (imgID.equals(imgID_check)) {
                        imgIDDuplicated = true;
                        break;
                    }
                }
            } while (imgIDDuplicated == true);

            if (conn != null) {
                ptm = conn.prepareStatement(ADD_IMG);
                ptm.setString(1, imgID);
                ptm.setString(2, imageName);
                ptm.setString(3, base64Data);
                ptm.setString(4, imageRole);
                ptm.setString(5, studioID);
                ptm.setString(6, userID);
                ptm.setString(7, serivceID);
                ptm.setString(8, serivceDetailID);
                ptm.setBoolean(9, status);
                ptm.executeUpdate();
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
    }

}
