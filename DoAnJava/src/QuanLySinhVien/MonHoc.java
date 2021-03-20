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
public class MonHoc {

    private String mamh;
    private String tenmh;
    private int tinchi;
    private String khoa;

    public MonHoc(String mamh, String tenmh, int tinchi, String khoa ) {

        this.mamh = mamh;
        this.tenmh = tenmh;
        this.tinchi = tinchi;
        this.khoa = khoa;
    }

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public String getTenmh() {
        return tenmh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }

    public int getTinchi() {
        return tinchi;
    }

    public void setTinchi(int tinchi) {
        this.tinchi = tinchi;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa1) {
        this.khoa = khoa;
    }

}
