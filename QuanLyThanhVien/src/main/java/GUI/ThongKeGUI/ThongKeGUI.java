/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ThongKeGUI;

import BLL.ThanhVienBLL;
import BLL.ThongTinSuDungBLL;
import DTO.ThanhVienDTO;
import DTO.ThongTinSuDungDTO;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import raven.chart.ModelChart;
import raven.datechooser.DateBetween;
import raven.datechooser.DateChooser;
import raven.datechooser.DateSelectable;
import raven.datechooser.listener.DateChooserAction;
import raven.datechooser.listener.DateChooserAdapter;

/**
 *
 * @author HP
 */
public class ThongKeGUI extends javax.swing.JPanel {
    ThongTinSuDungBLL tinSuDungBLL;
    ThanhVienBLL tvBLL;
    DateChooser ch;
    ArrayList<ThongTinSuDungDTO> dataforPie2;

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
        String listMonth[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String listDays[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        UIManager.put("DateChooser.listMonth", listMonth);
        UIManager.put("DateChooser.listDay", listDays);
        FlatAnimatedLafChange.showSnapshot();
        FlatMacLightLaf.setup();
        FlatLaf.updateUI();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
        ch = new DateChooser();
        ch.setDateSelectable(new DateSelectable() {
            @Override
            public boolean isDateSelectable(Date date) {
                return date.before(new Date());
            }
        });
        ch.addActionDateChooserListener(
                new DateChooserAdapter() {
                    @Override
                    public void dateChanged(Date date, DateChooserAction action) {
                        System.out.println("date single selected...");
                    }
                    @Override
                    public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                        System.out.println(date);
                    }
                });
        ch.setTextField(jTextField1);
        ch.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        
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
        
        dataforPie2 = tinSuDungBLL.listThongTinSuDung();
        drawPieChart1( dataforPie2);
        pieChart1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // Xử lý sự kiện khi chuột được click
                System.out.println("Mouse clicked! " +  pieChart1.getData());
                CallPieChart(pieChart1.getData(), dataforPie2);
            }
        });
        CallPieChart(pieChart1.getFirstData(), dataforPie2);
        System.out.println("GUI.ThongKe" + (parent.getHeight() - pieChart1.getHeight()));
        
        curveLineChart1.addLegend("Vào", Color.decode("#B2E4CD"), Color.decode("#B2E4B4"));
        curveLineChart1.addLegend("Mượn", Color.decode("#e65c00"), Color.decode("#F9D423"));
        
        curveLineChart1.clear();
        for (Object[] t:tinSuDungBLL.getThongKeThang()){
            curveLineChart1.addData(new ModelChart(t[0].toString(), new double[]{ (long) t[1], (long) t[2]}));
        }
        curveLineChart1.start();
    }
    public boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }
    private boolean jtextviewToDateToData(Date inputDate){
        String dateRange = jTextField1.getText().toString();
        // Chuyển đổi chuỗi ngày bắt đầu và kết thúc thành đối tượng Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = null;
        Date endDate = null;
        
        if (dateRange.length() < 11)  {
            try {
                startDate = dateFormat.parse(dateRange);
            } catch (ParseException ex) {
                Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            return isSameDate(startDate, inputDate);
        }
        String startDateStr = dateRange.split(" to ")[0];
        String endDateStr = dateRange.split(" to ")[1];

        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Kiểm tra xem ngày đầu vào có nằm trong phạm vi không
       return !(inputDate.before(startDate) || inputDate.after(endDate));
    }
    
    private void drawPieChart1(ArrayList<ThongTinSuDungDTO> listtemp){
        pieChart1.clearData();
        Map<String, Integer> khoaCounts = new HashMap<>();
        // Lặp qua danh sách thông tin sử dụng và cập nhật số lượng của từng loại khoa trong Map
        for (ThongTinSuDungDTO t : listtemp) {
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
    }
    private void CallPieChart(String khoa, ArrayList<ThongTinSuDungDTO> listtemp) {
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
            for (ThongTinSuDungDTO t : listtemp) {
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
        jPanel6 = new javax.swing.JPanel();
        pieChart1 = new GUI.ThongKeGUI.PieChart();
        jLabel3 = new javax.swing.JLabel();
        pieChart2 = new GUI.ThongKeGUI.PieChart();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        panelShadow1 = new raven.panel.PanelShadow();
        curveLineChart1 = new raven.chart.CurveLineChart();
        jPanel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(600, 350));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        pieChart1.setBackground(new java.awt.Color(255, 255, 255));
        pieChart1.setChartType(GUI.ThongKeGUI.PieChart.PeiChartType.DONUT_CHART);
        pieChart1.setName(""); // NOI18N
        pieChart1.setPreferredSize(new java.awt.Dimension(350, 350));
        pieChart1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Theo Khoa");
        pieChart1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 340, -1));

        jPanel6.add(pieChart1);

        pieChart2.setBackground(new java.awt.Color(255, 255, 255));
        pieChart2.setPreferredSize(new java.awt.Dimension(350, 350));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Theo Ngành");
        pieChart2.add(jLabel2);
        jLabel2.setBounds(0, 320, 350, 16);

        jPanel6.add(pieChart2);

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(600, 25));
        jPanel5.setLayout(null);

        jTextField1.setText("jTextField1");
        jPanel5.add(jTextField1);
        jTextField1.setBounds(0, 0, 230, 25);

        jButton1.setText("Selected Date");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);
        jButton1.setBounds(250, 0, 130, 25);

        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2);
        jButton2.setBounds(400, 0, 130, 25);

        jPanel4.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        panelShadow1.setBackground(new java.awt.Color(51, 153, 255));
        panelShadow1.setColorGradient(new java.awt.Color(51, 51, 255));
        panelShadow1.setPreferredSize(new java.awt.Dimension(184, 270));
        panelShadow1.setLayout(new java.awt.BorderLayout());

        curveLineChart1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        curveLineChart1.setForeground(new java.awt.Color(237, 237, 237));
        curveLineChart1.setFillColor(true);
        curveLineChart1.setTitle("Bảng Cột Theo Ngày");
        panelShadow1.add(curveLineChart1, java.awt.BorderLayout.CENTER);

        jPanel1.add(panelShadow1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Thời Gian Vào", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Bảng", jPanel2);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dataforPie2 = tinSuDungBLL.listThongTinSuDung();
        drawPieChart1(dataforPie2);
        CallPieChart(pieChart1.getFirstData(), dataforPie2);
        curveLineChart1.clear();
        for (Object[] t: tinSuDungBLL.getThongKeThang()){
            curveLineChart1.addData(new ModelChart(t[0].toString(), new double[]{ (long) t[1], (long) t[2]}));
        }
        curveLineChart1.start();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ArrayList<ThongTinSuDungDTO> listtemp = new ArrayList<>();
        for (ThongTinSuDungDTO t: tinSuDungBLL.listThongTinSuDung()){
            if (jtextviewToDateToData(t.getTGVao())){
                listtemp.add(t);
            }
        }
        if (listtemp.size() < 1){
            JOptionPane.showMessageDialog(null, "Không có thanh vien trong ngày!");
            return;
        }
        curveLineChart1.clear();
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yy");
        if (jTextField1.getText().toString().length() < 11) {
            for (Object[] t:tinSuDungBLL.getThongKeNgay(jTextField1.getText().toString())){
                curveLineChart1.addData(new ModelChart(t[0].toString(), new double[]{ (long) t[1], (long) t[2]}));
            }
        } else {
            for (Object[] t:tinSuDungBLL.getThongKeThang()){
                Date inputDate = null;
                try {
                    inputDate = inputDateFormat.parse(t[0].toString());
                } catch (ParseException ex) {
                    Logger.getLogger(ThongKeGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (jtextviewToDateToData(inputDate))
                curveLineChart1.addData(new ModelChart(t[0].toString(), new double[]{ (long) t[1], (long) t[2]}));
            }
        }
        curveLineChart1.start();

        dataforPie2 = listtemp;
        drawPieChart1(listtemp);
        CallPieChart(pieChart1.getFirstData(), listtemp);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.chart.CurveLineChart curveLineChart1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private raven.panel.PanelShadow panelShadow1;
    private GUI.ThongKeGUI.PieChart pieChart1;
    private GUI.ThongKeGUI.PieChart pieChart2;
    // End of variables declaration//GEN-END:variables
    private JTable jTable1;
    private JScrollPane jScrollPane1;
}
