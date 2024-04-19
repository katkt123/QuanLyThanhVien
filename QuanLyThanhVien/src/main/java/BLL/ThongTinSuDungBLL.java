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
import javax.swing.JOptionPane;

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
        return ttDAL.getThongKe();
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
    
    
    public List<Object[]> getThongKeMuon() {
        return ttDAL.getThongKeMuon();
    }
    public List<Object[]> getThongKeMuon(String dateFind) {
        String startDateStr = dateFind.split(" to ")[0];
        String endDateStr = dateFind.split(" to ")[1];
        System.out.println("BLL.ThongTinSuDungBLL.getThongKeMuon() + " +chagneDateToIDK(startDateStr) + " " + chagneDateToIDK(endDateStr));
        return ttDAL.getThongKeMuon(chagneDateToIDK(startDateStr) , chagneDateToIDK(endDateStr));
    }
    public List<Object[]> getThongKeMuonDaTra() {
        return ttDAL.getThongKeMuonDaTra();
    }
    public List<Object[]> getThongKeMuonDaTra(String dateFind) {
        String startDateStr = dateFind.split(" to ")[0];
        String endDateStr = dateFind.split(" to ")[1];
        System.out.println("BLL.ThongTinSuDungBLL.getThongKeMuon() + " +chagneDateToIDK(startDateStr) + " " + chagneDateToIDK(endDateStr));
        return ttDAL.getThongKeMuonDaTra(chagneDateToIDK(startDateStr) , chagneDateToIDK(endDateStr));
    }
    
    public String chagneDateToIDK(String datefind) {
        String inputDate = datefind;

        // Định dạng cho phép phân tích ngày
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Phân tích ngày từ chuỗi
        LocalDate date = LocalDate.parse(inputDate, formatter);

        // Định dạng lại ngày theo định dạng mới
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(newFormatter);
    }
    
}
