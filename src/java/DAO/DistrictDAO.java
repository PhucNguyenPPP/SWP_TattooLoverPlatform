/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.DistrictDTO;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class DistrictDAO {

    private static String DISTRICT = "Select [districtID],[cityID],[districtName] from [dbo].[District]";

    public static List<DistrictDTO> getDistrictList() throws SQLException {
        List<DistrictDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DISTRICT);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String districtID = rs.getString("districtID");
                    String cityID = rs.getString("cityID");
                    String districtName = rs.getString("districtName");
                    list.add(new DistrictDTO(districtID, cityID, districtName));
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
    
    
    

    public static List<DistrictDTO> getDistrictListByCityID(String cityID) throws SQLException {
        List<DistrictDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = DISTRICT + " where [cityID] = ?";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, cityID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String districtID = rs.getString("districtID");
                    String thisCityID = rs.getString("cityID");
                    String districtName = rs.getString("districtName");
                    list.add(new DistrictDTO(districtID, thisCityID, districtName));
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
