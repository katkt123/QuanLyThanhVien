/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.ThanhVienDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Year;
import java.util.Locale;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import org.apache.poi.ss.usermodel.*;
import org.hibernate.query.Query;

/**
 *
 * @author ASUS
 */
public class ThanhVienDAL {
    private static SessionFactory factory;
    
    public ThanhVienDAL(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
            
        }catch(Throwable ex){
            System.out.println("Failed to create sessionFactory object" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public ArrayList<ThanhVienDTO> listThanhVien() {
        Session session = factory.openSession();
        ArrayList<ThanhVienDTO> List = new ArrayList<>();
        Transaction tx = null;
         try{
             tx = session.beginTransaction();
             List employess = session.createQuery("FROM ThanhVienDTO").list();
             for (Iterator iterator = employess.iterator(); iterator.hasNext();) {
                 ThanhVienDTO tv = (ThanhVienDTO) iterator.next();
                 List.add(tv);
             }
         } catch (HibernateException e){
             if (tx != null) tx.rollback();
             e.printStackTrace();
         }
         return List;
    }
    public ThanhVienDTO getThanhVienById(int id) {
        Session session = factory.openSession();
        ThanhVienDTO thanhVien = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            // Sử dụng phương thức get của session để lấy đối tượng ThanhVienDTO theo id
            thanhVien = session.get(ThanhVienDTO.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return thanhVien;
    }

    public void ThemThanhVien(ThanhVienDTO tv) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer tvienID = null;
        try {
            tx = session.beginTransaction();
             // Sử dụng Criteria để lấy số lượng bản ghi trong bảng ThanhVien
            String hql = "SELECT COUNT(*) FROM ThongTinSuDungDTO";
            Query<Long> query = session.createQuery(hql, Long.class);
            Long count = query.uniqueResult();
            
            String id = "";
            String year = Year.now().toString().substring(2);
            String khoa = tv.getKhoa();
            
            String nganh = tv.getNganh();
            String khoaCode= "";
            
            switch (khoa.toUpperCase(Locale.ROOT)) {
                case "SP KHXH":
                    switch (nganh.toUpperCase(Locale.ROOT)) {
                        case "ĐỊA":
                            khoaCode = "11";
                            break;
                        case "SỬ":
                            khoaCode = "10";
                            break;
                        case "VĂN":
                            khoaCode = "09";
                            break;
                        default:
                            khoaCode = "00";
                            break;
                    }
                    break;
                case "SP KHTN":
                    switch (nganh.toUpperCase(Locale.ROOT)) {
                        case "LÍ":
                            khoaCode = "02";
                            break;
                        case "HÓA":
                            khoaCode = "03";
                            break;
                        case "SINH":
                            khoaCode = "04";
                            break;
                        default:
                            khoaCode = "00";
                            break;
                    }
                    break;
                case "NGOẠI NGỮ":
                    switch (nganh.toUpperCase(Locale.ROOT)) {
                        case "ANH":
                            khoaCode = "13";
                            break;
                        case "NNA":
                            khoaCode = "38";
                            break;
                        default:
                            khoaCode = "00";
                            break;
                    }
                    break;
                case "QTKD":
                    khoaCode = "55";
                    break;
                case "QLGD":
                    switch (nganh.toUpperCase(Locale.ROOT)) {
                        case "TLH":
                            khoaCode = "53";
                            break;
                        default:
                            khoaCode = "00";
                            break;
                    }
                    break;
                case "TOÁN UD":
                    switch (nganh.toUpperCase(Locale.ROOT)) {
                        case "TOÁN":
                            khoaCode = "48";
                            break;
                        default:
                            khoaCode = "00";
                            break;
                    }
                    break;
                case "CNTT":
                    switch (nganh.toUpperCase(Locale.ROOT)) {
                        case "CNTT":
                            khoaCode = "41";
                            break;
                        case "KTPM":
                            khoaCode = "42";
                            break;
                        case "HTTT":
                            khoaCode = "43";
                            break;
                        default:
                            khoaCode = "00";
                            break;
                    }
                    break;
                default:
                    khoaCode = "00";
                    break;
            }

            id = "11"+ year + khoaCode + String.format("%04d", count + 1);
            int idCode= Integer.parseInt(id);
            ThanhVienDTO tvien = new ThanhVienDTO(idCode,tv.getHoTen(), tv.getKhoa(), tv.getNganh(), tv.getSDT());
            
            session.save(tvien);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


 

    public void updateThanhVien(ThanhVienDTO tv) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ThanhVienDTO tvien = (ThanhVienDTO) session.get(ThanhVienDTO.class, tv.getMaTV());
            tvien.setHoTen(tv.getHoTen());
            tvien.setKhoa(tv.getKhoa());
            tvien.setNganh(tv.getNganh());
            tvien.setSDT(tv.getSDT());
            session.update(tvien);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    } 
//    public boolean deleteThanhVien(int tvienID) {
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//
//            
//            String hql = "SELECT COUNT(*) FROM ThongTinSuDungDTO ttsd WHERE ttsd.MaTV = :tvienID";
//            Long count = (Long) session.createQuery(hql)
//                .setParameter("tvienID", tvienID)
//                .uniqueResult();
//
//            if (count > 0) {
//                System.out.println("Không thể xóa thành viên vì đang dính khóa ngoại trong ThongTinSuDungDTO");
//                return false;
//            }
//
//            ThanhVienDTO tvien = (ThanhVienDTO) session.get(ThanhVienDTO.class, tvienID);
//            session.delete(tvien);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return true;
//    }
    public void deleteThanhVien(int tvID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ThanhVienDTO tbi = (ThanhVienDTO) session.get(ThanhVienDTO.class, tvID);
            session.delete(tbi);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void ThemThanhVienExcel(String path) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT COUNT(*) FROM ThongTinSuDungDTO";
            Query<Long> query = session.createQuery(hql, Long.class);
            Long count = query.uniqueResult();

            FileInputStream inputStream = new FileInputStream(new File(path));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

            // Bắt đầu đọc từ hàng thứ hai (index 1)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    Cell idCell =row.getCell(0);
                    Cell nameCell = row.getCell(1);
                    Cell khoaCell = row.getCell(2);
                    Cell nganhCell = row.getCell(3);
                    Cell sdtCell = row.getCell(4);
                    Cell passCell = row.getCell(5);
                    Cell emailCell = row.getCell(6);
                    
                    // Kiểm tra và lưu dữ liệu vào Hibernate
                    if (idCell != null && nameCell != null && khoaCell != null && nganhCell != null && sdtCell != null) {
                        int idCode=0;
                        if (idCell.getCellType() == CellType.NUMERIC) {
                            idCode = (int) idCell.getNumericCellValue();
                            
                        } else if (idCell.getCellType() == CellType.STRING) {
                            String id = idCell.getStringCellValue();
                            idCode = Integer.parseInt(id);
                        }
                        String name = nameCell.getStringCellValue();
                        String khoa = khoaCell.getStringCellValue();
                        String nganh = nganhCell.getStringCellValue();
                        String sdt =  sdtCell.getStringCellValue();
                        String pass = passCell.getStringCellValue();
                        String email = emailCell.getStringCellValue();
                        

                        // Tạo đối tượng ThanhVienDTO từ dữ liệu
                        ThanhVienDTO thanhVien = new ThanhVienDTO(idCode, name, khoa, nganh, sdt, pass,email);
                        // Lưu đối tượng vào cơ sở dữ liệu bằng Hibernate
                        session.save(thanhVien);
                        // Cập nhật giá trị của count
                        count++;
                    }
                }
            }
            // Commit transaction và đóng session
            tx.commit();
            session.close();

            // Đóng workbook và inputStream
            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        }
    }   

    


