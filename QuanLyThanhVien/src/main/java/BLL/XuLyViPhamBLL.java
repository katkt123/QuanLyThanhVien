/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.XuLyViPhamDAL;
import DTO.ThanhVienDTO;
import DTO.XuLyViPhamDTO;
import DTO.ThongTinSuDungDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class XuLyViPhamBLL {
    
    XuLyViPhamDAL xlDAL = new XuLyViPhamDAL();
    
    public ArrayList<XuLyViPhamDTO> listxlvp(){
        return xlDAL.listXuLyViPham();
    }
    
    public void themXuLyViPham(XuLyViPhamDTO xuLyViPham) {
//        if (xlDAL.kiemTraTrangThai(xuLyViPham)) {
//            JOptionPane.showMessageDialog(null, "Không thể tạo mới vì lỗi cũ vẫn chưa xử lí xong.");
//            return;
//        } else {
            xlDAL.themXuLyViPham(xuLyViPham);
            JOptionPane.showMessageDialog(null, "Thêm vi phạm thành công.");
//        }
    }
    
    public ArrayList<XuLyViPhamDTO> search(String s){
        return xlDAL.search(s);
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

    public void xoaXuLyViPham(int id) {
        xlDAL.xoaXuLyViPham(id);
    }
    
    public void updateXuLyViPham(XuLyViPhamDTO xuLyViPhamDTO) {
        xlDAL.updateXuLyViPham(xuLyViPhamDTO);
    }
    
    public XuLyViPhamDTO getXuLyViPhamByMaXL(int maXL) {
        return xlDAL.getXuLyViPhamByMaXL(maXL);
    }
    
    public ArrayList<ThongTinSuDungDTO> listQuaHan() {
        ArrayList<ThongTinSuDungDTO> list = xlDAL.listQuaHan();

        // In ra danh sách để kiểm tra dữ liệu
        for (ThongTinSuDungDTO item : list) {
            System.out.println("MaTT: " + item.getMaTT() + ", MaTV: " + item.getMaTV() + ", MaTB: " + item.getMaTB());
        }

        return list;
    }

}
