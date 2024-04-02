/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//import lombok.Data;

/**
 *
 * @author DELL
 */
//@Data
@Entity
@Table(name = "thietbi")

public class ThietBiDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column ( name = "MaTB")
    private int MaTB;
    
    @Column ( name = "TenTB")
    private String TenTB;
    
    @Column ( name = "MoTaTB")
    private String MoTaTB;
    
//    @OneToMany(fetch=FetchType.LAZY, mappedBy= "thietbi")
//    private ArrayList<ThongTinSuDungDTO> ttsd = new ArrayList<>();
    

    public ThietBiDTO(int MaTB, String TenTB, String MoTa) {
        this.MaTB = MaTB;
        this.TenTB = TenTB;
        this.MoTaTB = MoTa;
    }

    public ThietBiDTO() {
    }
    
    

    public int getMaTB() {
        return MaTB;
    }

    public void setMaTB(int MaTB) {
        this.MaTB = MaTB;
    }

    public String getTenTB() {
        return TenTB;
    }

    public void setTenTB(String TenTB) {
        this.TenTB = TenTB;
    }

    public String getMoTaTB() {
        return MoTaTB;
    }

    public void setMoTaTB(String MoTa) {
        this.MoTaTB = MoTa;
    }

//    public ArrayList<ThongTinSuDungDTO> getTtsd() {
//        return ttsd;
//    }
//
//    public void setTtsd(ArrayList<ThongTinSuDungDTO> ttsd) {
//        this.ttsd = ttsd;
//    }
    
    
    
    
}
