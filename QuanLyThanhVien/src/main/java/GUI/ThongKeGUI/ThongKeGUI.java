/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ThongKeGUI;

import BLL.ThanhVienBLL;
import BLL.ThongTinSuDungBLL;
import DTO.ThanhVienDTO;
import DTO.ThongTinSuDungDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author HP
 */
public class ThongKeGUI extends javax.swing.JPanel {
    ThongTinSuDungBLL tinSuDungBLL;
    ThanhVienBLL tvBLL;

    /**
     * Creates new form ThongKeGUI
     */
    public ThongKeGUI() {
        initComponents();
        setBackground(new Color(255, 255, 255));
        
        tinSuDungBLL = new ThongTinSuDungBLL();
        ArrayList<Object[]> dataList = new ArrayList<>();
        for (ThongTinSuDungDTO t: tinSuDungBLL.listThongTinSuDung()) {       
            dataList.add(new Object[]{t.getMaTT(), 
                t.getMaTV().getMaTV(), 
                t.getMaTB() != null ? t.getMaTB().getMaTB():"NULL", 
                t.getTGVao() != null ? t.getTGVao():"NULL", 
                t.getTGMuon() != null ? t.getTGMuon():"NULL", 
                t.getTGTra() != null ? t.getTGTra():"NULL"});           
        }
        
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("MaTT");
        model.addColumn("MaTV");
        model.addColumn("MaTB");
        model.addColumn("Vào");
        model.addColumn("Mượn");
        model.addColumn("Trả");
        
        for (Object[] row : dataList) {
            model.addRow(row);
        }
        jTable1 = new JTable( model )
        {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);
                Object cellValue = jTable1.getValueAt(row, column);
                //  Alternate row color

                if (cellValue == "NULL") {
                    c.setForeground(Color.RED);
                } else {
                    // Thiết lập màu nền mặc định cho các ô khác
                    c.setForeground(jTable1.getForeground());
                }
                return c;
            }
        };
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setViewportView(jTable1);
        jPanel2.add(jScrollPane1, BorderLayout.CENTER);
        
        int SP_KHXH = 0;
        int SP_KHTN = 0;
        int NGOAI_NGU = 0;
        int QTKD = 0;
        int QLGD = 0;
        int TOAN_UD = 0;
        int CNTT = 0;
        tvBLL= new ThanhVienBLL();
        
        for (ThongTinSuDungDTO t: tinSuDungBLL.listThongTinSuDung()) {       
            if (t.getMaTV() == null)
                continue;
            String khoa = tvBLL.getThanhVienByID(t.getMaTV().getMaTV()).getKhoa();
             switch (khoa.toUpperCase(Locale.ROOT)) {
                case "SP KHXH":
                    SP_KHXH += 1;
                    break;
                case "SP KHTN":
                    SP_KHTN += 1;
                    break;
                case "NGOẠI NGỮ":
                    NGOAI_NGU += 1;
                    break;
                case "QTKD":
                    QTKD += 1;
                    break;
                case "QLGD":
                    QLGD += 1;
                    break;
                case "TOÁN UD":
                    TOAN_UD += 1;
                    break;
                case "CNTT":
                    CNTT += 1;
                    break;
                default:
                    break;
            }
        }
        
        pieChart1.setPreferredSize(new Dimension(0, 400));
        pieChart1.setChartType(PieChart.PeiChartType.DONUT_CHART);
        if (SP_KHXH > 0)
            pieChart1.addData(new ModelPieChart("SP KHXH", SP_KHXH, new Color(23, 126, 238)));
        if (SP_KHTN > 0)
            pieChart1.addData(new ModelPieChart("SP KHTN", SP_KHTN, new Color(221, 65, 65)));
        if (NGOAI_NGU > 0)
            pieChart1.addData(new ModelPieChart("NGOẠI NGỮ", NGOAI_NGU, new Color(47, 157, 64)));
        if (QTKD > 0)
            pieChart1.addData(new ModelPieChart("QTKD", QTKD, new Color(187,78,255)));
        if (QLGD > 0)
            pieChart1.addData(new ModelPieChart("QLGD", QLGD, new Color(255,144,18)));
        if (TOAN_UD > 0)
            pieChart1.addData(new ModelPieChart("TOÁN UD", TOAN_UD, new Color(254,0,230)));
        if (CNTT > 0)
            pieChart1.addData(new ModelPieChart("CNTT", CNTT, new Color(46,204,217)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pieChart1 = new GUI.ThongKeGUI.PieChart();
        jPanel2 = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());
        add(pieChart1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());
        add(jPanel2, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private GUI.ThongKeGUI.PieChart pieChart1;
    // End of variables declaration//GEN-END:variables
    private JTable jTable1;
    private JScrollPane jScrollPane1;
}
