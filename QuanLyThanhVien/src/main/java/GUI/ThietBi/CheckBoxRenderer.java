/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.ThietBi;

import java.awt.Color;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author DELL
 */
public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {

     public CheckBoxRenderer() {
        super();
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setOpaque(false); // Đảm bảo ô vẽ nền
    }

    @Override
    public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        if (value instanceof Boolean) {
            this.setSelected((boolean) value);
        }
        
        // Lấy màu nền từ hàng của bảng
        Color backgroundColor;
        if (isSelected) {
            backgroundColor = table.getSelectionBackground();
        } else if (row % 2 == 0) { // Hàng chẵn
            backgroundColor = Color.WHITE; // Đặt màu trắng
        } else { // Hàng lẻ
            backgroundColor = Color.LIGHT_GRAY; // Đặt màu xám nhạt
        }
        this.setForeground(backgroundColor);

        return this;
    }
    
}
