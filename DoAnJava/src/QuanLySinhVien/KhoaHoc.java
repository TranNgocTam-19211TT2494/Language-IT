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
public class KhoaHoc {

    private String makh;
    private String mamh;
    private int hocky;
    private int nam;
    private String giaovien;

    public KhoaHoc(String makh, String mamh, int hocky, int nam, String giaovien) {

        this.makh = makh;
        this.mamh = mamh;
        this.hocky = hocky;
        this.nam = nam;
        this.giaovien = giaovien;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public int getHocky() {
        return hocky;
    }

    public void setHocky(int hocky) {
        this.hocky = hocky;
    }

    public int getNam() {
        return nam;
    }

    public void setNam(int nam) {
        this.nam = nam;
    }

    public String getGiaovien() {
        return giaovien;
    }

    public void setGiaovien(String giaovien) {
        this.giaovien = giaovien;
    }

}
