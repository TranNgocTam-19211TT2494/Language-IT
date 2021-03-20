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
public class SinhVien{
    private String masv;
    private String hoten;
    private String gioitinh;
    private int namthu;
    private String khoa;


//    public SinhVien(String masv, String makh, float diem) {
//        super(masv, makh, diem);
//    }

    public SinhVien(String masv, String hoten, String gioitinh, int namthu, String khoa) {
        this.masv = masv;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.namthu = namthu;
        this.khoa = khoa;
        
    }



    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getNamthu() {
        return namthu;
    }

    public void setNamthu(int namthu) {
        this.namthu = namthu;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }
}
