/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.ThanhVienDTO;
import DTO.ThongTinSuDungDTO;
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
 * @author PC
 */
public class ThongTinSuDungDAL {
    private static SessionFactory factory;
    
    public ThongTinSuDungDAL(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
            
        }catch(Throwable ex){
            System.out.println("Failed to create sessionFactory object" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public ArrayList<ThongTinSuDungDTO> listThongTinSuDung() {
        Session session = factory.openSession();
        ArrayList<ThongTinSuDungDTO> List = new ArrayList<>();
        Transaction tx = null;
         try{
             tx = session.beginTransaction();
             List employess = session.createQuery("FROM ThongTinSuDungDTO").list();
             for (Iterator iterator = employess.iterator(); iterator.hasNext();) {
                 ThongTinSuDungDTO tt = (ThongTinSuDungDTO) iterator.next();
                 List.add(tt);
             }
         } catch (HibernateException e){
             if (tx != null) tx.rollback();
             e.printStackTrace();
         }
         return List;
    }
    
    
    
}
