/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThietBiDAL;
import DTO.ThietBiDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class ThietBiBLL {
    ThietBiDAL tbDAL = new ThietBiDAL();
    ArrayList<ThietBiDTO> list_tb = new ArrayList<>();
    
    public ArrayList<ThietBiDTO> getListTB(){
        list_tb = tbDAL.listThietBi();
        return list_tb;
    }
    
    public void ThemTB(ThietBiDTO tb){
//        if (tbDAL.addThietBi(tb)){
//            return "Thêm thiet bi thành công";
//        }
//        return "Thêm thiet bi thất bại :((";
        tbDAL.addThietBi(tb);
    }
    public void SuaTB(ThietBiDTO tb){
        tbDAL.updateThietBi(tb);
    }
    public String XoaTB(DefaultTableModel model){
        int dem = 0;
        for (int i = 0; i < model.getRowCount();i++){
            boolean trangthai = (boolean) model.getValueAt(i, 0);
            if (trangthai){
                tbDAL.deleteThietBi((int) model.getValueAt(i, 1));
                dem++;
            }
        }
        if (dem!=0){
            return "Xóa thành công";
        }
        else if(dem == 0){
            return "Hãy tick vào các đối tượng muốn xóa";
        }
        
        return "Xóa thất bại"; 
    }
    
    public int LayID_TB(){
       return tbDAL.Lay_ID_Thietbi();
    }
    
    
    public void search(ArrayList<ThietBiDTO> list_tb,DefaultTableModel model, String txtMaTB, String txtTen){
        model.setRowCount(0);
        for (ThietBiDTO tb : list_tb){
            if (!txtMaTB.equals("Nhập mã thiết bị") && !txtTen.equals("Nhập tên thiết bị")){
                if (Integer.toString(tb.getMaTB()).contains(txtMaTB) && tb.getTenTB().contains(txtTen)){
                    model.addRow(new Object[]{false,tb.getMaTB(),tb.getTenTB(),tb.getMoTaTB()});
                }
            
            }
            else if(txtMaTB.equals("Nhập mã thiết bị")){
                if (tb.getTenTB().contains(txtTen)){
                    model.addRow(new Object[]{false,tb.getMaTB(),tb.getTenTB(),tb.getMoTaTB()});
                }
            }
            else if(txtTen.equals("Nhập tên thiết bị")){
                if (Integer.toString(tb.getMaTB()).contains(txtMaTB)){
                    model.addRow(new Object[]{false,tb.getMaTB(),tb.getTenTB(),tb.getMoTaTB()});
                }
            }
        }
    }
    
    public ArrayList<ThietBiDTO> getListSearch(String id){
        return tbDAL.listThietBiComboBox(id);
    }
    
}
