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
             List employess = session.createQuery("FROM ThanhVien").list();
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
    
}
