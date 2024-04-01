/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "thanhvien")
public class ThanhVienDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTV")
    private int MaTV;

    @Column(name = "HoTen")
    private String HoTen;

    @Column(name = "Khoa")
    private String Khoa;

    @Column(name = "Nganh")
    private String Nganh;

    @Column(name = "SDT")
    private int SDT;

    public ThanhVienDTO() {
    }

    public ThanhVienDTO(int MaTV, String HoTen, String Khoa, String Nganh, int SDT) {
        this.MaTV = MaTV;
        this.HoTen = HoTen;
        this.Khoa = Khoa;
        this.Nganh = Nganh;
        this.SDT = SDT;
    }
    public ThanhVienDTO(String HoTen, String Khoa, String Nganh, int SDT) {
        this.HoTen = HoTen;
        this.Khoa = Khoa;
        this.Nganh = Nganh;
        this.SDT = SDT;
    }

    public String getHoTen() {
        return HoTen;
    }

    public int getMaTV() {
        return MaTV;
    }

    public String getKhoa() {
        return Khoa;
    }

    public String getNganh() {
        return Nganh;
    }

    public int getSDT() {
        return SDT;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public void setKhoa(String Khoa) {
        this.Khoa = Khoa;
    }

    public void setMaTV(int MaTV) {
        this.MaTV = MaTV;
    }

    public void setNganh(String Nganh) {
        this.Nganh = Nganh;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }    
}
