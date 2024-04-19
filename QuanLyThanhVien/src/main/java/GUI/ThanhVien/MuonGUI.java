/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.ThanhVien;

import BLL.ThanhVienBLL;
import BLL.ThietBiBLL;
import BLL.ThongTinSuDungBLL;
import BLL.XuLyViPhamBLL;
import DTO.ThanhVienDTO;
import DTO.ThietBiDTO;
import DTO.ThongTinSuDungDTO;
import GUI.MainGUI;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class MuonGUI extends javax.swing.JFrame {
    ThanhVienBLL tvBLL = new ThanhVienBLL();
    ThietBiBLL tbBLL= new ThietBiBLL();
    ThongTinSuDungBLL ttBLL=new ThongTinSuDungBLL();
    XuLyViPhamBLL xlBLL = new XuLyViPhamBLL();
    
    ArrayList<ThongTinSuDungDTO> list = new ArrayList<>();
    /**
     * Creates new form MuonGUI
     */
    public MuonGUI() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(MainGUI.DISPOSE_ON_CLOSE);
        jLabel4.setHorizontalAlignment(jLabel4.CENTER); // Đưa chữ về giữa theo chiều ngang
//        jLabel2.setHorizontalAlignment(jLabel2.CENTER); // Đưa chữ về giữa theo chiều ngang
//        jLabel3.setHorizontalAlignment(jLabel3.CENTER); // Đưa chữ về giữa theo chiều ngang
//        jLabel4.setHorizontalAlignment(jLabel4.CENTER); // Đưa chữ về giữa theo chiều ngang
//        jLabel4.setHorizontalAlignment(jLabel5.CENTER); // Đưa chữ về giữa theo chiều ngang
        
        list = ttBLL.listThongTinSuDung();
        
        String[] items1 = {};
        String[] items = {};
        
//        Thành viên
        DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<>(items1);
        jComboBox_ThanhVien.setModel(model1);
        ArrayList<ThanhVienDTO> arr = tvBLL.listThanhVien();
        for(ThanhVienDTO thanhVien : arr){
            String hoTen = thanhVien.getHoTen();
            int maTV = thanhVien.getMaTV(); // Giả sử có phương thức để lấy mã thành viên
            String matv= String.valueOf(maTV);
            String combinedString = hoTen + " - " + matv; // Ghép chuỗi
            model1.addElement(combinedString); // Thêm chuỗi đã ghép vào model
        }
//        Thiết bị
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(items);
        jComboBox_ThietBi.setModel(model);
        ArrayList<ThietBiDTO> arr1 = tbBLL.getListTB();
        for(ThietBiDTO thietBi : arr1){
            String hoTen = thietBi.getTenTB();
            int maTB = thietBi.getMaTB(); // Giả sử có phương thức để lấy mã thành viên
            String matv= String.valueOf(maTB);
            String combinedString1 = hoTen + " - " + matv; // Ghép chuỗi
            if (new ThietBiBLL().CheckMuon(maTB)==0){
                model.addElement(combinedString1); // Thêm chuỗi đã ghép vào model
            }
        }
        
        
        
        
    }
    public boolean checkDate() {
        Date muonDate = jDateChooser_tgmuon.getDate();
        Date traDate = jDateChooser_tgtra.getDate();
        return muonDate.before(traDate);
    }
    public boolean checkInit(){
        if(jComboBox_ThanhVien.getSelectedItem()!=null && jComboBox_ThietBi.getSelectedItem()!=null && jDateChooser_tgmuon.getDate()!=null && jDateChooser_tgtra.getDate()!=null){
            return true;
        }
        return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton_Create = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_ThanhVien = new javax.swing.JComboBox<>();
        jComboBox_ThietBi = new javax.swing.JComboBox<>();
        jDateChooser_tgmuon = new com.toedter.calendar.JDateChooser();
        jDateChooser_tgtra = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Thời Gian Mượn");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Thiết Bị");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Họ Tên");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("MƯỢN THIẾT BỊ");

        jButton_Create.setBackground(new java.awt.Color(153, 255, 153));
        jButton_Create.setText("Mượn");
        jButton_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CreateActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Thời Gian Trả");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBox_ThanhVien.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_ThanhVienItemStateChanged(evt);
            }
        });
        jComboBox_ThanhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_ThanhVienActionPerformed(evt);
            }
        });

        jComboBox_ThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_ThietBiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jButton_Create, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_ThanhVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox_ThietBi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser_tgmuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser_tgtra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_ThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_ThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser_tgmuon, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser_tgtra, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addComponent(jButton_Create, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CreateActionPerformed
        // TODO add your handling code here:
        if(checkInit()){
            if (checkDate()) {
            int id=0;
            String name= jComboBox_ThanhVien.getSelectedItem().toString();
            String thietbi = jComboBox_ThietBi.getSelectedItem().toString();
            Date tgmuon= jDateChooser_tgmuon.getDate();
            Date tgtra=jDateChooser_tgtra.getDate();
            String[] parts = name.split(" - ");
            int idtv= Integer.parseInt(parts[1]);
            ThanhVienDTO idTV= tvBLL.getThanhVienByID(idtv);
            
            String[] part = thietbi.split(" - ");
            int idtb= Integer.parseInt(part[1]);
            ThietBiDTO idTB= tbBLL.getThietBiById(idtb);
            if(xlBLL.checkVP(idtv)){
                    ThongTinSuDungDTO tt = new ThongTinSuDungDTO(id,idTV,idTB,tgmuon,tgtra);
                    ttBLL.MuonThietBi(tt);
                    JOptionPane.showMessageDialog(null, "Mượn thành công !!!", "Mượn Thiết Bị", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Thành viên đang thuộc biên chế xử lý. KHÔNG THỂ MƯỢN THIẾT BỊ!!!!", "Mượn Thiết Bị", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Thời gian trả vui lòng lớn hơn thời gian mượn !!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);            
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin !!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);            
        }
        
        
    }//GEN-LAST:event_jButton_CreateActionPerformed

    private void jComboBox_ThanhVienItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_ThanhVienItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox_ThanhVienItemStateChanged

    private void jComboBox_ThanhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ThanhVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_ThanhVienActionPerformed

    private void jComboBox_ThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ThietBiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_ThietBiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MuonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MuonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MuonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MuonGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MuonGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Create;
    private javax.swing.JComboBox<String> jComboBox_ThanhVien;
    private javax.swing.JComboBox<String> jComboBox_ThietBi;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser_tgmuon;
    private com.toedter.calendar.JDateChooser jDateChooser_tgtra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
