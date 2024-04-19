/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.ThongKeGUI;

import BLL.ThanhVienBLL;
import BLL.ThongTinSuDungBLL;
import BLL.XuLyViPhamBLL;
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
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
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
    XuLyViPhamBLL xlBLL;
    
    DateChooser ch;
    DateChooser ch1;
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
        new Color(168, 168, 71),  // Vàng
        new Color(4, 179, 161)
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
        xlBLL = new XuLyViPhamBLL();
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
        
        
        ch1 = new DateChooser();
        ch1.setDateSelectable(new DateSelectable() {
            @Override
            public boolean isDateSelectable(Date date) {
                return date.before(new Date());
            }
        });
        ch1.addActionDateChooserListener(
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
        ch1.setTextField(jTextField2);
        ch1.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        
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
        
        curveLineChart1.clear();
        for (Object[] t:tinSuDungBLL.getThongKeThang()){
            curveLineChart1.addData(new ModelChart(t[0].toString(), new double[]{ (long) t[1]}));
        }
        curveLineChart1.start();
        
        
        jTable2 = new javax.swing.JTable()
        {
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
            {
                Component c = super.prepareRenderer(renderer, row, column);
                Object cellValue = jTable2.getValueAt(row, column);
                //  Alternate row color

                if (cellValue == "NULL") {
                    c.setForeground(Color.RED);
                } else {
                    // Thiết lập màu nền mặc định cho các ô khác
                    c.setForeground(jTable2.getForeground());
                }
                return c;
            }
        };

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Thành Viên", "Mã Thiết bị", "Tên thiết bị", "Thời gian mượn"
            }
        ));

        jScrollPane2.setViewportView(jTable2);

        addTableMuon();
        
        
        drawPieChart3();
        drawPieChart4();
        jLabel6.setText(xlBLL.getThongKeTongTienDaXL() + " VND");
        jLabel8.setText(xlBLL.getThongKeTongTienChuaXL()+ " VND");
        
    }
    public void addTableMuon() {
        DefaultTableModel tableModel = (DefaultTableModel) jTable2.getModel();
        
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        if (jComboBox2.getSelectedIndex() == 0) {
            for (Object[] t: tinSuDungBLL.getThongKeMuon()) {
                tableModel.insertRow(tableModel.getRowCount(), t);
            }
        } else {
            for (Object[] t: tinSuDungBLL.getThongKeMuonDaTra()) {
                tableModel.insertRow(tableModel.getRowCount(), t);
            }
        }
        
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
        int dem= 0;
        for (ThongTinSuDungDTO t : listtemp) {
            if (t.getTGVao() == null) continue;
            dem++;
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
        jLabel3.setText("Theo Khoa (" + dem + ")");
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
        int tong = 0;
        // Lặp qua danh sách ngành và thêm dữ liệu vào biểu đồ
        for (int i = 0; i < nganhList.size(); i++) {
            String nganh = nganhList.get(i);
            int dem = 0;
            for (ThongTinSuDungDTO t : listtemp) {
                if (t.getTGVao() == null) continue;
                String nganhTV = tvBLL.getThanhVienByID(t.getMaTV().getMaTV()).getNganh();
                if (nganhTV.toUpperCase(Locale.ROOT).equals(nganh)) {
                    dem++;
                }
            }
            if (dem > 0) {
                pieChart2.addData(new ModelPieChart(nganh, dem, color_pie[i]));
                tong += dem;
            }
        }
        jLabel2.setText("Theo Ngành (" + khoa + ": " + tong + ")");

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
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        pieChart3 = new GUI.ThongKeGUI.PieChart();
        jLabel1 = new javax.swing.JLabel();
        pieChart4 = new GUI.ThongKeGUI.PieChart();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(300, 320));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        pieChart1.setBackground(new java.awt.Color(255, 255, 255));
        pieChart1.setChartType(GUI.ThongKeGUI.PieChart.PeiChartType.DONUT_CHART);
        pieChart1.setName(""); // NOI18N
        pieChart1.setPreferredSize(new java.awt.Dimension(320, 320));
        pieChart1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Theo Khoa");
        pieChart1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 320, -1));

        jPanel6.add(pieChart1);

        pieChart2.setBackground(new java.awt.Color(255, 255, 255));
        pieChart2.setPreferredSize(new java.awt.Dimension(320, 320));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Theo Ngành");
        pieChart2.add(jLabel2);
        jLabel2.setBounds(0, 290, 320, 16);

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

        jButton2.setText("Reload");
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
        panelShadow1.setPreferredSize(new java.awt.Dimension(184, 300));
        panelShadow1.setLayout(new java.awt.BorderLayout());

        curveLineChart1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        curveLineChart1.setForeground(new java.awt.Color(237, 237, 237));
        curveLineChart1.setFillColor(true);
        curveLineChart1.setTitle("Bảng Cột Theo Ngày");
        panelShadow1.add(curveLineChart1, java.awt.BorderLayout.CENTER);

        jPanel1.add(panelShadow1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Thời Gian Vào", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(100, 25));
        jPanel7.setLayout(null);

        jTextField2.setText("jTextField2");
        jTextField2.setPreferredSize(new java.awt.Dimension(230, 25));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel7.add(jTextField2);
        jTextField2.setBounds(0, 0, 230, 25);

        jButton3.setText("filed");
        jButton3.setPreferredSize(new java.awt.Dimension(75, 25));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton3);
        jButton3.setBounds(250, 0, 75, 25);

        jButton4.setText("Clear");
        jButton4.setPreferredSize(new java.awt.Dimension(75, 25));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4);
        jButton4.setBounds(340, 0, 75, 25);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Micro", "Máy chiếu", "Máy ảnh", "Cassette", "Tivi", "Quạt đứng" }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(72, 25));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel7.add(jComboBox1);
        jComboBox1.setBounds(430, 0, 150, 25);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Được mượn", "Đang mượn" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel7.add(jComboBox2);
        jComboBox2.setBounds(600, 0, 160, 25);

        jPanel3.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.addTab("Mượn", jPanel3);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tổng tiền đã nhận:");
        jLabel5.setToolTipText("");
        jPanel9.add(jLabel5);
        jLabel5.setBounds(10, 10, 190, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setToolTipText("");
        jPanel9.add(jLabel6);
        jLabel6.setBounds(210, 10, 260, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tổng tiền chưa nhận:");
        jLabel7.setToolTipText("");
        jPanel9.add(jLabel7);
        jLabel7.setBounds(10, 50, 190, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setToolTipText("");
        jPanel9.add(jLabel8);
        jLabel8.setBounds(210, 50, 260, 30);

        jPanel8.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setPreferredSize(new java.awt.Dimension(10, 350));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        pieChart3.setChartType(GUI.ThongKeGUI.PieChart.PeiChartType.DONUT_CHART);
        pieChart3.setPreferredSize(new java.awt.Dimension(450, 350));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Theo Hình Thức");
        pieChart3.add(jLabel1);
        jLabel1.setBounds(0, 320, 450, 16);

        jPanel10.add(pieChart3);

        pieChart4.setPreferredSize(new java.awt.Dimension(350, 350));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Theo Trạng Thái");
        pieChart4.add(jLabel4);
        jLabel4.setBounds(0, 320, 350, 16);

        jPanel10.add(pieChart4);

        jPanel8.add(jPanel10, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.addTab("Vi phạm", jPanel8);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());
        jTabbedPane1.addTab("Bảng Thông tin sữ dụng", jPanel2);

        add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dataforPie2 = tinSuDungBLL.listThongTinSuDung();
        drawPieChart1(dataforPie2);
        CallPieChart(pieChart1.getFirstData(), dataforPie2);
        curveLineChart1.clear();
        for (Object[] t: tinSuDungBLL.getThongKeThang()){
            curveLineChart1.addData(new ModelChart(t[0].toString(), new double[]{ (long) t[1]}));
        }
        curveLineChart1.start();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ArrayList<ThongTinSuDungDTO> listtemp = new ArrayList<>();
        for (ThongTinSuDungDTO t: tinSuDungBLL.listThongTinSuDung()){
            if (t.getTGVao() == null) continue;
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
                curveLineChart1.addData(new ModelChart(t[0].toString(), new double[]{ (long) t[1]}));
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
                curveLineChart1.addData(new ModelChart(t[0].toString(), new double[]{ (long) t[1]}));
            }
        }
        curveLineChart1.start();

        dataforPie2 = listtemp;
        drawPieChart1(listtemp);
        CallPieChart(pieChart1.getFirstData(), listtemp);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String datefind = jTextField2.getText().toString();
        if (datefind.length() < 11) return;
        DefaultTableModel tableModel = (DefaultTableModel) jTable2.getModel();
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        if (jComboBox2.getSelectedIndex() == 0) {
            for (Object[] t: tinSuDungBLL.getThongKeMuon(datefind)){
                tableModel.insertRow(tableModel.getRowCount(), t);
            }
        } else {
            for (Object[] t: tinSuDungBLL.getThongKeMuonDaTra(datefind)){
                tableModel.insertRow(tableModel.getRowCount(), t);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        addTableMuon();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String selectedIndex = "" +  jComboBox1.getSelectedIndex();
        if (selectedIndex.equals("0")) {
            selectedIndex = "";
        }
        System.out.println("Selected Item: " + selectedIndex);
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>((DefaultTableModel) jTable2.getModel());
        jTable2.setRowSorter(rowSorter);
        rowSorter.setRowFilter(RowFilter.regexFilter("^" + selectedIndex, 1)); // Lọc theo cột thứ 2
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        addTableMuon();
    }//GEN-LAST:event_jComboBox2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.chart.CurveLineChart curveLineChart1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private raven.panel.PanelShadow panelShadow1;
    private GUI.ThongKeGUI.PieChart pieChart1;
    private GUI.ThongKeGUI.PieChart pieChart2;
    private GUI.ThongKeGUI.PieChart pieChart3;
    private GUI.ThongKeGUI.PieChart pieChart4;
    // End of variables declaration//GEN-END:variables
    private JTable jTable1;
    private JTable jTable2;
    private JScrollPane jScrollPane1;

    private void drawPieChart3() {
        pieChart3.clearData();
        int indexx = 0;
        for (Object[] t: xlBLL.getThongKeHinhThucXL()) {
            pieChart3.addData(new ModelPieChart(t[0].toString(), (long) t[1], COLOR_OPTIONS[indexx]));
            indexx++;
        }
    }

    private void drawPieChart4() {
        pieChart4.clearData();
        int indexx = 0;
        for (Object[] t: xlBLL.getThongKeTrangThaiXL()) {
            String trangthai = t[0].toString().equals("0") ? "Chưa xử lý" : "Đã xử lý";
            pieChart4.addData(new ModelPieChart(trangthai, (long) t[1], COLOR_OPTIONS[indexx]));
            indexx++;
        }
    }
}
