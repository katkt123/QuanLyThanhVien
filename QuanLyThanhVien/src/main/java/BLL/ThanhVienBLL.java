/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThanhVienDAL;
import DAL.ThietBiDAL;
import DAL.ThongTinSuDungDAL;
import DAL.XuLyViPhamDAL;
import DTO.ThanhVienDTO;
import DTO.ThietBiDTO;
import DTO.ThongTinSuDungDTO;
import DTO.XuLyViPhamDTO;
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
    XuLyViPhamDAL xlDAL= new XuLyViPhamDAL();
    
    
    public boolean CheckMuon(int ID){
        ArrayList<ThongTinSuDungDTO> list = new ThongTinSuDungDAL().listThongTinSuDung();
        for (ThongTinSuDungDTO s : list){
            if (s.getMaTV() != null){
                if (s.getMaTV().getMaTV() == ID){
                    return false;
                }
            }
        }
        return true;
    }
     public boolean checkVP(int id){
        ArrayList<XuLyViPhamDTO> arr = xlDAL.listXuLyViPham();
        for(XuLyViPhamDTO a:arr){
            if(a.getMaTV().getMaTV()==id && a.getTrangThaiXL()==0){
                return false;
                
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
            if (ttsd.getMaTB() != null && ttsd.getMaTV().getMaTV() == id && ttsd.getTGTra() != null) {
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
    
    public ArrayList<XuLyViPhamDTO> getViPham(int id){
        ArrayList<XuLyViPhamDTO> list = xlDAL.listXuLyViPham(); 
        ArrayList<XuLyViPhamDTO> result = new ArrayList<>();
        for(XuLyViPhamDTO xl:list){
            if(xl.getMaTV().getMaTV() == id) result.add(xl);
        }
        return result;
    }
    public String HienThiChiTiet(int id) {
        ThanhVienDTO s =  tvDAL.getThanhVienById(id);
        String message = "";

        // Thông tin thành viên
        message += "Mã thành viên: \t" + s.getMaTV() + "\n";
        message += "Họ Tên: \t" + s.getHoTen() + "\n";
        message += "Khoa: \t" + s.getKhoa() + "\n";
        message += "Ngành: \t" + s.getNganh() + "\n\n\n";

        // Thông tin mượn thiết bị
        message += "-Thông tin mượn thiết bị: \n";
        ArrayList<ThongTinSuDungDTO> listThietBi = getMuon(id);
        boolean coThongTinMuonThietBi = false;
        int i = 1;
        for (ThongTinSuDungDTO temp : listThietBi) {
            coThongTinMuonThietBi = true;
            message += "--------------" + i + "--------------\n";
            ThietBiDTO temp1 = tbDAL.getThietBiById(temp.getMaTB().getMaTB());
            message += "Mã thành viên: \t" + id + "\n";
            message += "Tên : \t" + s.getHoTen() + "\n";
            message += "Tên Thiết Bị: \t" + temp1.getTenTB() + "\n";
            message += "Ngày mượn: \t" + temp.getTGMuon().toString() + "\n";
            message += "Ngày trả: \t" + temp.getTGTra().toString() + "\n\n";
            i++;
        }
        if (!coThongTinMuonThietBi) {
            message += "Không có thông tin mượn thiết bị\n";
        }

        // Thông tin vào khu vực học tập
        message += "-Thông tin vào khu vực học tập: \n";
        ArrayList<ThongTinSuDungDTO> listKhuVuc = getKhuVuc(id);
        boolean coThongTinVaoKhuVuc = false;
        int j = 1;
        for (ThongTinSuDungDTO temp : listKhuVuc) {
            coThongTinVaoKhuVuc = true;
            message += "--------------" + j + "--------------\n";
            message += "Mã thành viên: \t" + id + "\n";
            message += "Tên : \t" + s.getHoTen() + "\n";
            message += "Thời gian vào khu vực học tập: \t" + temp.getTGVao().toString() + "\n\n";
            j++;
        }
        if (!coThongTinVaoKhuVuc) {
            message += "Không có thông tin vào khu vực học tập\n";
        }

        // Thông tin vi phạm
        message += "-Những vi phạm: \n";
        ArrayList<XuLyViPhamDTO> listVP = getViPham(id);
        boolean VIPHAM = false;
        int k = 1;
        for (XuLyViPhamDTO a : listVP) {
            VIPHAM = true;
            message += "--------------" + k + "--------------\n";
            message += "Mã thành viên: \t" + id + "\n";
            message += "Tên : \t" + s.getHoTen() + "\n";
            message += "Hình thức xử lý: \t" + a.getHinhThucXL() + "\n";
            String tien = Integer.toString(a.getSoTien());
            message += "Tiền trả: \t" + tien + "\n";
            message += "Ngày xử lý: \t" + a.getNgayXL().toString() + "\n";
            if (a.getTrangThaiXL() == 0) {
                message += "Tình trạng: \tĐang xử lý ....\n";
            } else {
                message += "Tình trạng: \tĐã xử lý\n";
            }
            k++;
        }
        if (!VIPHAM) {
            message += "Không có thông tin vi phạm\n";
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
        if(CheckMuon(id) && checkVP(id)) {
            tvDAL.deleteThanhVien(id);
            return true;
        }   
        else return false;
    
    }
    public String XoaTheoKhoa(int khoa){
        ArrayList<ThanhVienDTO> list= tvDAL.XoaTheoNam(khoa);
        ArrayList<String> listtb = new ArrayList<>();
        for(ThanhVienDTO tvien : list){
            if(CheckMuon(tvien.getMaTV()) && checkVP(tvien.getMaTV())){
                
                tvDAL.deleteThanhVien(tvien.getMaTV());
            }
            else{
                listtb.add(tvien.getMaTV() + " " +  tvien.getHoTen());
            }  
        }
        if (listtb.size() != 0){
            String result = "Thành viên đang mượn thiết bị hoặc đang bị xử lý : ";
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