    public ArrayList<ThanhVienDTO> search(String s) {
        ArrayList<ThanhVienDTO> list = new ArrayList<>();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String hql = "FROM ThanhVienDTO WHERE CAST(MaTV AS string) LIKE :keyword OR HoTen LIKE :keyword OR Khoa LIKE :keyword OR Nganh LIKE :keyword OR SDT LIKE :keyword";
            Query<ThanhVienDTO> query = session.createQuery(hql, ThanhVienDTO.class);
            query.setParameter("keyword", "%" + s + "%");

            list = (ArrayList<ThanhVienDTO>) query.getResultList();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    
   public ArrayList<ThanhVienDTO> XoaTheoNam(int twoDigits) {
        Session session = factory.openSession();
        Transaction tx = null;
        ArrayList<ThanhVienDTO> danhSachCanXoa = new ArrayList<>();

        try {
            tx = session.beginTransaction();
            int thirdDigit = twoDigits / 10;
            int fourthDigit = twoDigits % 10;

            String thirdDigitStr = String.valueOf(thirdDigit);
            String fourthDigitStr = String.valueOf(fourthDigit);

            // Tạo câu lệnh HQL để truy vấn
            String hql = "FROM ThanhVienDTO WHERE SUBSTRING(MaTV, 3, 1) = :thirdDigit AND SUBSTRING(MaTV, 4, 1) = :fourthDigit";
            Query<ThanhVienDTO> query = session.createQuery(hql);
            query.setParameter("thirdDigit", thirdDigitStr);
            query.setParameter("fourthDigit", fourthDigitStr);

            danhSachCanXoa = (ArrayList<ThanhVienDTO>) query.getResultList();

            // Duyệt qua danh sách các đối tượng và kiểm tra dính khóa ngoại trước khi xóa
            

            // Kết thúc giao dịch
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return danhSachCanXoa;
    }

    public int getIdByName(String name) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT tv.maTV FROM ThanhVienDTO tv WHERE tv.ten = :name";
            Integer id = (Integer) session.createQuery(hql)
                .setParameter("name", name)
                .uniqueResult();
            tx.commit();
            return (id != null) ? id : -1; // Trả về -1 nếu không tìm thấy hoặc trả về ID nếu có
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return -1; // Trả về -1 nếu có lỗi xảy ra
        } finally {
            session.close();
        }
    }
   
    

    
    
//    
}
