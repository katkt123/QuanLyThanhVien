/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThongTinSuDungDAL;
import DTO.ThongTinSuDungDTO;
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
    
    
}
