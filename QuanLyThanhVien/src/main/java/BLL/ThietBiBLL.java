/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThietBiDAL;
import DTO.ThietBiDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.poi.ss.usermodel.*;
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
    
    public String AddExcel(String filePath){
        try{
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            
            Row Titlerow = sheet.getRow(0);
            
            Cell TitleMaCell = Titlerow.getCell(0);
            Cell TitleTenCell = Titlerow.getCell(1);
            Cell TitleMoTaCell = Titlerow.getCell(2);
            
            // Bắt đầu đọc từ hàng thứ hai (index 1)
//            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                Row row = sheet.getRow(i);
//                if (row != null) {
//                    Cell nameCell = row.getCell(0);
//                    Cell khoaCell = row.getCell(1);
//                    Cell nganhCell = row.getCell(2);
//
//                    // Kiểm tra và lưu dữ liệu vào Hibernate
//                    if (nameCell != null && khoaCell != null && nganhCell != null && sdtCell != null) {
//                        String name = nameCell.getStringCellValue();
//                        String khoa = khoaCell.getStringCellValue();
//                        String nganh = nganhCell.getStringCellValue();
//                        String sdt =  sdtCell.getStringCellValue();
//
//                        String id1 ="";
//                        String year1 = Year.now().toString().substring(2);
//                        String khoa1 = khoa;
//                        String khoaCode1 = "";
//
//                        switch (khoa1.toUpperCase(Locale.ROOT)) {
//                            case "CNTT":
//                                khoaCode1 = "41";
//                                break;
//                            case "QTKD":
//                                khoaCode1 = "42";
//                                break;
//                            case "TLH":
//                                khoaCode1 = "43";
//                                break;
//                            // Thêm các trường hợp khác nếu cần
//                            default:
//                                // Mặc định sẽ là 00 nếu không trùng khớp
//                                khoaCode1 = "00";
//                                break;
//                        }
//                        id1 = "11"+ year1 + khoaCode1 + String.format("%03d", count + 1);    
//                        int idCode = Integer.parseInt(id1);
//
//                        // Tạo đối tượng ThanhVienDTO từ dữ liệu
//                        ThanhVienDTO thanhVien = new ThanhVienDTO(idCode, name, khoa, nganh, sdt);
//                        // Lưu đối tượng vào cơ sở dữ liệu bằng Hibernate
//                        session.save(thanhVien);
//                        // Cập nhật giá trị của count
//                        count++;
//                    }
//                }
//            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return "Thất bại rồi";
    }
    
}
