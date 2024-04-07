/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.ThietBiDTO;
import java.util.ArrayList;
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
import org.hibernate.query.Query;

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
         finally {
            session.close();
        }
         return List;
    }
    
    public ArrayList<ThietBiDTO> listThietBiComboBox(String id) {
        Session session = factory.openSession();
        ArrayList<ThietBiDTO> List = new ArrayList<>();
        Transaction tx = null;
         try{
                tx = session.beginTransaction();
                String hql = "FROM ThietBiDTO WHERE CAST(MaTB AS string) LIKE :value";
                Query query = session.createQuery(hql);
                query.setParameter("value", "" + id + "%");
                List result = query.list();
                for (Iterator iterator = result.iterator(); iterator.hasNext();) {
                 ThietBiDTO tv = (ThietBiDTO) iterator.next();
                 List.add(tv);
             }

         } catch (HibernateException e){
             if (tx != null) tx.rollback();
             e.printStackTrace();
         }
         finally {
            session.close();
        }
         return List;
    }

    
    public void addThietBi(ThietBiDTO tb) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer tvienID = null;
        try {
            tx = session.beginTransaction();
            ThietBiDTO tbDTO = new ThietBiDTO(listThietBi().size()+1,tb.getTenTB(),tb.getMoTaTB());
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
    
    
     public boolean kiemTraMaThietBiTonTai(int maThietBi) {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("SELECT COUNT(*) FROM ThietBiDTO WHERE MaTB = :maThietBi");
            query.setParameter("maThietBi", maThietBi);
            Long count = (Long) query.uniqueResult();
            if (count != null && count > 0) {
                return true; // Mã thiết bị đã tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    
}
