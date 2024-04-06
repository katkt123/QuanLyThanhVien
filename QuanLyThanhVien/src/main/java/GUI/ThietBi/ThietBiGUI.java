/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ThietBi;

import BLL.ThietBiBLL;
import DTO.ThanhVienDTO;
import DTO.ThietBiDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.io.File;
/**
 *
 * @author ASUS
 */
public class ThietBiGUI extends javax.swing.JPanel {
   
    /**
     * Creates new form ThietBiGUI
     */
    DefaultTableModel modelTB;
    
    ArrayList<ThietBiDTO> list = new ArrayList<ThietBiDTO>();; 
    
    ThietBiBLL thietBiBLL; 
    public int idTB;
    public String TenTB;
    public String MoTaTB;
    
    public ThietBiGUI() {
        initComponents();
        
        thietBiBLL = new ThietBiBLL();
        
        modelTB = (DefaultTableModel) jTableThietBi.getModel();
       
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setFont(new Font("Arial", Font.BOLD, 20)); // Set bold font
        headerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER); // Set center alignment

        for (int i = 0; i < jTableThietBi.getColumnCount(); i++) {
            jTableThietBi.getTableHeader().getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Set center alignment

        CheckBoxRenderer checkBoxRenderer = new CheckBoxRenderer();
        for (int i = 1; i < jTableThietBi.getColumnCount(); i++) {
            if (jTableThietBi.getColumnClass(i) == Boolean.class) {
                jTableThietBi.getColumnModel().getColumn(i).setCellRenderer(checkBoxRenderer);
            } else {
                jTableThietBi.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
            }
        }

        loadThietBi();
        
        setIconAdd();
        setIconEdit();
        setIconDelete();
        setIconRefresh();
        setIconExcel();
        

        
    }
    public void setIconAdd(){
        String imagePath = "src\\main\\java\\Image\\Add.png"; // 
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        btnAdd.setIcon(icon);
    }
    public void setIconEdit(){
        String imagePath = "src\\main\\java\\Image\\Edit.png"; // 
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        btnEdit.setIcon(icon);
    }
   
