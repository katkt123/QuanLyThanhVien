/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ThanhVien;

import BLL.ThanhVienBLL;
import BLL.ThongTinSuDungBLL;
import DAL.ThanhVienDAL;
import DTO.ThanhVienDTO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class ThanhVienGUI extends javax.swing.JPanel {
    ThanhVienBLL tvBLL = new ThanhVienBLL();
    ThongTinSuDungBLL ttBLL= new ThongTinSuDungBLL();
    
    public static int id;
    public static String name;
    public static String khoa;
    public static String nganh;
    public static String sdt;
    /**
     * Creates new form ThanhVienGUI
     */
    ArrayList<ThanhVienDTO> arrThanhVien = new ArrayList<ThanhVienDTO>();
    
    DefaultTableModel modelTV = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // không cho phép chỉnh sửa giá trị các ô trong bảng
        }
    };
    public ThanhVienGUI() {
        initComponents();
        
        jTable_ThanhVien.setModel(modelTV);
        
        modelTV.addColumn("Mã Thành Viên");
        modelTV.addColumn("Họ Tên");
        modelTV.addColumn("Khoa");
        modelTV.addColumn("Ngành");
        modelTV.addColumn("Số Điện Thoại");
        
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setFont(new Font("Arial", Font.BOLD, 20)); // Set bold font
        headerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER); // Set center alignment
        
        for (int i = 0; i < jTable_ThanhVien.getColumnCount(); i++) {
            jTable_ThanhVien.getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Set center alignment
        
        for (int i = 0; i < jTable_ThanhVien.getColumnCount(); i++) {
            jTable_ThanhVien.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        
        loadThanhVien();
        setIconAdd();
        setIconEdit();
        setIconRefresh();
        setIconSearch();
        setIconDelete();
        setIconDelete1();
        setIconMuon();
        setIconVao();
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
    public void setIconDelete1(){
        String imagePath = "src\\main\\java\\Image\\DeleteMany.png"; // 
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
        jButton_Delete1.setIcon(icon);
    }
    public void setIconMuon(){
        String imagePath = "src\\main\\java\\Image\\borrowing.png"; // 
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        jButton_Muon.setIcon(icon);
    }
    public void setIconVao(){
        String imagePath = "src\\main\\java\\Image\\reading.png"; // 
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        jButton_VaoKhuVuc.setIcon(icon);
    }
    public void load(){
        loadThanhVien();
        System.out.print("refresh completely!!!!");
    }
    
    public void loadThanhVien(){
        arrThanhVien = tvBLL.listThanhVien();
//        int a = arrNCC.size();
        for(int i = modelTV.getRowCount()-1;i>=0;i--)
            modelTV.removeRow(i);
        for(int i = 0; i<arrThanhVien.size();i++){
            ThanhVienDTO em= arrThanhVien.get(i);
            
            int id= em.getMaTV();
            String name = em.getHoTen();
            String khoa = em.getKhoa();
            String nganh = em.getNganh();
            String sdt = em.getSDT();
            
        
            Object[] row = {id,name,khoa,nganh,sdt};
            modelTV.addRow(row);
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

        jPanel3 = new javax.swing.JPanel();
        jButton_Add = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField_Search = new javax.swing.JTextField();
        jButton_Edit = new javax.swing.JButton();
        jButton_Delete = new javax.swing.JButton();
        jButton_Refresh = new javax.swing.JButton();
        jButton_Delete1 = new javax.swing.JButton();
        jButton_Muon = new javax.swing.JButton();
        jButton_VaoKhuVuc = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_ThanhVien = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(930, 680));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButton_Add.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddActionPerformed(evt);
            }
        });

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
                .addComponent(jTextField_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
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

        jButton_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditActionPerformed(evt);
            }
        });

        jButton_Delete.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_DeleteActionPerformed(evt);
            }
        });

        jButton_Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RefreshActionPerformed(evt);
            }
        });

        jButton_Delete1.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Delete1ActionPerformed(evt);
            }
        });

        jButton_Muon.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Muon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MuonActionPerformed(evt);
            }
        });

        jButton_VaoKhuVuc.setForeground(new java.awt.Color(255, 255, 255));
        jButton_VaoKhuVuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_VaoKhuVucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jButton_VaoKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Muon, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Delete1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_Add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Edit, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(jButton_Refresh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(jButton_Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Delete1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Muon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_VaoKhuVuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setRowHeaderView(null);

        jTable_ThanhVien.setAutoCreateRowSorter(true);
        jTable_ThanhVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTable_ThanhVien.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_ThanhVien.setGridColor(new java.awt.Color(204, 204, 204));
        jTable_ThanhVien.setRowHeight(50);
        jScrollPane1.setViewportView(jTable_ThanhVien);

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

    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        // TODO add your handling code here:
        ModifyAddGUI atv = new ModifyAddGUI();
        atv.setVisible(true);
    }//GEN-LAST:event_jButton_AddActionPerformed

    private void jTextField_SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_SearchFocusGained
        // TODO add your handling code here:
        if (jTextField_Search.getText().equals("Nhập thông tin tìm kiếm : .....")) {
            jTextField_Search.setText("");
            jTextField_Search.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextField_SearchFocusGained

    private void jTextField_SearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_SearchFocusLost
        // TODO add your handling code here:
        if (jTextField_Search.getText().isEmpty()) {
            jTextField_Search.setText("Nhập thông tin tìm kiếm : .....");
            jTextField_Search.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_jTextField_SearchFocusLost

    private void jTextField_SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_SearchActionPerformed

    private void jTextField_SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SearchKeyReleased
        // TODO add your handling code here:
        String searchText = jTextField_Search.getText().trim();

        // Gọi hàm search với nội dung tìm kiếm
        ArrayList<ThanhVienDTO> searchResult = tvBLL.search(searchText);

        for(int i = modelTV.getRowCount()-1;i>=0;i--)
        modelTV.removeRow(i);
        for(int i = 0; i<searchResult.size();i++){
            ThanhVienDTO em= searchResult.get(i);
            int id= em.getMaTV();
            String name= em.getHoTen();
            String khoa = em.getKhoa();
            String nganh = em.getNganh();
            String sdt = em.getSDT();

            Object[] row = {id,name,khoa,nganh,sdt};
            modelTV.addRow(row);
        }
    }//GEN-LAST:event_jTextField_SearchKeyReleased

    private void jButton_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable_ThanhVien.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để sửa đổi.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            id = (int) jTable_ThanhVien.getValueAt(selectedRow, 0);
            name = jTable_ThanhVien.getValueAt(selectedRow, 1).toString();
            khoa = jTable_ThanhVien.getValueAt(selectedRow, 2).toString();
            nganh = jTable_ThanhVien.getValueAt(selectedRow, 3).toString();
            sdt = jTable_ThanhVien.getValueAt(selectedRow, 4).toString();
            UpdateThanhVienGUI utv = new UpdateThanhVienGUI(id,name,khoa,nganh,sdt);
            utv.setVisible(true);
        }
    }//GEN-LAST:event_jButton_EditActionPerformed

    private void jButton_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_DeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable_ThanhVien.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            id = (int) jTable_ThanhVien.getValueAt(selectedRow, 0);
            tvBLL.deleteThanhVien(id);
            JOptionPane.showMessageDialog(this, "Xóa thành công!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
           
        }
