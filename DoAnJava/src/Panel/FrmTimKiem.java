/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Panel;

import QuanLySinhVien.KetQua;
import QuanLySinhVien.KhoaHoc;
import QuanLySinhVien.MonHoc;
import QuanLySinhVien.SinhVien;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author DMX PHO THANH
 */
public class FrmTimKiem extends javax.swing.JPanel {

    /**
     * Creates new form FrmTimKiem
     */
    public FrmTimKiem() {
        initComponents();
        try {
            DbKetNoi.Db.getConnection();
            hienThiDanhSach();

            hienthidanhmonhoc();
            hienthidanhkhoahoc();
            hienthidanhketqua();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi kết nối");
        }
    }
//Lấy danh sách sinh viên trên WamServer

    public ArrayList<SinhVien> LayDSSinhVien() {
        ArrayList<SinhVien> list = new ArrayList<>();
        try {
            Connection con = (Connection) DbKetNoi.Db.getConnection();
            Statement st = (Statement) con.createStatement();
            String sql = "SELECT sinhvien.masv , sinhvien.hoten , sinhvien.gioitinh ,sinhvien.namthu, sinhvien.khoa , ketqua.diem FROM sinhvien , ketqua WHERE sinhvien.masv = ketqua.masv";
            //Thực thi : 
            ResultSet r = st.executeQuery(sql);

            SinhVien sv;
            KetQua kp;
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

    //Hiển thi danh sách sinh viên : 
    public void hienThiDanhSach() {
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
            jTableQuanLySV.setModel(model);
        }
    }

    //Lấy danh sách:
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

    //Lấy danh sách môn học : 
    public ArrayList<MonHoc> laydanhsachmonhoc() {
        ArrayList<MonHoc> list = new ArrayList<>();
        try {

            Connection conn = (Connection) DbKetNoi.Db.getConnection();
            java.sql.Statement st = (java.sql.Statement) conn.createStatement();
            String sql = "SELECT * FROM monhoc";
            ResultSet rs = st.executeQuery(sql);

            MonHoc mh;
            while (rs.next()) {
                mh = new MonHoc(rs.getString("mamh"), rs.getString("tenmh"), rs.getInt("tinchi"), rs.getString("khoa"));

                list.add(mh);

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }

    //Hiển thị danh sách môn học:
    public void hienthidanhmonhoc() {
        String co1tieude[] = new String[]{"Mã môn học", "Tên môn học", "Số tín chỉ", "Khoa"};

        ArrayList<MonHoc> list = laydanhsachmonhoc();

        DefaultTableModel model = new DefaultTableModel(co1tieude, 0);

        Object[] row;
        for (int i = 0; i < list.size(); i++) {
            row = new Object[4];

            //gán giá trị
            row[0] = list.get(i).getMamh();
            row[1] = list.get(i).getTenmh();
            row[2] = list.get(i).getTinchi();
            row[3] = list.get(i).getKhoa();

            //thêm vao model
            model.addRow(row);

            //hien thi Jtable
            jTableMonHoc.setModel(model);

        }
    }

    //Hiển thị danh sách:
    public void hienthidanhkhoahoc() {
        String co1tieude[] = new String[]{"Mã Khóa Học", "Mã Môn Học", "Học Kỳ", "Năm", "Giáo viên"};

        ArrayList<KhoaHoc> list = laydanhsachkhoahoc();

        DefaultTableModel model = new DefaultTableModel(co1tieude, 0);

        Object[] row;
        for (int i = 0; i < list.size(); i++) {
            row = new Object[5];

            //gán giá trị
            row[0] = list.get(i).getMakh();
            row[1] = list.get(i).getMamh();
            row[2] = list.get(i).getHocky();
            row[3] = list.get(i).getNam();
            row[4] = list.get(i).getGiaovien();

            //thêm vao model
            model.addRow(row);

            //hien thi Jtable
            jTableKhoaHoc.setModel(model);

        }
    }

    //Lấy danh sách Khóa học
    public ArrayList<KhoaHoc> laydanhsachkhoahoc() {
        ArrayList<KhoaHoc> list = new ArrayList<KhoaHoc>();

        try {
            Connection conn = (Connection) DbKetNoi.Db.getConnection();
            Statement st = (Statement) conn.createStatement();
            String sql = "SELECT * FROM khoahoc";
            ResultSet rs = st.executeQuery(sql);

            KhoaHoc kh;
            while (rs.next()) {
                kh = new KhoaHoc(rs.getString("makh"), rs.getString("mamh"), rs.getInt("hocky"), rs.getInt("nam"), rs.getString("giaovien"));
                list.add(kh);
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }
    //hiện thị danh sách:

    public void hienthidanhketqua() {

        String co1tieude[] = new String[]{"Mã sinh viên", "Mã Khóa học", "Điểm"};

        ArrayList<KetQua> list = laydanhsachketqua();

        DefaultTableModel model = new DefaultTableModel(co1tieude, 0);

        Object[] row;
        for (int i = 0; i < list.size(); i++) {
            row = new Object[5];

            //gán giá trị
            row[0] = list.get(i).getMasv();
            row[1] = list.get(i).getMakh();
            row[2] = list.get(i).getDiem();

            //thêm vao model
            model.addRow(row);

            //hien thi Jtable
            jTableKetQua.setModel(model);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableQuanLySV3 = new javax.swing.JTable();
        btnSeach1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtSeach1 = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableQuanLySV = new javax.swing.JTable();
        txtTim = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMonHoc = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtTimMH = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableKhoaHoc = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableKetQua = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();

        jTableQuanLySV3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableQuanLySV3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableQuanLySV3MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTableQuanLySV3);

        btnSeach1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search2.png"))); // NOI18N
        btnSeach1.setText("Search");

        jLabel5.setText("Nhập Khoa : ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(47, 47, 47)
                .addComponent(txtSeach1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btnSeach1)
                .addGap(256, 256, 256))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSeach1)
                            .addComponent(txtSeach1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setToolTipText("");

        jTableQuanLySV.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableQuanLySV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableQuanLySVMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableQuanLySV);

        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search2.png"))); // NOI18N
        btnTim.setText("Search");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jLabel2.setText("Nhập mã sinh viên : ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jLabel2)
                .addGap(47, 47, 47)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(btnTim)
                .addContainerGap(235, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tìm kiếm sinh viên theo mã sinh viên", jPanel3);

        jTableMonHoc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTableMonHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã Môn Học : ", "Tên Môn Học : ", "Số Tín Chỉ : ", "Khoa"
            }
        ));
        jTableMonHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMonHocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMonHoc);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search-icon.png"))); // NOI18N
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimMH, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addGap(312, 312, 312))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 57, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(txtTimMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Tìm kiếm bảng môn học", jPanel4);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search-icon.png"))); // NOI18N
        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jScrollPane3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jTableKhoaHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Khóa Học", "Mã Môn Học", "Học Kỳ", "Năm ", "Giáo viên"
            }
        ));
        jTableKhoaHoc.setMaximumSize(new java.awt.Dimension(2147483647, 200));
        jTableKhoaHoc.setMinimumSize(new java.awt.Dimension(75, 100));
        jTableKhoaHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableKhoaHocMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableKhoaHoc);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(270, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jButton2)
                .addGap(284, 284, 284))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tìm kiếm trong bảng khóa học", jPanel5);

        jScrollPane2.setForeground(new java.awt.Color(0, 51, 51));

        jTableKetQua.setBorder(new javax.swing.border.MatteBorder(null));
        jTableKetQua.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sinh viên", "Mã Khóa học", "Title 3"
            }
        ));
        jTableKetQua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableKetQuaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableKetQua);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search-icon.png"))); // NOI18N
        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(242, Short.MAX_VALUE)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jButton3)
                .addGap(305, 305, 305))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(161, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(150, 150, 150)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Tìm kiếm trong bảng kết quả", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        SinhVien sv = null;

        DefaultTableModel m = (DefaultTableModel) jTableQuanLySV.getModel();
        m.fireTableDataChanged();
        TableRowSorter s = new TableRowSorter(m);
        jTableQuanLySV.setRowSorter(s);
        s.setRowFilter(RowFilter.regexFilter(txtTim.getText()));
    }//GEN-LAST:event_btnTimActionPerformed

    private void jTableQuanLySVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableQuanLySVMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableQuanLySVMouseClicked

    private void jTableQuanLySV3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableQuanLySV3MouseClicked
    }//GEN-LAST:event_jTableQuanLySV3MouseClicked

    private void jTableMonHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMonHocMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTableMonHocMouseClicked

    private void jTableKetQuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKetQuaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTableKetQuaMouseClicked

    private void jTableKhoaHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableKhoaHocMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableKhoaHocMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel m = (DefaultTableModel) jTableMonHoc.getModel();
        m.fireTableDataChanged();
        TableRowSorter s = new TableRowSorter(m);
        jTableMonHoc.setRowSorter(s);
        s.setRowFilter(RowFilter.regexFilter(txtTimMH.getText()));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel m = (DefaultTableModel) jTableKhoaHoc.getModel();
        m.fireTableDataChanged();
        TableRowSorter s = new TableRowSorter(m);
        jTableKhoaHoc.setRowSorter(s);
        s.setRowFilter(RowFilter.regexFilter(jTextField1.getText()));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel m = (DefaultTableModel) jTableKetQua.getModel();
        m.fireTableDataChanged();
        TableRowSorter s = new TableRowSorter(m);
        jTableKetQua.setRowSorter(s);
        s.setRowFilter(RowFilter.regexFilter(jTextField2.getText()));
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSeach1;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableKetQua;
    private javax.swing.JTable jTableKhoaHoc;
    private javax.swing.JTable jTableMonHoc;
    private javax.swing.JTable jTableQuanLySV;
    private javax.swing.JTable jTableQuanLySV3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField txtSeach1;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextField txtTimMH;
    // End of variables declaration//GEN-END:variables
}
