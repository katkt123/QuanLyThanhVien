/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThanhVienDAL;
import DAL.ThietBiDAL;
import DAL.ThongTinSuDungDAL;
import DTO.ThanhVienDTO;
import DTO.ThietBiDTO;
import DTO.ThongTinSuDungDTO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class ThanhVienBLL {
    ThanhVienDAL tvDAL =  new ThanhVienDAL();
    ThietBiDAL tbDAL = new ThietBiDAL();
    ThongTinSuDungDAL ttDAL= new ThongTinSuDungDAL();
    
    
    public boolean CheckMuon(int ID){
        ArrayList<ThongTinSuDungDTO> list = new ThongTinSuDungBLL().listThongTinSuDung();
        for (ThongTinSuDungDTO s : list){
            if (s.getMaTV() != null){
                if (s.getMaTV().getMaTV() == ID){
                    return false;
                }
            }
        }
        return true;
    }
//    public String HienThiChiTiet(int id){
//        ThanhVienDTO s =  tvDAL.getThanhVienById(id);
//        String message = "";
//        message += "\nMã thành viên: " + s.getMaTV();
//        message += "\nHọ Tên: " + s.getHoTen();
//        message += "\nKhoa: " + s.getKhoa();
//        message += "\nNgành: " + s.getNganh();
//        message += "\n\n\n";
//        message += "Thông tin sử dụng : ";
//        
//        ArrayList<ThongTinSuDungDTO> list = new ThongTinSuDungBLL().listThongTinSuDung();
//        boolean coThongTinSuDung = false;
//        for (ThongTinSuDungDTO temp : list){
//            if (temp.getMaTB() != null){
//                if (temp.getMaTV().getMaTV() == id){
//                    ThietBiDTO temp1 = tbDAL.getThietBiById(temp.getMaTB().getMaTB());
//                    coThongTinSuDung = true;
//                    message += "\nMã thành viên: " + id;
//                    message += "\nTên người mượn: " + s.getHoTen();
//                    message += "\nTên Thiết Bị: " + temp1.getTenTB();
//                    message += "\nNgày mượn: " + temp.getTGMuon().toString();
//                    message += "\nNgày trả: " + temp.getTGTra().toString();  
//                    message += "\n";
//                }
//            }
//            else {
//                if(temp.getMaTV().getMaTV() == id){
//                    coThongTinSuDung = true;
//                    message += "\nMã thành viên: " + id;
//                    message += "\nTên người mượn: " + s.getHoTen();
//                    message += "\nThời gian vào khu vực học tập:"+ temp.getTGVao().toString();
//                    message += "\n";
//                }
//            }
//        }
//        if (!coThongTinSuDung) {
//            message += "\nKhông có thông tin sử dụng";
//        }
//        return message;  
//    }
    public ArrayList<ThongTinSuDungDTO> getMuon(int id) {
        ArrayList<ThongTinSuDungDTO> list = ttDAL.listThongTinSuDung(); 
        ArrayList<ThongTinSuDungDTO> result = new ArrayList<>();
        for (ThongTinSuDungDTO ttsd : list) {
            if (ttsd.getMaTB() != null && ttsd.getMaTV().getMaTV() == id) {
                result.add(ttsd);
            }
        }
        return result;
    }

    public ArrayList<ThongTinSuDungDTO> getKhuVuc(int id) {
        ArrayList<ThongTinSuDungDTO> list = ttDAL.listThongTinSuDung(); 
        ArrayList<ThongTinSuDungDTO> result = new ArrayList<>();
        for (ThongTinSuDungDTO ttsd : list) {
            if (ttsd.getTGVao() != null && ttsd.getMaTV().getMaTV() == id) {
                result.add(ttsd);
            }
        }
        return result;
    }
    public String HienThiChiTiet(int id){
        ThanhVienDTO s =  tvDAL.getThanhVienById(id);
        String message = "";
        message += "\nMã thành viên: " + s.getMaTV();
        message += "\nHọ Tên: " + s.getHoTen();
        message += "\nKhoa: " + s.getKhoa();
        message += "\nNgành: " + s.getNganh();
        message += "\n\n\n";
        message += "-Thông tin mượn thiết bị: ";

        ArrayList<ThongTinSuDungDTO> listThietBi = getMuon(id);
        boolean coThongTinMuonThietBi = false;
        for (ThongTinSuDungDTO temp : listThietBi){
            coThongTinMuonThietBi = true;
            ThietBiDTO temp1 = tbDAL.getThietBiById(temp.getMaTB().getMaTB());
            message += "\nMã thành viên: " + id;
            message += "\nTên người mượn: " + s.getHoTen();
            message += "\nTên Thiết Bị: " + temp1.getTenTB();
            message += "\nNgày mượn: " + temp.getTGMuon().toString();
            message += "\nNgày trả: " + temp.getTGTra().toString();  
            message += "\n";
        }
        if (!coThongTinMuonThietBi) {
            message += "\nKhông có thông tin mượn thiết bị";
        }

        message += "\n\n\n-Thông tin vào khu vực học tập: ";
        ArrayList<ThongTinSuDungDTO> listKhuVuc = getKhuVuc(id);
        boolean coThongTinVaoKhuVuc = false;
        for (ThongTinSuDungDTO temp : listKhuVuc){
            coThongTinVaoKhuVuc = true;
            message += "\nMã thành viên: " + id;
            message += "\nTên người mượn: " + s.getHoTen();
            message += "\nThời gian vào khu vực học tập: " + temp.getTGVao().toString();
            message += "\n";
        }
        if (!coThongTinVaoKhuVuc) {
            message += "\nKhông có thông tin vào khu vực học tập";
        }

        return message;  
    }

    public ArrayList<ThanhVienDTO> listThanhVien(){
        return tvDAL.listThanhVien();
    }
    public void ThemThanhVien(ThanhVienDTO tv){
        tvDAL.ThemThanhVien(tv);
    }
    
    public void updateThanhVien(ThanhVienDTO tv){
        tvDAL.updateThanhVien(tv);
    }
    public boolean deleteThanhVien(int id){
        if(CheckMuon(id)) {
            tvDAL.deleteThanhVien(id);
            return true;
        }
        else return false;
    
    }
    public String XoaTheoKhoa(int khoa){
        ArrayList<ThanhVienDTO> list= tvDAL.XoaTheoNam(khoa);
        ArrayList<String> listtb = new ArrayList<>();
        for(ThanhVienDTO tvien : list){
            if(CheckMuon(tvien.getMaTV())){
                
                tvDAL.deleteThanhVien(tvien.getMaTV());
            }
            else{
                listtb.add(tvien.getMaTV() + " " +  tvien.getHoTen());
            }  
        }
        if (listtb.size() != 0){
            String result = "Thành viên đang mượn thiết bị : ";
            for (String i : listtb){
                result += "\n" + i;
            }
            return result;
        }
        else if(listtb.size() == 0){
            return "Không có thành viên để xóa!!!";
        }
        
        return "Xóa thất bại";
    }
    public void ThemThanhVienExcel(String path){
        tvDAL.ThemThanhVienExcel(path);
    }
    public ArrayList<ThanhVienDTO> search(String s){
        return tvDAL.search(s);
    }
    public void XoaTheoNam(int so){
        
    }
    public ThanhVienDTO getThanhVienByID(int id)
    {
        return tvDAL.getThanhVienById(id);
    }
}
