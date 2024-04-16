/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.XuLyViPham;

import BLL.XuLyViPhamBLL;
import DTO.ThanhVienDTO;
import DTO.XuLyViPhamDTO;
import static GUI.ThanhVien.ThanhVienGUI.id;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
        
        jTable_XuLy.setModel(modelXL);
        
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
        setIconAdd();
        setIconEdit();
        setIconRefresh();
        setIconSearch();
        setIconDelete();
        setIconCheck();
    }
    
        public void setIconAdd(){
            String imagePath = "src\\main\\java\\Image\\Add.png"; // 
            ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
            jButton_Add.setIcon(icon);
        }
        public void setIconEdit(){
            String imagePath = "src\\main\\java\\Image\\Edit.png"; // 
            ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
            jButton_Edit.setIcon(icon);
        }
        public void setIconRefresh(){
            String imagePath = "src\\main\\java\\Image\\Refresh.png"; // 
            ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
            jButton_Refresh.setIcon(icon);
        }
        public void setIconSearch(){
            String imagePath = "src\\main\\java\\Image\\Search.png"; // 
            ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
            jLabel1.setIcon(icon);
        }
        public void setIconDelete(){
            String imagePath = "src\\main\\java\\Image\\Delete.png"; // 
            ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
            jButton_Delete.setIcon(icon);
        }
        
        public void setIconCheck(){
            String imagePath = "src\\main\\java\\Image\\check.png"; // 
            ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
            jButton1.setIcon(icon);
        }
    public void loadXuLyViPham(){

        arrXuLy = xlvpBLL.listxlvp();

        // Xóa tất cả các dòng hiện có trong bảng
        modelXL.setRowCount(0);

        for(int i = 0; i < arrXuLy.size(); i++){
            XuLyViPhamDTO em = arrXuLy.get(i);

            int idXL = em.getMaXL();
            ThanhVienDTO thanhvien = em.getMaTV();
            String hinhthuc = em.getHinhThucXL();
            int sotien = em.getSoTien();
            Date ngayxl = em.getNgayXL();
            int trangthai = em.getTrangThaiXL();

            Object[] row = {idXL, thanhvien.getMaTV(), hinhthuc, sotien, ngayxl, trangthai};
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
        jButton_Add = new javax.swing.JButton();
        jButton_Edit = new javax.swing.JButton();
        jButton_Refresh = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_XuLy = new javax.swing.JTable();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setMaximumSize(new java.awt.Dimension(32, 32));
        jLabel1.setMinimumSize(new java.awt.Dimension(24, 24));
        jLabel1.setPreferredSize(new java.awt.Dimension(32, 32));

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

        jButton_Add.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddActionPerformed(evt);
            }
        });

        jButton_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditActionPerformed(evt);
            }
        });

        jButton_Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RefreshActionPerformed(evt);
            }
        });

        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton_Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                "Mã xử lý", "Mã thành viên", "Hình thức xử lý", "Số tiền","Ngày xử lý", "Trạng thái xử lý"
            }
        ));
        jTable_XuLy.setGridColor(new java.awt.Color(204, 204, 204));
        jTable_XuLy.setRowHeight(50);
        jScrollPane1.setViewportView(jTable_XuLy);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
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
        String searchText = jTextField_Search.getText().trim();

        // Gọi hàm search với nội dung tìm kiếm
        ArrayList<XuLyViPhamDTO> searchResult = xlvpBLL.search(searchText);

        for(int i = modelXL.getRowCount()-1;i>=0;i--)
        modelXL.removeRow(i);
        for(int i = 0; i<searchResult.size();i++){
            XuLyViPhamDTO em= searchResult.get(i);

            int idXL = em.getMaXL();
            ThanhVienDTO thanhvien = em.getMaTV();
            String hinhthuc = em.getHinhThucXL();
            int sotien = em.getSoTien();
            Date ngayxl = em.getNgayXL();
            int trangthai = em.getTrangThaiXL();

            Object[] row = {idXL, thanhvien.getMaTV(), hinhthuc, sotien, ngayxl, trangthai};
            modelXL.addRow(row);
        }
    }//GEN-LAST:event_jTextField_SearchKeyReleased

    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        // TODO add your handling code here:
        AddHinhThucXuLyGUI ahtxl = new AddHinhThucXuLyGUI();
        ahtxl.setVisible(true);
    }//GEN-LAST:event_jButton_AddActionPerformed

    private int layMaXuLyDuocChon() {
        int rowIndex = jTable_XuLy.getSelectedRow();
        if (rowIndex != -1) { // Kiểm tra xem có hàng nào được chọn không
            // Lấy giá trị ở cột đầu tiên (cột chứa mã thành viên) của hàng được chọn
            return (int) jTable_XuLy.getValueAt(rowIndex, 0);
        } else {
            // Nếu không có hàng nào được chọn, trả về giá trị mặc định (ví dụ: -1)
            return -1;
        }
    }
    private void jButton_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditActionPerformed
        // TODO add your handling code here:
        int maXuLy = layMaXuLyDuocChon();
        if (maXuLy != -1) {
            EditHinhThucXuLyGUI exl = new EditHinhThucXuLyGUI();
            exl.setXuLy(maXuLy);
            exl.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để sửa đổi.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton_EditActionPerformed

    private void jButton_RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RefreshActionPerformed
        // TODO add your handling code here:
        loadXuLyViPham();
    }//GEN-LAST:event_jButton_RefreshActionPerformed

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable_XuLy.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int maXL = (int) jTable_XuLy.getValueAt(selectedRow, 0);
        int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa xử lý vi phạm này không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            id = (int) jTable_XuLy.getValueAt(selectedRow, 0);
            xlvpBLL.xoaXuLyViPham(id);
            loadXuLyViPham();
        }
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable_XuLy.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int maXL = (int) jTable_XuLy.getValueAt(selectedRow, 0);
//        trangthai = jTable_XuLy.getSelectedRow();
        int trangthai = (int) jTable_XuLy.getValueAt(selectedRow, 5);
        if (trangthai == 1){
            JOptionPane.showMessageDialog(this, "Đã xử lí vi phạm này, vui lòng chọn vi phạm khác.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }else{
            int trangthaixl = 1;

        
        XuLyViPhamDTO xuLyViPhamDTO = new XuLyViPhamDTO();
        xuLyViPhamDTO.setMaXL(maXL);
        xuLyViPhamDTO.setTrangThaiXL(trangthaixl);
        
        xlvpBLL.updateXuLyTrangThai(xuLyViPhamDTO);
        
//        if (updated) {
        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
        loadXuLyViPham();
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_Edit;
    private javax.swing.JButton jButton_Refresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_XuLy;
    private javax.swing.JTextField jTextField_Search;
    // End of variables declaration//GEN-END:variables
}
