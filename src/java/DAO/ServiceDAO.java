/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ServiceDTO;
import DTO.ServiceDetailDTO;
import DTO.ServiceSizeDTO;
import DTO.StudioServiceDTO;
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
public class ServiceDAO {

    private static String SERVICE = "SELECT serviceID, serviceName, status FROM Service";
    private static String SERVICE_DETAIL = "SELECT serviceDetailID, serviceDetailName, status FROM Service_Detail";
    private static final String GET_SERVICE_BY_ID = "SELECT * FROM Service WHERE serviceID=?";
    private static final String GET_SERVICE_DETAIL_BY_ID = "SELECT * FROM Service_Detail WHERE serviceDetailID=?";
    private static final String DEL_STU_SERVICE = "UPDATE Studio_Service SET\n"
            + "status=0\n"
            + "WHERE studioID=? AND serviceID=? AND serviceDetailID=?";
    private static final String UPDATE_PRICE = "UPDATE Studio_Service\n"
            + "SET studioStaffID=?,\n"
            + "	price=?\n"
            + "WHERE studioID=? AND serviceID=? AND serviceDetailID =? AND serviceSizeID=? AND status=1";

    private static final String SERVICE_NAME = "SELECT serviceName FROM Service WHERE serviceID=?";
    private static final String SERVICE_DETAIL_NAME = "SELECT serviceDetailName FROM [Service_Detail] WHERE serviceDetailID =?";
    private static final String GET_STU_SER_LIST_PRICE = "SELECT serviceSizeName, k.serviceSizeID, price FROM Service_Size,\n"
            + "(SELECT * FROM Studio_Service WHERE studioID=? AND\n"
            + "serviceID=? AND serviceDetailID=? AND status=1) as k WHERE k.serviceSizeID=Service_Size.serviceSizeID";
    private static final String GET_SERVICE_SIZE = "SELECT * FROM [Service_Size] WHERE status=1";
    private static final String CHECK_STUDIO_SERVICE_ID = "SELECT studioServiceID FROM Studio_Service";
    private static final String ADD_PRICE = "INSERT INTO Studio_Service(studioStaffID,studioID,serviceID,serviceDetailID,serviceSizeID,price,status,studioServiceID) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SERVICE_LIST = "Select serviceID, fullname, serviceName, s.[status] from [Service] s, [User] u where s.systemStaffID = u.userID";
    private static final String SERVICE_LIST_DETAIL = "Select serviceDetailID, fullname, serviceDetailName, s.[status] from [Service_Detail] s, [User] u where s.systemStaffID = u.userID";
    private static final String SERVICE_SIZE = "Select serviceSizeID, fullname, serviceSizeName, s.[status] from [Service_Size] s, [User] u where s.systemStaffID = u.userID";
    private static final String SEARCH_SERVICE = "Select serviceID, fullname, serviceName, s.[status] from [Service] s, [User] u where s.systemStaffID = u.userID and serviceName like ? ;";
    private static final String SEARCH_SERVICE_DETAIL = "Select serviceDetailID, fullname, serviceDetailName, s.[status] from [Service_Detail] s, [User] u where s.systemStaffID = u.userID and serviceDetailName like ?";
    private static final String SEARCH_SERVICE_SIZE = "Select serviceSizeID, fullname, serviceSizeName, s.[status] from [Service_Size] s, [User] u where s.systemStaffID = u.userID and serviceSizeName like ?";
    private static final String CHECK_SERVICE_NAME_EXISTED = "SELECT * FROM Service WHERE serviceName = ?";
    private static final String CHECK_SERVICE_DETAIL_NAME_EXISTED = "SELECT * FROM Service_Detail WHERE serviceDetailName = ?";
    private static final String CHECK_SERVICE_SIZE_NAME_EXISTED = "SELECT * FROM Service_Size WHERE serviceSizeName = ?";
    private static final String CREATE_SERVICE = "insert into [Service] (serviceID, systemStaffID, serviceName, [status]) values (?,?,?,1)";
    private static final String CREATE_SERVICE_DETAIL = "insert into [Service_Detail] (serviceDetailID, systemStaffID, serviceDetailName, [status]) values (?,?,?,1)";
    private static final String CREATE_SERVICE_SIZE = "insert into [Service_Size] (serviceSizeID, systemStaffID, serviceSizeName, [status]) values (?,?,?,1)";
    private static final String CHECK_SERVICE_ID_DUPLICATED = "SELECT serviceID FROM Service";
    private static final String CHECK_SERVICE_DETAIL_ID_DUPLICATED = "SELECT serviceDetailID FROM Service_Detail";
    private static final String CHECK_SERVICE_SIZE_ID_DUPLICATED = "SELECT serviceSizeID FROM Service_Size";
    private static final String DELETE_SERVICE_SYSTEM = "UPDATE Studio_Service "
            + "SET status = 0 "
            + "WHERE serviceID = ?; "
            + "UPDATE Service "
            + "SET status = 0 "
            + "WHERE serviceID = ?;";
    private static final String DELETE_SERVICE_DETAIL_SYSTEM = "UPDATE Studio_Service "
            + "SET status = 0 "
            + "WHERE serviceDetailID = ?; "
            + "UPDATE Service_Detail "
            + "SET status = 0 "
            + "WHERE serviceDetailID = ?;";
    private static final String DELETE_SERVICE_SIZE_SYSTEM = "UPDATE Studio_Service "
            + "SET status = 0 "
            + "WHERE serviceSizeID = ?; "
            + "UPDATE Service_Size "
            + "SET status = 0 "
            + "WHERE serviceSizeID = ?;";

