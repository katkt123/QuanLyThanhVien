/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThietBiDAL;
import DTO.ThietBiDTO;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ThietBiBLL {
    ThietBiDAL tbDAL = new ThietBiDAL();
    ArrayList<ThietBiDTO> list_tb = new ArrayList<>();
    
    public ArrayList<ThietBiDTO> getListTB(){
        return tbDAL.listThietBi();
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
    public void XoaTB(int tbID){
        tbDAL.deleteThietBi(tbID);
    }
}
