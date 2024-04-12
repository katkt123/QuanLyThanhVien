/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.ThongTinSuDungDTO;
import DTO.XuLyViPhamDTO;
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
public class XuLyViPhamDAL {
    
    private static SessionFactory factory;
    
    public XuLyViPhamDAL(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
            
        }catch(Throwable ex){
            System.out.println("Failed to create sessionFactory object" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    
     public ArrayList<XuLyViPhamDTO> listXuLyViPham() {
        Session session = factory.openSession();
        ArrayList<XuLyViPhamDTO> list = new ArrayList<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            list = (ArrayList<XuLyViPhamDTO>) session.createQuery("FROM XuLyViPhamDTO").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    
    public static void main(String[] args) {
        XuLyViPhamDAL a = new XuLyViPhamDAL();
        ArrayList<XuLyViPhamDTO> b= a.listXuLyViPham();
        System.out.print(b.size()+"______________");
    }
}