    public List<ServiceDTO> getService() throws SQLException {
        List<ServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SERVICE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceID = rs.getString("serviceID");
                    String serviceName = rs.getString("serviceName");
                    boolean status = rs.getBoolean("status");
                    if (status == true) {
                        list.add(new ServiceDTO(serviceID, null, serviceName, status));
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

    public List<ServiceDetailDTO> getServiceDetail() throws SQLException {
        List<ServiceDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SERVICE_DETAIL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceDetailID = rs.getString("serviceDetailID");
                    String serviceDetailName = rs.getString("serviceDetailName");
                    boolean status = rs.getBoolean("status");
                    if (status == true) {
                        list.add(new ServiceDetailDTO(serviceDetailID, null, serviceDetailName, status));
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

    public List<ServiceDTO> getServiceByID(String serviceID) throws SQLException {
        List<ServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SERVICE_BY_ID);
                ptm.setString(1, serviceID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceName = rs.getString("serviceName");
                    boolean status = rs.getBoolean("status");
                    if (status == true) {
                        list.add(new ServiceDTO(serviceID, null, serviceName, status));
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

    public List<ServiceDetailDTO> getServiceDetailByID(String serviceID) throws SQLException {
        List<ServiceDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SERVICE_DETAIL_BY_ID);
                ptm.setString(1, serviceID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceDetailID = rs.getString("serviceDetailID");
                    String serviceDetailName = rs.getString("serviceDetailName");
                    boolean status = rs.getBoolean("status");
                    if (status == true) {
                        list.add(new ServiceDetailDTO(serviceDetailID, null, serviceDetailName, status));
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

    public boolean delStudioService(String studioID, String serviceID, String serviceDetailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DEL_STU_SERVICE);
                ptm.setString(1, studioID);
                ptm.setString(2, serviceID);
                ptm.setString(3, serviceDetailID);
                ptm.executeUpdate();
                check = true;
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

    public boolean updatePriceList(String staffId, String studioID, String serviceID, String serviceDetailID, String serviceSizeID, int price) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_PRICE);
                ptm.setString(1, staffId);
                ptm.setInt(2, price);
                ptm.setString(3, studioID);
                ptm.setString(4, serviceID);
                ptm.setString(5, serviceDetailID);
                ptm.setString(6, serviceSizeID);
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

    public String getServiceNameByID(String serviceID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String name = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SERVICE_NAME);
                ptm.setString(1, serviceID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    name = rs.getString("serviceName");
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
        return name;
    }

    public String getServiceDetailNameByID(String serviceDetailID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String name = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SERVICE_DETAIL_NAME);
                ptm.setString(1, serviceDetailID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    name = rs.getString("serviceDetailName");
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
        return name;
    }

    public List<StudioServiceDTO> getStuPriceList(String studioID, String serviceID, String serviceDetailID) throws SQLException {
        List<StudioServiceDTO> stuPriceList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_STU_SER_LIST_PRICE);
                ptm.setString(1, studioID);
                ptm.setString(2, serviceID);
                ptm.setString(3, serviceDetailID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String sizeID = rs.getString("serviceSizeID");
                    int price = rs.getInt("price");
                    String sizeName = rs.getString("serviceSizeName");
                    StudioServiceDTO stuSer = new StudioServiceDTO(null, null, null, null, null, null, sizeID, sizeName, null, price, 0, true);
                    stuPriceList.add(stuSer);
                }
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
        return stuPriceList;
    }

    public List<ServiceSizeDTO> getSizeList() throws SQLException {
        List<ServiceSizeDTO> sizelist = new ArrayList<>();
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
                    String serviceSizeName = rs.getString("serviceSizeName");
                    String staffID = rs.getString("systemStaffID");
                    boolean status = rs.getBoolean("status");
                    if (status == true) {
                        sizelist.add(new ServiceSizeDTO(serviceSizeID, staffID, serviceSizeName, status));
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
        return sizelist;
    }

    public static String generateStudioServiceID() throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean STSVIDDuplicated = false;
        String studioServiceID = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                Random rd = new Random();
                do {
                    STSVIDDuplicated = false;
                    int number_stsv = rd.nextInt(1000);
                    studioServiceID = "STSV" + number_stsv;
                    ptm = conn.prepareStatement(CHECK_STUDIO_SERVICE_ID);
                    rs = ptm.executeQuery();
                    while (rs.next()) {
                        String studio_service_ID_check = rs.getString("studioServiceID");
                        if (studioServiceID.equals(studio_service_ID_check)) {
                            STSVIDDuplicated = true;
                            break;
                        }
                    }
                } while (STSVIDDuplicated == true);

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
        return studioServiceID;
    }

    public void addPriceList(String staffId, String studioID, String serviceID, String serviceDetailID, String serviceSizeID, int price, String studioServiceID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_PRICE);
                ptm.setString(1, staffId);
                ptm.setString(2, studioID);
                ptm.setString(3, serviceID);
                ptm.setString(4, serviceDetailID);
                ptm.setString(5, serviceSizeID);
                ptm.setInt(6, price);
                ptm.setBoolean(7, true);
                ptm.setString(8, studioServiceID);
                ptm.executeUpdate();
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
    }

    public List<ServiceDTO> getListService() throws SQLException {
        List<ServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SERVICE_LIST);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceId = rs.getString(1);
                    String staffName = rs.getString(2);
                    String nameService = rs.getString(3);
                    boolean status = rs.getBoolean(4);
                    if (status == true) {
                        list.add(new ServiceDTO(serviceId, staffName, nameService, status));
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

    public List<ServiceDetailDTO> getListServiceDetail() throws SQLException {
        List<ServiceDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SERVICE_LIST_DETAIL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceId = rs.getString(1);
                    String staffName = rs.getString(2);
                    String nameService = rs.getString(3);
                    boolean status = rs.getBoolean(4);
                    if (status == true) {
                        list.add(new ServiceDetailDTO(serviceId, staffName, nameService, status));
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

    public List<ServiceSizeDTO> getListServiceSize() throws SQLException {
        List<ServiceSizeDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SERVICE_SIZE);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceId = rs.getString(1);
                    String staffName = rs.getString(2);
                    String nameService = rs.getString(3);
                    boolean status = rs.getBoolean(4);
                    if (status == true) {
                         list.add(new ServiceSizeDTO(serviceId, staffName, nameService, status));
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

    public List<ServiceDTO> searchService(String search) throws SQLException {
        List<ServiceDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(SEARCH_SERVICE);
                ptm.setNString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceId = rs.getString("serviceID");
                    String staffName = rs.getString("fullname");
                    String nameService = rs.getString("serviceName");
                    boolean status = rs.getBoolean("status");

                    list.add(new ServiceDTO(serviceId, staffName, nameService, status));

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

    public List<ServiceDetailDTO> searchServiceDetail(String search) throws SQLException {
        List<ServiceDetailDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(SEARCH_SERVICE_DETAIL);
                ptm.setNString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceDetailId = rs.getString("serviceDetailID");
                    String fullName = rs.getString("fullname");
                    String nameServiceDetail = rs.getString("serviceDetailName");
                    boolean status = rs.getBoolean("status");

                    list.add(new ServiceDetailDTO(serviceDetailId, fullName, nameServiceDetail, status));

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

    public List<ServiceSizeDTO> searchServiceSize(String search) throws SQLException {
        List<ServiceSizeDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(SEARCH_SERVICE_SIZE);
                ptm.setNString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String serviceSizeId = rs.getString("serviceSizeID");
                    String fullName = rs.getString("fullname");
                    String nameServiceSize = rs.getString("serviceSizeName");
                    boolean status = rs.getBoolean("status");

                    list.add(new ServiceSizeDTO(serviceSizeId, fullName, nameServiceSize, status));

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

    public boolean checkServiceNameDuplicated(String nameService) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(CHECK_SERVICE_NAME_EXISTED);
                ptm.setNString(1, nameService);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    check = true;
                    break;
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
            return check;
        }
    }
    public String serviceID = null;

    public String getServiceID() {
        return serviceID;
    }

    public boolean createService(String staffID, String nameService) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();

            boolean serviceIDDuplicated;
            Random rd = new Random();
            do {
                serviceIDDuplicated = false;
                int number = rd.nextInt(1000);
                serviceID = "SV" + number;
                ptm = conn.prepareStatement(CHECK_SERVICE_ID_DUPLICATED);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String service_ID_check = rs.getString("serviceID");
                    if (serviceID.equals(service_ID_check)) {
                        serviceIDDuplicated = true;
                        break;
                    }
                }
            } while (serviceIDDuplicated == true);

            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_SERVICE);
                ptm.setString(1, serviceID);
                ptm.setNString(2, staffID);
                ptm.setString(3, nameService);
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

    public boolean checkServiceDetailNameDuplicated(String nameService) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(CHECK_SERVICE_DETAIL_NAME_EXISTED);
                ptm.setNString(1, nameService);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    check = true;
                    break;
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
            return check;
        }
    }

    public String serviceDetailID = null;

    public String getServiceDetailID() {
        return serviceDetailID;
    }

    public boolean createServiceDetail(String staffID, String nameService) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();

            boolean serviceDetailIDDuplicated;
            Random rd = new Random();
            do {
                serviceDetailIDDuplicated = false;
                int number = rd.nextInt(1000);
                serviceDetailID = "SVD" + number;
                ptm = conn.prepareStatement(CHECK_SERVICE_DETAIL_ID_DUPLICATED);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String service_ID_check = rs.getString("serviceDetailID");
                    if (serviceDetailID.equals(service_ID_check)) {
                        serviceDetailIDDuplicated = true;
                        break;
                    }
                }
            } while (serviceDetailIDDuplicated == true);

            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_SERVICE_DETAIL);
                ptm.setString(1, serviceDetailID);
                ptm.setNString(2, staffID);
                ptm.setString(3, nameService);
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

    public boolean checkServiceSizeNameDuplicated(String nameService) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(CHECK_SERVICE_SIZE_NAME_EXISTED);
                ptm.setNString(1, nameService);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    check = true;
                    break;
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
            return check;
        }
    }

    public boolean createServiceSize(String staffID, String nameService) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();

            boolean serviceSizeIDDuplicated;
            String serviceSizeID = null;
            Random rd = new Random();
            do {
                serviceSizeIDDuplicated = false;
                int number = rd.nextInt(1000);
                serviceSizeID = "SVS" + number;
                ptm = conn.prepareStatement(CHECK_SERVICE_SIZE_ID_DUPLICATED);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String service_size_ID_check = rs.getString("serviceSizeID");
                    if (serviceSizeID.equals(service_size_ID_check)) {
                        serviceSizeIDDuplicated = true;
                        break;
                    }
                }
            } while (serviceSizeIDDuplicated == true);

            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_SERVICE_SIZE);
                ptm.setString(1, serviceSizeID);
                ptm.setNString(2, staffID);
                ptm.setString(3, nameService);
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
    
    public boolean deleteService(String serviceID) throws SQLException {
         boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_SERVICE_SYSTEM);
                ptm.setString(1, serviceID);
                ptm.setNString(2, serviceID);
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

    public boolean deleteServiceDetail(String serviceDetailID) throws SQLException {
       boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_SERVICE_DETAIL_SYSTEM);
                ptm.setString(1, serviceDetailID);
                ptm.setNString(2, serviceDetailID);
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

    public boolean deleteServiceSize(String serviceSizeID) throws SQLException {
         boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_SERVICE_SIZE_SYSTEM);
                ptm.setString(1, serviceSizeID);
                ptm.setNString(2, serviceSizeID);
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
