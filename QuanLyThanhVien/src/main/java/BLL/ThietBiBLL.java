/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThietBiDAL;
import DTO.ThanhVienDTO;
import DTO.ThietBiDTO;
import DTO.ThongTinSuDungDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    
    public boolean CheckMuon(int ID){
        ArrayList<ThongTinSuDungDTO> list = new ThongTinSuDungBLL().listThongTinSuDung();
        for (ThongTinSuDungDTO s : list){
            if (s.getMaTB() != null){
                if (s.getMaTB().getMaTB() == ID){
                    return true;
                }
            }
        }
        return false;
    }
    
    public String KiemTraTruocKhiXoa(DefaultTableModel model){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < model.getRowCount();i++){
            boolean trangthai = (boolean) model.getValueAt(i, 0);
            if (trangthai){
                int ID = (int) model.getValueAt(i, 1);
                if (CheckMuon(ID)){
                    list.add(ID + " " +  model.getValueAt(i, 2));
                }
                else{
                    tbDAL.deleteThietBi(ID);
                }

            }
        }
        
        if (list.size() != 0){
            String result = "Mã thiết bị đang mượn: ";
            for (String i : list){
                result += "\n" + i;
            }
            return result;
        }
        
        return "Xóa thất bại"; 
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
        ArrayList<ThietBiDTO> list_excel = new ArrayList<>();
        try{
            FileInputStream inputStream = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            
            Row Titlerow = sheet.getRow(0);
            
            Cell TitleMaCell = Titlerow.getCell(0);
            Cell TitleTenCell = Titlerow.getCell(1);
            Cell TitleMoTaCell = Titlerow.getCell(2);
            
            if (TitleMaCell != null && TitleTenCell != null && TitleMoTaCell != null){
                String ma = TitleMaCell.getStringCellValue();
                String ten = TitleTenCell.getStringCellValue();
                String mota = TitleMoTaCell.getStringCellValue();
                if (!ma.equals("MaTB") || !ten.equals("TenTB") || !mota.equals("MoTaTB")){
                    return "File không đúng cấu trúc cột";
                }
                
                
                // Bắt đầu đọc từ hàng thứ hai (index 1)
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        Cell MaCell = row.getCell(0);
                        Cell TenCell = row.getCell(1);
                        Cell MoTaCell = row.getCell(2);

                        // Kiểm tra và lưu dữ liệu vào Hibernate
                        if (MaCell  != null && TenCell != null && MoTaCell != null) {
                            
                            double Ma = 0;
                            String Ten = "";
                            String MoTa = "";
                                        
                            try{
                                Ma = MaCell.getNumericCellValue();
                                Ten = TenCell.getStringCellValue();
                                MoTa = MoTaCell.getStringCellValue();
                            }
                            catch(Exception e){
                                return "MaTB là số, TenTB và MoTaTB là chuỗi ký tự";
                            }
                            
                            ThietBiDTO thietbi = new ThietBiDTO((int) Ma, Ten, MoTa);
                            
                            list_excel.add(thietbi);
                            
                        }
                    }
                }
            }
            
            for (ThietBiDTO a : list_excel){
                if (tbDAL.kiemTraMaThietBiTonTai(a.getMaTB())){
                    tbDAL.updateThietBi(a);
                }
                else{
                    tbDAL.addThietBi(a);
                }
            }
            
            return "Thêm thành công";
            

        }catch(Exception e){
            e.printStackTrace();
        }
        
        return "Phải rỗng rồi";
    }
    public ThietBiDTO getThietBiById(int id){
        return tbDAL.getThietBiById(id);
    }
    
    
    public String HienChiTiet(int ID){
        ArrayList<ThongTinSuDungDTO> list = new ThongTinSuDungBLL().listThongTinSuDung();
        String message = "";
        for (ThongTinSuDungDTO s : list){
            if (s.getMaTB() != null){
                if (s.getMaTB().getMaTB() == ID){
                    ThanhVienDTO temp = new ThanhVienBLL().getThanhVienByID(s.getMaTV().getMaTV());
                    message += "\nMã thành viên: " + s.getMaTV().getMaTV();
                    message += "\nTên người mượn: " + temp.getHoTen();
                    message += "\nNgày mượn: " + s.getTGMuon().toString();
                    message += "\nNgày trả: " + s.getTGTra().toString();
                    return message;
                }
            }
        }
        return "Thiết bị chưa được mượn";
    }
    
}
