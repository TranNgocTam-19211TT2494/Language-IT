/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLySinhVien;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;


/**
 *
 * @author DMX PHO THANH
 */
public class CheckLogin {

    public static Connection conn = null;
    public static ResultSet rs = null;
    public static PreparedStatement pst = null;

    public static String testConnect() {
        try {
            Connection conn = DbKetNoi.Db.getConnection();
            return "kết nối thành công !";

        } catch (Exception e) {

            return "kết nối thất bại";
        }
    }

    public static ResultSet cLogin(String user, String pass) {
        String sql = "SELECT * FROM user WHERE taikhoan = ? AND password = ?";
        try {
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);
            return rs = pst.executeQuery();

        } catch (Exception e) {
            return rs = null;
        }
    }
}
