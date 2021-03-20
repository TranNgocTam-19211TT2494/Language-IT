/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panel;

import QuanLySinhVien.KetQua;
import QuanLySinhVien.SinhVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author DMX PHO THANH
 */
public class FrmSapxep extends javax.swing.JPanel {

    /**
     * Creates new form FrmSapxep
     */
    public FrmSapxep() {
        initComponents();
        try {
            DbKetNoi.Db.getConnection();

            hienThiDanhSach2();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi kết nối");
        }
    }

    public void hienThiDanhSach2() {
        //Đẩy dữ liểu lên bảng:
        String co1tieude[] = new String[]{"MaSv", "HoTen", "Giới tính", "Năm Thứ", "Khoa", "Điểm"};

        ArrayList<SinhVien> list = LayDSSinhVien();
        ArrayList<KetQua> list2 = laydanhsachketqua();
        DefaultTableModel model = new DefaultTableModel(co1tieude, 0);
        Object[] row;
        for (int i = 0; i < list.size(); i++) {
            row = new Object[6];

            //Gán giá trị : 
            row[0] = list.get(i).getMasv();
            row[1] = list.get(i).getHoten();
            row[2] = list.get(i).getGioitinh();
            row[3] = list.get(i).getNamthu();
            row[4] = list.get(i).getKhoa();
            row[5] = list2.get(i).getDiem();

            model.addRow(row);

            //hien thi Jtable
            jTableQuanLySV1.setModel(model);
        }
    }

