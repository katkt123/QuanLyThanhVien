/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "thongtinsd")
public class ThongTinSuDungDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTT")
    private int MaTT;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="MaTV",nullable=false,foreignKey=@ForeignKey(name="fk_ThongTinSuDungDTO_ThanhVienDTO"))
    private ThanhVienDTO MaTV;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="MaTB",nullable=false,foreignKey=@ForeignKey(name="fk_ThongTinSuDungDTO_ThietBiDTO"))
    private ThietBiDTO MaTB;
    
    @Column(name = "TGVao")
    private Date TGVao;

    @Column(name = "TGMuon")
    private Date TGMuon;

    @Column(name = "TGTra")
    private Date TGTra;
    
    
    
    public ThongTinSuDungDTO() {
    }

    public ThongTinSuDungDTO(int MaTT, ThanhVienDTO MaTV, ThietBiDTO MaTB, Date TGVao, Date TGMuon, Date TGTra) {
        this.MaTT = MaTT;
        this.MaTV = MaTV;
        this.MaTB = MaTB;
        this.TGVao = TGVao;
        this.TGMuon = TGMuon;
        this.TGTra = TGTra;
    }
    public ThongTinSuDungDTO(int MaTT,Date TGVao, Date TGMuon, Date TGTra) {
        
        this.MaTT = MaTT;
        this.TGVao = TGVao;
        this.TGMuon = TGMuon;
        this.TGTra = TGTra;
    }

    public void setMaTB(ThietBiDTO MaTB) {
        this.MaTB = MaTB;
    }

    public ThanhVienDTO getMaTV() {
        return MaTV;
    }

    public void setMaTV(ThanhVienDTO MaTV) {
        this.MaTV = MaTV;
    }
    
    public int getMaTT() {
        return MaTT;
    }

    public Date getTGTra() {
        return TGTra;
    }

    public Date getTGMuon() {
        return TGMuon;
    }

    
    public Date getTGVao() {
        return TGVao;
    }

    

    public void setMaTT(int MaTT) {
        this.MaTT = MaTT;
    }

    

    public void setTGMuon(Date TGMuon) {
        this.TGMuon = TGMuon;
    }

    public void setTGVao(Date TGVao) {
        this.TGVao = TGVao;
    }

    public void setTGTra(Date TGTra) {
        this.TGTra = TGTra;
    }

    
}
