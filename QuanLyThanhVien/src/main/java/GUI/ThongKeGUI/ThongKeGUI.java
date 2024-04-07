/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.ThongKeGUI;

/**
 *
 * @author HP
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;

public class ThongKeGUI extends JPanel {

    public ThongKeGUI() {
        // Tạo dữ liệu mẫu cho biểu đồ hình tròn
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Category 1", 30);
        dataset.setValue("Category 2", 20);
        dataset.setValue("Category 3", 50);

        // Tạo biểu đồ hình tròn từ dữ liệu
        JFreeChart chart = ChartFactory.createPieChart(
                "Sample Pie Chart", // Tiêu đề của biểu đồ
                dataset, // Dữ liệu
                true, // Có hiển thị chú thích không
                true, // Có tạo tooltips không
                false // Có tạo URLs không
        );

        // Tạo một Panel để chứa biểu đồ
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(930, 680));

        setLayout(new BorderLayout());

        // Thêm chartPanel vào JPanel ở vị trí CENTER của BorderLayout
        add(chartPanel, BorderLayout.CENTER);
    }
}

