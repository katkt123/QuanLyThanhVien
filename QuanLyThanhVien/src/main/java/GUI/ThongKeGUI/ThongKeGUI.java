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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import raven.chart.ModelChart;

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
    private static final Map<String, Color> khoaColors = new HashMap<>();
    static {
        khoaColors.put("SP KHXH", new Color(23, 126, 238));
        khoaColors.put("SP KHTN", new Color(221, 65, 65));
        khoaColors.put("NGOẠI NGỮ", new Color(47, 157, 64));
        khoaColors.put("QTKD", new Color(187, 78, 255));
        khoaColors.put("QLGD", new Color(255, 144, 18));
        khoaColors.put("TOÁN UD", new Color(254, 0, 230));
        khoaColors.put("CNTT", new Color(46, 204, 217));
    }
    private static final Color[] COLOR_OPTIONS = {
        new Color(255, 0, 0),   // Đỏ
        new Color(47, 157, 64),   // Xanh lá cây
        new Color(0, 0, 255),   // Xanh dương
        new Color(255, 255, 0)  // Vàng
        // Các màu khác tùy chọn có thể thêm vào đây
    };

    // Hàm để lấy màu cho từng loại khoa
    public static Color getColorForKhoa(String khoa) {
        // Nếu Map chứa loại khoa này, trả về màu tương ứng
        // Nếu không, trả về màu mặc định hoặc bạn có thể xử lý tùy ý
        return khoaColors.getOrDefault(khoa, Color.BLACK); // Màu mặc định là đen
    }
    public ThongKeGUI(JPanel parent) {
        
        initComponents();
        tinSuDungBLL = new ThongTinSuDungBLL();
        tvBLL = new ThanhVienBLL();
        ArrayList<Object[]> dataList = new ArrayList<>();
        for (ThongTinSuDungDTO t: tinSuDungBLL.listThongTinSuDung()) {       
            dataList.add(new Object[]{t.getMaTT(), 
                t.getMaTV().getMaTV(), 
                t.getMaTB() != null ? t.getMaTB().getMaTB():"NULL", 
                t.getTGVao() != null ? t.getTGVao():"NULL", 
                t.getTGMuon() != null ? t.getTGMuon():"NULL", 
                t.getTGTra() != null ? t.getTGTra():"NULL"});           
        }
        
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all cells non-editable
                return false;
            }
        };
        
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
        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel3.setBackground(new Color(255, 255, 255)); 
        pieChart1.setPreferredSize(new Dimension(300, 300));
        pieChart2.setPreferredSize(new Dimension(300, 300));
        pieChart1.setChartType(PieChart.PeiChartType.DONUT_CHART);
        pieChart2.setChartType(PieChart.PeiChartType.DEFAULT);
        
        Map<String, Integer> khoaCounts = new HashMap<>();
        // Lặp qua danh sách thông tin sử dụng và cập nhật số lượng của từng loại khoa trong Map
        for (ThongTinSuDungDTO t : tinSuDungBLL.listThongTinSuDung()) {
            String khoa = tvBLL.getThanhVienByID(t.getMaTV().getMaTV()).getKhoa();
            khoa = khoa.toUpperCase(Locale.ROOT);
            khoaCounts.put(khoa, khoaCounts.getOrDefault(khoa, 0) + 1);
        }

        // Lặp qua Map và thêm dữ liệu vào biểu đồ
        for (Map.Entry<String, Integer> entry : khoaCounts.entrySet()) {
            String khoa = entry.getKey();
            int count = entry.getValue();
            Color color = getColorForKhoa(khoa); 
            pieChart1.addData(new ModelPieChart(khoa, count, color));
        }
        pieChart1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // Xử lý sự kiện khi chuột được click
                System.out.println("Mouse clicked! " +  pieChart1.getData());
                CallPieChart(pieChart1.getData());
            }
        });
        CallPieChart(pieChart1.getFirstData());
        System.out.println("GUI.ThongKe" + (parent.getHeight() - pieChart1.getHeight()));
        curveLineChart1.setPreferredSize(new Dimension(0,parent.getHeight() - 300 - 70));
        
        curveLineChart1.addLegend("Amount", Color.decode("#7b4397"), Color.decode("#dc2430"));
        curveLineChart1.addLegend("Cost", Color.decode("#e65c00"), Color.decode("#F9D423"));
        curveLineChart1.addLegend("Profit", Color.decode("#0099F7"), Color.decode("#F11712"));
        
        curveLineChart1.clear();
        curveLineChart1.addData(new ModelChart("January", new double[]{500, 50, 100}));
        curveLineChart1.addData(new ModelChart("February", new double[]{600, 300, 150}));
        curveLineChart1.addData(new ModelChart("March", new double[]{200, 50, 900}));
        curveLineChart1.addData(new ModelChart("April", new double[]{480, 700, 100}));
        curveLineChart1.start();
        
        
        
        
        
        Map<String, Integer> MuonCounts = new HashMap<>();
        pieChart3.setPreferredSize(new Dimension(400, 400));
        pieChart3.setChartType(PieChart.PeiChartType.DONUT_CHART);
        // Lặp qua danh sách thông tin sử dụng và cập nhật số lượng của từng loại khoa trong Map
        for (ThongTinSuDungDTO t : tinSuDungBLL.listThongTinSuDung()) {
            if (t.getMaTB() == null) continue;
            String khoa = tvBLL.getThanhVienByID(t.getMaTV().getMaTV()).getKhoa();
            khoa = khoa.toUpperCase(Locale.ROOT);
            MuonCounts.put(khoa, MuonCounts.getOrDefault(khoa, 0) + 1);
        }

        // Lặp qua Map và thêm dữ liệu vào biểu đồ
        for (Map.Entry<String, Integer> entry : MuonCounts.entrySet()) {
            String khoa = entry.getKey();
            int count = entry.getValue();
            Color color = getColorForKhoa(khoa); 
            pieChart3.addData(new ModelPieChart(khoa, count, color));
        }
    }
    private void CallPieChart(String khoa) {
        if (khoa == null) return;
        pieChart2.clearData();

        // Tạo Map ánh xạ từng loại khoa tới danh sách các ngành
        Map<String, List<String>> nganhKhoaMap = new HashMap<>();
        nganhKhoaMap.put("SP KHXH", List.of("ĐỊA", "SỬ", "VĂN"));
        nganhKhoaMap.put("SP KHTN", List.of("LÍ", "HÓA", "SINH"));
        nganhKhoaMap.put("NGOẠI NGỮ", List.of("ANH", "NNA"));
        nganhKhoaMap.put("QTKD", List.of("QTKD"));
        nganhKhoaMap.put("QLGD", List.of("TLH", "QLGD"));
        nganhKhoaMap.put("TOÁN UD", List.of("TOÁN", "TOÁN UD"));
        nganhKhoaMap.put("CNTT", List.of("CNTT", "KTPM", "HTTT"));

        // Lấy danh sách ngành tương ứng với loại khoa
        List<String> nganhList = nganhKhoaMap.getOrDefault(khoa.toUpperCase(Locale.ROOT), List.of());

        // Mảng màu cho các ngành
        Color khoaColor = getColorForKhoa(khoa); // Màu của khoa đầu vào
        Color[] color_pie = new Color[4];
        color_pie[0] = khoaColor;

        // Tạo mảng màu cho các ngành khác không trùng với màu của khoa đầu vào
        int colorIndex = 1;
        for (Color color : COLOR_OPTIONS) {
            if (!color.equals(khoaColor)) {
                color_pie[colorIndex++] = color;
                if (colorIndex >= 4) break;
            }
        }

        // Lặp qua danh sách ngành và thêm dữ liệu vào biểu đồ
        for (int i = 0; i < nganhList.size(); i++) {
            String nganh = nganhList.get(i);
            int dem = 0;
            for (ThongTinSuDungDTO t : tinSuDungBLL.listThongTinSuDung()) {
                String nganhTV = tvBLL.getThanhVienByID(t.getMaTV().getMaTV()).getNganh();
                if (nganhTV.toUpperCase(Locale.ROOT).equals(nganh)) {
                    dem++;
                }
            }
            if (dem > 0)
                pieChart2.addData(new ModelPieChart(nganh, dem, color_pie[i]));
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        pieChart1 = new GUI.ThongKeGUI.PieChart();
        pieChart2 = new GUI.ThongKeGUI.PieChart();
        panelShadow1 = new raven.panel.PanelShadow();
        curveLineChart1 = new raven.chart.CurveLineChart();
        jPanel3 = new javax.swing.JPanel();
        pieChart3 = new GUI.ThongKeGUI.PieChart();
        jPanel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        pieChart1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(pieChart1);
        jPanel4.add(pieChart2);

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        panelShadow1.setBackground(new java.awt.Color(34, 59, 69));
        panelShadow1.setColorGradient(new java.awt.Color(17, 38, 47));
        panelShadow1.setLayout(new java.awt.BorderLayout());

        curveLineChart1.setForeground(new java.awt.Color(237, 237, 237));
        curveLineChart1.setFillColor(true);
        panelShadow1.add(curveLineChart1, java.awt.BorderLayout.CENTER);

        jPanel1.add(panelShadow1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Thời Gian Vào", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(pieChart3, java.awt.BorderLayout.LINE_START);

        jTabbedPane1.addTab("Mượn", jPanel3);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Bảng", jPanel2);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.chart.CurveLineChart curveLineChart1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private raven.panel.PanelShadow panelShadow1;
    private GUI.ThongKeGUI.PieChart pieChart1;
    private GUI.ThongKeGUI.PieChart pieChart2;
    private GUI.ThongKeGUI.PieChart pieChart3;
    // End of variables declaration//GEN-END:variables
    private JTable jTable1;
    private JScrollPane jScrollPane1;
}
