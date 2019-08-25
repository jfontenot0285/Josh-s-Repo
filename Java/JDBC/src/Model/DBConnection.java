/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Josh
 */
public class DBConnection {
    private static String DATABASE_NAME = "U06C5H";
    private static String DB_URL = "jdbc:mysql://52.206.157.109/"+DATABASE_NAME;
    private static String USER_NAME = "U06C5H"; //U06C5H CHANGE TO TEST
    private static String PASSWORD = "53688721752"; //53688721752 CHANGE TO TEST
    private static String DRIVER = "com.mysql.jdbc.Driver";
    static Connection conn;
    
    public static void openConnection() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);
        conn = (Connection) DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        System.out.println("Connection successful");
    }
    
    public static void closeConnection() throws ClassNotFoundException, SQLException, Exception{
        conn.close();
        System.out.println("Connection closed");
    }

    public static String getDATABASE_NAME() {
        return DATABASE_NAME;
    }

    public static String getDB_URL() {
        return DB_URL;
    }

    public static String getUSER_NAME() {
        return USER_NAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static String getDRIVER() {
        return DRIVER;
    }

    public static Connection getConn() {
        return conn;
    }

    public static void setDATABASE_NAME(String DATABASE_NAME) {
        DBConnection.DATABASE_NAME = DATABASE_NAME;
    }

    public static void setDB_URL(String DB_URL) {
        DBConnection.DB_URL = DB_URL;
    }

    public static void setUSER_NAME(String USER_NAME) {
        DBConnection.USER_NAME = USER_NAME;
    }

    public static void setPASSWORD(String PASSWORD) {
        DBConnection.PASSWORD = PASSWORD;
    }

    public static void setDRIVER(String DRIVER) {
        DBConnection.DRIVER = DRIVER;
    }

    public static void setConn(Connection conn) {
        DBConnection.conn = conn;
    }
    
    
}
