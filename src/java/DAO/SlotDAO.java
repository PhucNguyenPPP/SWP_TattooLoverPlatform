/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class SlotDAO {
     public static void addStudioSlot(String studioID, String timeStart, String timeEnd) throws Exception {

        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "insert into [dbo].[Slot]([slotID],[studioID],[timeStart],[timeEnd], [status]) values (?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);

            pst.setString(1, createSlotId());
            pst.setString(2, studioID);
            pst.setString(3, timeStart);
            pst.setString(4, timeEnd);
             pst.setBoolean(5, true);
            pst.executeUpdate();
            
            cn.close();
        }
    }

    public static String getRandomSlotId() {
        String userId;
        Random rand = new Random();
        int int_random = rand.nextInt(1000000000);
        String followInt = String.format("%06d", int_random);
        userId = "SL" + followInt;
        return userId;
    }

    public static String createSlotId() throws Exception{
        String slotId = "";
        do {
            slotId = getRandomSlotId();
        } while (slotIDIsUnique(slotId));
        return slotId;
    }

    public static boolean slotIDIsUnique(String slotID) throws Exception {
        boolean check = true;

        Connection cn = DBUtils.getConnection();
        if (cn != null) {
            String sql = "select [slotID] from [dbo].[Slot] where [slotID] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, slotID);

            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                check = false;
            }
            cn.close();
        }
        return check;
    }
}
