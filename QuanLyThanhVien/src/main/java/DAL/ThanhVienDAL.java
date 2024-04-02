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
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import org.apache.poi.ss.usermodel.*;

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
    public void ThemThanhVien(ThanhVienDTO tv) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer tvienID = null;
        try {
            tx = session.beginTransaction();
            ThanhVienDTO tvien = new ThanhVienDTO(tv.getHoTen(), tv.getKhoa(), tv.getNganh(), tv.getSDT());
            tvien.setMaTV(initID()+1);
            session.save(tvien);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public int initID() {
        int id = 0;
        Session session = null;
        try {
            session = factory.openSession();
            // Sử dụng Criteria để lấy số lượng bản ghi trong bảng person
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<ThanhVienDTO> root = criteriaQuery.from(ThanhVienDTO.class);
            criteriaQuery.select(builder.count(root));
            Long count = session.createQuery(criteriaQuery).getSingleResult();
            id = count.intValue();
        } catch (HibernateException e) {
            System.out.println("Init Sinh Vien: " + e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
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
    public void deleteThanhVien(int tvienID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ThanhVienDTO tvien = (ThanhVienDTO) session.get(ThanhVienDTO.class, tvienID);
            session.delete(tvien);
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
            FileInputStream inputStream = new FileInputStream(new File(path));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên

            tx = session.beginTransaction();

            // Bắt đầu đọc từ hàng thứ hai (index 1)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    // Lấy dữ liệu từ các ô trong hàng
                    Cell nameCell = row.getCell(0);
                    Cell khoaCell = row.getCell(1);
                    Cell nganhCell = row.getCell(2);
                    Cell sdtCell = row.getCell(3);

                    // Kiểm tra và lưu dữ liệu vào Hibernate
                    if (nameCell != null && khoaCell != null && nganhCell != null && sdtCell != null) {
                        int id = initID()+1;
                        String name = nameCell.getStringCellValue();
                        String khoa = khoaCell.getStringCellValue();
                        String nganh = nganhCell.getStringCellValue();
                        int sdt = (int) sdtCell.getNumericCellValue();

                        // Tạo đối tượng ThanhVienDTO từ dữ liệu
                        ThanhVienDTO thanhVien = new ThanhVienDTO();
                        thanhVien.setMaTV(id);
                        thanhVien.setHoTen(name);
                        thanhVien.setKhoa(khoa);
                        thanhVien.setNganh(nganh);
                        thanhVien.setSDT(sdt);

                        // Lưu đối tượng vào cơ sở dữ liệu bằng Hibernate
                        session.save(thanhVien);
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
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ThanhVienDTO> criteria = builder.createQuery(ThanhVienDTO.class);
            Root<ThanhVienDTO> root = criteria.from(ThanhVienDTO.class);

            // Tạo biểu thức để kiểm tra MaTV và SDT là kiểu int
            Expression<Integer> maTVExpression = root.get("MaTV").as(Integer.class);
            Expression<Integer> sdtExpression = root.get("SDT").as(Integer.class);

            // Tạo điều kiện cho các cột
            Predicate predicate = builder.or(
                    builder.like(maTVExpression.as(String.class), "%" + s + "%"),
                    builder.like(root.get("HoTen"), "%" + s + "%"),
                    builder.like(root.get("Khoa"), "%" + s + "%"),
                    builder.like(root.get("Nganh"), "%" + s + "%"),
                    builder.like(sdtExpression.as(String.class), "%" + s + "%")
            );

            // Áp dụng điều kiện và lấy kết quả
            criteria.select(root).where(predicate);
            list = (ArrayList<ThanhVienDTO>) session.createQuery(criteria).getResultList();
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


//    
}
