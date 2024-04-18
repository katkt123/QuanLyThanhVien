/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThongTinSuDungDAL;
import DTO.ThongTinSuDungDTO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ThongTinSuDungBLL {
    ThongTinSuDungDAL ttDAL = new ThongTinSuDungDAL();
    
    public ArrayList<ThongTinSuDungDTO> listThongTinSuDung() {
        return ttDAL.listThongTinSuDung();
    }
    public ArrayList<ThongTinSuDungDTO> listMuon() {
        return ttDAL.listMuon();
    }
    public void themThongTinSuDung(ThongTinSuDungDTO thongTin) {
        ttDAL.themThongTinSuDung(thongTin);
    }
    public void capNhatThongTinSuDung(ThongTinSuDungDTO thongTin) {
        ttDAL.capNhatThongTinSuDung(thongTin);
    }
     public void xoaThongTinSuDung(int id) {
         ttDAL.xoaThongTinSuDung(id);
     }
    public void VaoKhuVucHocTap(int id){
        ttDAL.VaoKhuVucHocTap(id);
    }
    
    public void MuonThietBi(ThongTinSuDungDTO tt){
        ttDAL.MuonThietBi(tt);
    }
    public List<Object[]> getThongKeThang() {
        return ttDAL.getThongKeThang();
    }
    
    public List<Object[]> getThongKeNgay(String datefind) {
        DateTimeFormatter currentFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        // Định nghĩa định dạng mới của chuỗi ngày
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Chuyển chuỗi ngày sang LocalDate
        LocalDate date = LocalDate.parse(datefind, currentFormatter);
        
        // Đổi định dạng của LocalDate sang chuỗi với định dạng mới
        String newDateString = date.format(newFormatter);
        return ttDAL.getThongKeNgay(newDateString);
    }
    
}
