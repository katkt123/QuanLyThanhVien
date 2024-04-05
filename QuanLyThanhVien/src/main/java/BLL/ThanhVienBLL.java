/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThanhVienDAL;
import DTO.ThanhVienDTO;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ThanhVienBLL {
    ThanhVienDAL tvDAL =  new ThanhVienDAL();
    
    
    public ArrayList<ThanhVienDTO> listThanhVien(){
        return tvDAL.listThanhVien();
    }
    public void ThemThanhVien(ThanhVienDTO tv){
        tvDAL.ThemThanhVien(tv);
    }
    
    public void updateThanhVien(ThanhVienDTO tv){
        tvDAL.updateThanhVien(tv);
    }
    public void deleteThanhVien(int id){
        tvDAL.deleteThanhVien(id);
    }
    public void ThemThanhVienExcel(String path){
        tvDAL.ThemThanhVienExcel(path);
    }
    public ArrayList<ThanhVienDTO> search(String s){
        return tvDAL.search(s);
    }
}