    public void setIconDelete(){
        String imagePath = "src\\main\\java\\Image\\Delete.png"; // 
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH));
        btnDelete.setIcon(icon);
    }
    public void setIconRefresh(){
        String imagePath = "src\\main\\java\\Image\\Refresh.png"; // 
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        btnRefresh.setIcon(icon);
    }
    public void setIconExcel(){
        String imagePath = "src\\main\\java\\Image\\Excel.png"; // 
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        btnExcel.setIcon(icon);
    }
    
    
    public void loadThietBi() {
        modelTB.setRowCount(0);
        list = thietBiBLL.getListTB();
        for (ThietBiDTO t : list) {
            Object[] row = {false,t.getMaTB(),t.getTenTB(),t.getMoTaTB()};
            modelTB.addRow(row);
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

        jLabel3 = new javax.swing.JLabel();
        jpTaskbar = new javax.swing.JPanel();
        txtFindTen = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtFindMa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        btnChonALL = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbxLoai = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableThietBi = new javax.swing.JTable();

        jLabel3.setText("jLabel3");

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(930, 680));

        jpTaskbar.setBackground(new java.awt.Color(255, 255, 255));

        txtFindTen.setText("Nhập tên thiết bị");
        txtFindTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFindTenFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFindTenFocusLost(evt);
            }
        });
        txtFindTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindTenActionPerformed(evt);
            }
        });
        txtFindTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindTenKeyReleased(evt);
            }
        });

        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        txtFindMa.setText("Nhập mã thiết bị");
        txtFindMa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFindMaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFindMaFocusLost(evt);
            }
        });
        txtFindMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindMaActionPerformed(evt);
            }
        });
        txtFindMa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindMaKeyReleased(evt);
            }
        });

        jLabel1.setText("Tên thiết bị:");

        jLabel2.setText("Mã thiết bị:");

        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        btnChonALL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChonALL.setText("Chọn tất cả");
        btnChonALL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonALLActionPerformed(evt);
            }
        });

        jLabel4.setText("Loại thiết bị:");

        cbxLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Micro", "Máy chiếu", "Máy ảnh", "Cassette", "Tivi", "Quạt đứng" }));
        cbxLoai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxLoaiMouseClicked(evt);
            }
        });
        cbxLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLoaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpTaskbarLayout = new javax.swing.GroupLayout(jpTaskbar);
        jpTaskbar.setLayout(jpTaskbarLayout);
        jpTaskbarLayout.setHorizontalGroup(
            jpTaskbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTaskbarLayout.createSequentialGroup()
                .addGroup(jpTaskbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTaskbarLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jpTaskbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(30, 30, 30)
                        .addGroup(jpTaskbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFindMa, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFindTen, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 268, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTaskbarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnChonALL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnRefresh)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcel)
                        .addGap(34, 34, 34)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jpTaskbarLayout.setVerticalGroup(
            jpTaskbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTaskbarLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jpTaskbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTaskbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindMa, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTaskbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTaskbarLayout.createSequentialGroup()
                        .addComponent(txtFindTen, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpTaskbarLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39)))
                .addComponent(btnChonALL))
            .addGroup(jpTaskbarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jpTaskbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpTaskbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTableThietBi.setAutoCreateRowSorter(true);
        jTableThietBi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTableThietBi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "Mã", "Tên", "Mô tả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableThietBi.setMaximumSize(new java.awt.Dimension(2147483647, 200));
        jTableThietBi.setMinimumSize(new java.awt.Dimension(90, 200));
        jTableThietBi.setRowHeight(50);
        jScrollPane2.setViewportView(jTableThietBi);
        if (jTableThietBi.getColumnModel().getColumnCount() > 0) {
            jTableThietBi.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTableThietBi.getColumnModel().getColumn(0).setMaxWidth(70);
            jTableThietBi.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTableThietBi.getColumnModel().getColumn(1).setMaxWidth(150);
            jTableThietBi.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTableThietBi.getColumnModel().getColumn(2).setMaxWidth(250);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTaskbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpTaskbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        ThemThietBi themtb = new ThemThietBi();
        themtb.setVisible(true);
        themtb.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                loadThietBi();
            }
        });
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTableThietBi.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để sửa đổi.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            idTB = (int) jTableThietBi.getValueAt(selectedRow, 1);
            TenTB = jTableThietBi.getValueAt(selectedRow, 2).toString();