    public ArrayList<SinhVien> LayDSSinhVien() {
        ArrayList<SinhVien> list = new ArrayList<>();
        try {
            Connection con = (Connection) DbKetNoi.Db.getConnection();
            Statement st = (Statement) con.createStatement();
            String sql = "SELECT sinhvien.masv , sinhvien.hoten , sinhvien.gioitinh ,sinhvien.namthu, sinhvien.khoa , ketqua.diem FROM sinhvien , ketqua WHERE sinhvien.masv = ketqua.masv";
            //Thực thi : 
            ResultSet r = st.executeQuery(sql);

            SinhVien sv;
            KetQua l;
            while (r.next()) {

                sv = new SinhVien(r.getString("masv"), r.getString("hoten"),
                        r.getString("gioitinh"),
                        r.getInt("namthu"), r.getString("khoa"));

                list.add(sv);
            }
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());
        }
        return list;
    }
    //Lấy danh sách:
  public void hienThiDanhSach2Sort() {
        //Đẩy dữ liểu lên bảng:
        String co1tieude[] = new String[]{"MaSv", "HoTen", "Giới tính", "Năm Thứ", "Khoa", "Điểm"};

        ArrayList<SinhVien> list = MasvSort();
        ArrayList<KetQua> list2 = laydanhsachketqua();
        DefaultTableModel model = new DefaultTableModel(co1tieude, 0);
        Object[] row;
        for (int i = 0; i < list.size(); i++) {
            row = new Object[6];

            //Gán giá trị : 
            row[0] = list.get(i).getMasv();
            row[1] = list.get(i).getHoten();
            row[2] = list.get(i).getGioitinh();
            row[3] = list.get(i).getNamthu();
            row[4] = list.get(i).getKhoa();
            row[5] = list2.get(i).getDiem();

            model.addRow(row);

            //hien thi Jtable
            jTableQuanLySV1.setModel(model);
        }
    }
    public ArrayList<KetQua> laydanhsachketqua() {
        ArrayList<KetQua> list = new ArrayList<KetQua>();

        try {
            Connection conn = (Connection) DbKetNoi.Db.getConnection();
            Statement st = (Statement) conn.createStatement();
            String sql = "SELECT * FROM ketqua";
            ResultSet rs = st.executeQuery(sql);
            KetQua kq;
            while (rs.next()) {
                kq = new KetQua(rs.getString("masv"), rs.getString("makh"), rs.getFloat("diem"));
                list.add(kq);

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }
     public ArrayList<SinhVien> MasvSort() {
        ArrayList<SinhVien> list = new ArrayList<SinhVien>();

        try {
            Connection conn = (Connection) DbKetNoi.Db.getConnection();
            Statement st = (Statement) conn.createStatement();
            String sql = "SELECT sinhvien.masv , sinhvien.hoten , sinhvien.gioitinh , sinhvien.namthu , sinhvien.khoa , ketqua.diem FROM sinhvien , ketqua WHERE sinhvien.masv = ketqua.masv ORDER BY sinhvien.masv";
            ResultSet r = st.executeQuery(sql);
             SinhVien sv;
            KetQua l;
            while (r.next()) {

                sv = new SinhVien(r.getString("masv"), r.getString("hoten"),
                        r.getString("gioitinh"),
                        r.getInt("namthu"), r.getString("khoa"));

                list.add(sv);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }
     
     ///Sắp xếp năm thứ sinh viên ;::
      public void hienthiNamthuSort() {
        //Đẩy dữ liểu lên bảng:
        String co1tieude[] = new String[]{"MaSv", "HoTen", "Giới tính", "Năm Thứ", "Khoa", "Điểm"};

        ArrayList<SinhVien> list = NamThuSort();
        ArrayList<KetQua> list2 = laydanhsachketqua();
        DefaultTableModel model = new DefaultTableModel(co1tieude, 0);
        Object[] row;
        for (int i = 0; i < list.size(); i++) {
            row = new Object[6];

            //Gán giá trị : 
            row[0] = list.get(i).getMasv();
            row[1] = list.get(i).getHoten();
            row[2] = list.get(i).getGioitinh();
            row[3] = list.get(i).getNamthu();
            row[4] = list.get(i).getKhoa();
            row[5] = list2.get(i).getDiem();

            model.addRow(row);

            //hien thi Jtable
            jTableQuanLySV1.setModel(model);
        }
    }
      public ArrayList<SinhVien> NamThuSort() {
        ArrayList<SinhVien> list = new ArrayList<SinhVien>();

        try {
            Connection conn = (Connection) DbKetNoi.Db.getConnection();
            Statement st = (Statement) conn.createStatement();
            String sql = "SELECT sinhvien.masv , sinhvien.hoten , sinhvien.gioitinh , sinhvien.namthu , sinhvien.khoa , ketqua.diem FROM sinhvien , ketqua WHERE sinhvien.masv = ketqua.masv ORDER BY sinhvien.namthu";
            ResultSet r = st.executeQuery(sql);
             SinhVien sv;
           
            while (r.next()) {

                sv = new SinhVien(r.getString("masv"), r.getString("hoten"),
                        r.getString("gioitinh"),
                        r.getInt("namthu"), r.getString("khoa"));

                list.add(sv);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }
      
      
      //Sắp xếp điểm:
         public void hienthiDiemSort() {
        //Đẩy dữ liểu lên bảng:
        String co1tieude[] = new String[]{"MaSv", "HoTen", "Giới tính", "Năm Thứ", "Khoa", "Điểm"};

        ArrayList<KetQua> list = DiemSort();
        ArrayList<SinhVien> list2 = LayDSSinhVien();
        DefaultTableModel model = new DefaultTableModel(co1tieude, 0);
        Object[] row;
        for (int i = 0; i < list.size(); i++) {
            row = new Object[6];

            //Gán giá trị : 
            row[0] = list.get(i).getMasv();
            row[1] = list2.get(i).getHoten();
            row[2] = list2.get(i).getGioitinh();
            row[3] = list2.get(i).getNamthu();
            row[4] = list2.get(i).getKhoa();
            row[5] = list.get(i).getDiem();

            model.addRow(row);

            //hien thi Jtable
            jTableQuanLySV1.setModel(model);
        }
    }
      public ArrayList<KetQua> DiemSort() {
        ArrayList<KetQua> list = new ArrayList<>();
          
        try {
            Connection conn = (Connection) DbKetNoi.Db.getConnection();
            Statement st = (Statement) conn.createStatement();
            String sql = "SELECT sinhvien.masv ,ketqua.makh, sinhvien.hoten , sinhvien.gioitinh , sinhvien.namthu , sinhvien.khoa , ketqua.diem FROM sinhvien , ketqua WHERE sinhvien.masv = ketqua.masv ORDER BY ketqua.diem";
            ResultSet r = st.executeQuery(sql);
             SinhVien sv;
            KetQua l;
            while (r.next()) {

                sv = new SinhVien(r.getString("masv"), r.getString("hoten"),
                        r.getString("gioitinh"),
                        r.getInt("namthu"), r.getString("khoa"));
                
                l = new KetQua(r.getString("masv"), r.getString("makh"), r.getFloat("diem"));

                list.add(l);
                
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableQuanLySV1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/distributor-report-icon.png"))); // NOI18N
        jLabel1.setText("COLECTION SINH VIÊN");

        jTableQuanLySV1.setBorder(new javax.swing.border.MatteBorder(null));
        jTableQuanLySV1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã sinh viên", "Họ tên", "Giới tính", "Năm thứ", "Khoa", "Điểm"
            }
        ));
        jTableQuanLySV1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableQuanLySV1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableQuanLySV1);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/distributor-report-icon.png"))); // NOI18N
        jButton1.setText("Sắp xếp theo mã sinh viên");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/distributor-report-icon.png"))); // NOI18N
        jButton3.setText("Sắp xếp theo năm học");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/distributor-report-icon.png"))); // NOI18N
        jButton2.setText("Sắp xếp theo điểm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(88, 88, 88)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(68, 68, 68))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(197, 197, 197))
            .addComponent(jScrollPane5)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTableQuanLySV1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableQuanLySV1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableQuanLySV1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hienThiDanhSach2Sort();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        hienthiDiemSort();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       hienthiNamthuSort();
    }//GEN-LAST:event_jButton3ActionPerformed
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableQuanLySV1;
    // End of variables declaration//GEN-END:variables
}
