/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import lombok.Data;

/**
 *
 * @author DELL
 */
//@Data
@Entity
@Table(name = "ThietBiDTO")

public class ThietBiDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String MaTB;
    
    @Column
    private String TenTB;
    
    @Column
    private String MoTaTB;

    public ThietBiDTO(String MaTB, String TenTB, String MoTa) {
        this.MaTB = MaTB;
        this.TenTB = TenTB;
        this.MoTaTB = MoTa;
    }

    public String getMaTB() {
        return MaTB;
    }

    public void setMaTB(String MaTB) {
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
    
    
    
}
