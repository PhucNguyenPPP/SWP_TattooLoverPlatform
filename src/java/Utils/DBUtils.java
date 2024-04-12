/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class DBUtils {

    /*private static final String DB_NAME = "TattooManagementDB";
    private static final String DB_USER_NAME = "SA";
    private static final String DB_PASSWORD = "12345";*/
    private static final String DB_USER_NAME = "tattooDatabase@phuc-sa";
    private static final String DB_PASSWORD = "Minhphuc3617@";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        /*String url = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME;
        conn = DriverManager.getConnection(url,DB_USER_NAME,DB_PASSWORD);*/
        String url = "jdbc:sqlserver://phuc-sa.database.windows.net:1433;database=TattooManagementDB;user=" + DB_USER_NAME + ";password=" + DB_PASSWORD + ";encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        conn = DriverManager.getConnection(url,DB_USER_NAME,DB_PASSWORD);
        return conn;
    }
}