//       
    }//GEN-LAST:event_jButton_DeleteActionPerformed

    private void jButton_RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RefreshActionPerformed
        // TODO add your handling code here:
        loadThanhVien();
    }//GEN-LAST:event_jButton_RefreshActionPerformed

    private void jButton_Delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Delete1ActionPerformed
        // TODO add your handling code here:
        XoaNhieuGUI atv = new XoaNhieuGUI();
        atv.setVisible(true);
    }//GEN-LAST:event_jButton_Delete1ActionPerformed

    private void jButton_MuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MuonActionPerformed
        // TODO add your handling code here:
        MuonGUI mg=new MuonGUI();
        mg.setVisible(true);
        
    }//GEN-LAST:event_jButton_MuonActionPerformed

    private void jButton_VaoKhuVucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_VaoKhuVucActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable_ThanhVien.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thành viên vào khu vực học tập!!!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            id = (int) jTable_ThanhVien.getValueAt(selectedRow, 0);
            ttBLL.VaoKhuVucHocTap(id);
            JOptionPane.showMessageDialog(this, "Thành viên đã checkin thành công!!", "Thông báo", JOptionPane.WARNING_MESSAGE);
           
        }
    }//GEN-LAST:event_jButton_VaoKhuVucActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JButton jButton_Delete1;
    private javax.swing.JButton jButton_Edit;
    private javax.swing.JButton jButton_Muon;
    private javax.swing.JButton jButton_Refresh;
    private javax.swing.JButton jButton_VaoKhuVuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_ThanhVien;
    private javax.swing.JTextField jTextField_Search;
    // End of variables declaration//GEN-END:variables
}
