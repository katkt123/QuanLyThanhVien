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

//    public void updateDepartment(Department tv) {
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            Department tvien = (Department) session.get(Department.class, tv.getDepartmentID());
//            tvien.setName(tv.getName());
//            tvien.setStartDate(tv.getStartDate());
//            tvien.setBudget(tv.getBudget());
//            tvien.setAdministrator(tv.getAdministrator());
//            session.update(tvien);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    } 
//    public void deleteDepartment(Integer tvienID) {
//        Session session = factory.openSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            Department tvien = (Department) session.get(Department.class, tvienID);
//            session.delete(tvien);
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//    
}
