/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.ThietBiDTO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author DELL
 */
public class ThietBiDAL {
    private static SessionFactory factory;
    
    public ThietBiDAL(){
        try {
            factory = new Configuration().configure().buildSessionFactory();

        }catch(Throwable ex){
            System.out.println("Failed to create sessionFactory object" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public ArrayList<ThietBiDTO> listThietBi() {
        Session session = factory.openSession();
        ArrayList<ThietBiDTO> List = new ArrayList<>();
        Transaction tx = null;
         try{
             tx = session.beginTransaction();
             List employess = session.createQuery("FROM ThietBiDTO").list();
             for (Iterator iterator = employess.iterator(); iterator.hasNext();) {
                 ThietBiDTO tv = (ThietBiDTO) iterator.next();
                 List.add(tv);
             }
         } catch (HibernateException e){
             if (tx != null) tx.rollback();
             e.printStackTrace();
         }
         return List;
    }
    
    
    public void addThietBi(ThietBiDTO tb) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer tvienID = null;
        try {
            tx = session.beginTransaction();
            tb.setMaTB(listThietBi().size()+1);
            session.save(tb);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void updateThietBi(ThietBiDTO tb) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ThietBiDTO tbi = (ThietBiDTO) session.get(ThietBiDTO.class, tb.getMaTB());
            tbi.setMoTaTB(tb.getMoTaTB());
            tbi.setTenTB(tb.getTenTB());
            session.update(tbi);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    } 
    public void deleteThietBi(int tbiID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ThietBiDTO tbi = (ThietBiDTO) session.get(ThietBiDTO.class, tbiID);
            session.delete(tbi);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
//    public static void main(String[] args) {
//        ThietBiDAL aL = new ThietBiDAL();
//        for (int i = 0; i < 10; i++) {
//            ThietBiDTO tb = new ThietBiDTO();
//            tb.setTenTB("Tên thiết bị " + i); // Thiết lập tên cho thiết bị
//            tb.setMoTaTB("Mô tả thiết bị " + i); // Thiết lập mô tả cho thiết bị
//            aL.addThietBi(tb); // Thêm thiết bị vào cơ sở dữ liệu
//
//        }
//        
//    }
    
}