//            MoTaTB = jTableThietBi.getValueAt(selectedRow, 3).toString();
            Object cellValue = jTableThietBi.getValueAt(selectedRow, 3);
            String MoTaTB = (cellValue != null) ? cellValue.toString() : "";
            SuaThietBi SuaTB = new SuaThietBi(idTB,TenTB,MoTaTB);
            SuaTB.setVisible(true);
        }
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(jTableThietBi, thietBiBLL.XoaTB(modelTB));
        loadThietBi();
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtFindTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindTenActionPerformed

    private void txtFindMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindMaActionPerformed

    private void txtFindMaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFindMaFocusGained
        // TODO add your handling code here:
        if (txtFindMa.getText().equals("Nhập mã thiết bị")){
            txtFindMa.setText("");
        }
    }//GEN-LAST:event_txtFindMaFocusGained

    private void txtFindMaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFindMaFocusLost
        // TODO add your handling code here:
        if (txtFindMa.getText().equals("")){
            txtFindMa.setText("Nhập mã thiết bị");
        }
        
    }//GEN-LAST:event_txtFindMaFocusLost

    private void txtFindTenFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFindTenFocusGained
        // TODO add your handling code here:
        if (txtFindTen.getText().equals("Nhập tên thiết bị")){
            txtFindTen.setText("");
        }
    }//GEN-LAST:event_txtFindTenFocusGained

    private void txtFindTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFindTenFocusLost
        // TODO add your handling code here:
        if (txtFindTen.getText().equals("")){
            txtFindTen.setText("Nhập tên thiết bị");
        }
    }//GEN-LAST:event_txtFindTenFocusLost

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        cbxLoai.setSelectedIndex(0);
        loadThietBi();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtFindMaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindMaKeyReleased
        // TODO add your handling code here:
        int Selected = cbxLoai.getSelectedIndex();
        if (Selected != 0){
            thietBiBLL.search(thietBiBLL.getListSearch(Integer.toString(Selected)),modelTB, txtFindMa.getText(), txtFindTen.getText());
        }
        else{
            thietBiBLL.search(list,modelTB, txtFindMa.getText(), txtFindTen.getText());
        }
        
    }//GEN-LAST:event_txtFindMaKeyReleased

    private void txtFindTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindTenKeyReleased
        // TODO add your handling code here:'
        int Selected = cbxLoai.getSelectedIndex();
        if (Selected != 0){
            thietBiBLL.search(thietBiBLL.getListSearch(Integer.toString(Selected)),modelTB, txtFindMa.getText(), txtFindTen.getText());
        }
        else{
            thietBiBLL.search(list,modelTB, txtFindMa.getText(), txtFindTen.getText());
        }
    }//GEN-LAST:event_txtFindTenKeyReleased

    private void btnChonALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonALLActionPerformed
        // TODO add your handling code here:
        if (btnChonALL.getText().equals("Chọn tất cả")){
            for (int i = 0; i < modelTB.getRowCount();i++){
                modelTB.setValueAt(true, i, 0);
            }
            btnChonALL.setText("Bỏ chọn tất cả");
        }
        else{
            for (int i = 0; i < modelTB.getRowCount();i++){
                modelTB.setValueAt(false, i, 0);
            }
            btnChonALL.setText("Chọn tất cả");    
        }
    }//GEN-LAST:event_btnChonALLActionPerformed

    private void cbxLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLoaiActionPerformed
        // TODO add your handling code here:
        int Selected = cbxLoai.getSelectedIndex();
        txtFindMa.setText("Nhập mã thiết bị");
        txtFindTen.setText("Nhập tên thiết bị");
        if (Selected != 0){
            ArrayList<ThietBiDTO> list_temp = thietBiBLL.getListSearch(Integer.toString(Selected));
           
            modelTB.setRowCount(0);
            
            for (ThietBiDTO t : list_temp) {
                Object[] row = {false,t.getMaTB(),t.getTenTB(),t.getMoTaTB()};
                modelTB.addRow(row);
            }
        }
        else{
            loadThietBi();
        }
        
        
    }//GEN-LAST:event_cbxLoaiActionPerformed

    private void cbxLoaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxLoaiMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxLoaiMouseClicked

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getName();
            if (isExcelFile(filePath)){
                System.out.println("true");
            }
        }
    }//GEN-LAST:event_btnExcelActionPerformed
    // Phương thức để kiểm tra xem tập tin có phải là tập tin Excel (.xlsx) không
    public static boolean isExcelFile(String filePath) {
        // Kiểm tra phần mở rộng của tập tin
        String extension = getFileExtension(filePath);
        return extension != null && extension.equals("xlsx");
    }
    
    // Phương thức để lấy phần mở rộng của tập tin
    public static String getFileExtension(String filePath) {
        String extension = null;
        int lastDotIndex = filePath.lastIndexOf(".");
        if (lastDotIndex > 0) {
            extension = filePath.substring(lastDotIndex + 1);
        }
        return extension;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChonALL;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JComboBox<String> cbxLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableThietBi;
    private javax.swing.JPanel jpTaskbar;
    private javax.swing.JTextField txtFindMa;
    private javax.swing.JTextField txtFindTen;
    // End of variables declaration//GEN-END:variables
}
