/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbKetNoi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DMX PHO THANH
 */
public class Db {

    public static Connection conn = null;
    private static String url = "jdbc:mysql://localhost:3306/sinhvien1";

    public static final Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = (Connection) DriverManager.getConnection(url, "root", "");
        }
        return conn;
    }

    public static int setDta(String s) throws SQLException {
        return Db.getConnection().createStatement().executeUpdate(s);
    }

    public static ResultSet getData(String s) throws SQLException {
        return Db.getConnection().createStatement().executeQuery(s);
    }

}
