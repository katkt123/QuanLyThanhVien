/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.XuLyViPham;

import BLL.XuLyViPhamBLL;
import DTO.ThanhVienDTO;
import DTO.XuLyViPhamDTO;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class XuLyViPhamGUI extends javax.swing.JPanel {

    XuLyViPhamBLL xlvpBLL = new XuLyViPhamBLL();
   
    public static int idXL;
    public static ThanhVienDTO thanhvien;
    public static String hinhthuc;
    public static int sotien;
    public static Date ngayxl;
    public static int trangthai;
    
    ArrayList<XuLyViPhamDTO> arrXuLy = new ArrayList<XuLyViPhamDTO>();
    
    DefaultTableModel modelXL = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // không cho phép chỉnh sửa giá trị các ô trong bảng
        }
    };
    
    public XuLyViPhamGUI() {
        initComponents();
        
        modelXL.addColumn("Mã xử lý");
        modelXL.addColumn("Mã Thành viên");
        modelXL.addColumn("Hình thức");
        modelXL.addColumn("Số tiền");
        modelXL.addColumn("Ngày xử lý");
        modelXL.addColumn("Trạng thái");
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setFont(new Font("Arial", Font.BOLD, 20)); // Set bold font
        headerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER); // Set center alignment
        
        for (int i = 0; i < jTable_XuLy.getColumnCount(); i++) {
            jTable_XuLy.getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Set center alignment
        
        for (int i = 0; i < jTable_XuLy.getColumnCount(); i++) {
            jTable_XuLy.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        
        loadXuLyViPham();
    }

    public void loadXuLyViPham(){
        
        arrXuLy = xlvpBLL.listxlvp();

        for(int i = modelXL.getRowCount()-1;i>=0;i--)
            modelXL.removeRow(i);
        for(int i = 0; i<arrXuLy.size();i++){
            XuLyViPhamDTO em= arrXuLy.get(i);
            
            int idXL= em.getMaXL();
            ThanhVienDTO thanhvien = em.getMaTV();
            String hinhthuc = em.getHinhThucXL();
            int sotien = em.getSoTien();
            Date ngayxl = em.getNgayXL();
            int trangthai = em.getTrangThaiXL();
//            int a;
            
            Object[] row = {idXL, thanhvien.getMaTV() , hinhthuc, sotien, ngayxl, trangthai};
            modelXL.addRow(row);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_Search = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_XuLy = new javax.swing.JTable();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setMaximumSize(new java.awt.Dimension(32, 32));
        jLabel1.setMinimumSize(new java.awt.Dimension(24, 24));
        jLabel1.setPreferredSize(new java.awt.Dimension(32, 32));

        jTextField_Search.setText("Nhập thông tin tìm kiếm : .....");
        jTextField_Search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_SearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_SearchFocusLost(evt);
            }
        });
        jTextField_Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_SearchActionPerformed(evt);
            }
        });
        jTextField_Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_SearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_Search))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(321, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setRowHeaderView(null);

        jTable_XuLy.setAutoCreateRowSorter(true);
        jTable_XuLy.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTable_XuLy.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_XuLy.setGridColor(new java.awt.Color(204, 204, 204));
        jTable_XuLy.setRowHeight(50);
        jScrollPane1.setViewportView(jTable_XuLy);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_SearchFocusGained
        // TODO add your handling code here:
//        if (jTextField_Search.getText().equals("Nhập thông tin tìm kiếm : .....")) {
//            jTextField_Search.setText("");
//            jTextField_Search.setForeground(Color.BLACK);
//        }
    }//GEN-LAST:event_jTextField_SearchFocusGained

    private void jTextField_SearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_SearchFocusLost
//        // TODO add your handling code here:
//        if (jTextField_Search.getText().isEmpty()) {
//            jTextField_Search.setText("Nhập thông tin tìm kiếm : .....");
//            jTextField_Search.setForeground(Color.GRAY);
//        }
    }//GEN-LAST:event_jTextField_SearchFocusLost

    private void jTextField_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_SearchActionPerformed

    private void jTextField_SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SearchKeyReleased
        // TODO add your handling code here:
//        String searchText = jTextField_Search.getText().trim();
//
//        // Gọi hàm search với nội dung tìm kiếm
//        ArrayList<ThanhVienDTO> searchResult = tvBLL.search(searchText);
//
//        for(int i = modelTV.getRowCount()-1;i>=0;i--)
//        modelTV.removeRow(i);
//        for(int i = 0; i<searchResult.size();i++){
//            ThanhVienDTO em= searchResult.get(i);
//            int id= em.getMaTV();
//            String name= em.getHoTen();
//            String khoa = em.getKhoa();
//            String nganh = em.getNganh();
//            String sdt = em.getSDT();
//
//            Object[] row = {id,name,khoa,nganh,sdt};
//            modelTV.addRow(row);
//        }
    }//GEN-LAST:event_jTextField_SearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_XuLy;
    private javax.swing.JTextField jTextField_Search;
    // End of variables declaration//GEN-END:variables
}
