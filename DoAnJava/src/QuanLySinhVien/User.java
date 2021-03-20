/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLySinhVien;

/**
 *
 * @author DMX PHO THANH
 */
public class User {
    private String taikhoan;
    private String pass;
    private int soDT;

    public User(String taikhoan, String pass, int soDT) {
        this.taikhoan = taikhoan;
        this.pass = pass;
        this.soDT = soDT;
    }

    public String getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getSoDT() {
        return soDT;
    }

    public void setSoDT(int soDT) {
        this.soDT = soDT;
    }
    
}
