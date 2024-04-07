package DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "xuly")
public class XuLyViPhamDTO {

    @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MaXL")
  private int MaXL;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "MaTV", nullable = false, foreignKey = @ForeignKey(name = "fk_ThanhVienDTO"))
  private ThanhVienDTO thanhVien;

    @Column(name = "HinhThucXL")
    private String HinhThucXL;

    @Column(name = "SoTien")
    private int SoTien;

    @Column(name = "NgayXL")
    private Date NgayXL;

    @Column(name = "TrangThaiXL")
    private int TrangThaiXL;

    public XuLyViPhamDTO() {
    }

    public XuLyViPhamDTO(ThanhVienDTO thanhVien, String HinhThucXL, int SoTien, Date NgayXL, int TrangThaiXL) {
        this.thanhVien = thanhVien;
        this.HinhThucXL = HinhThucXL;
        this.SoTien = SoTien;
        this.NgayXL = NgayXL;
        this.TrangThaiXL = TrangThaiXL;
    }

    public int getMaXL() {
        return MaXL;
    }

    public void setMaXL(int MaXL) {
        this.MaXL = MaXL;
    }

    public ThanhVienDTO getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVienDTO thanhVien) {
        this.thanhVien = thanhVien;
    }

    public String getHinhThucXL() {
        return HinhThucXL;
    }

    public void setHinhThucXL(String HinhThucXL) {
        this.HinhThucXL = HinhThucXL;
    }

    public int getSoTien() {
        return SoTien;
    }

    public void setSoTien(int SoTien) {
        this.SoTien = SoTien;
    }

    public Date getNgayXL() {
        return NgayXL;
    }

    public void setNgayXL(Date NgayXL) {
        this.NgayXL = NgayXL;
    }

    public int getTrangThaiXL() {
        return TrangThaiXL;
    }

    public void setTrangThaiXL(int TrangThaiXL) {
        this.TrangThaiXL = TrangThaiXL;
    }

}
