/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.XuLyViPhamDAL;
import DTO.XuLyViPhamDTO;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class XuLyViPhamBLL {
    
    XuLyViPhamDAL xlDAL = new XuLyViPhamDAL();
    
    public ArrayList<XuLyViPhamDTO> listxlvp(){
        return xlDAL.listXuLyViPham();
    }
            
    
}
