/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DTO.ThanhVienDTO;
import DTO.ThongTinSuDungDTO;
import DTO.XuLyViPhamDTO;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.sql.Timestamp;
import java.util.Iterator;

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
    
    public boolean kiemTraTrangThai(XuLyViPhamDTO xuLyViPham) {
        Session session = factory.openSession();
        Transaction tx = null;
        boolean trangThaiXL = false;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT TrangThaiXL FROM XuLyViPhamDTO WHERE MaTV = :maTV");
            query.setParameter("maTV", xuLyViPham.getMaTV());
            Integer trangThai = (Integer) query.uniqueResult();
            if (trangThai != null && trangThai == 1) {
                trangThaiXL = true;
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return trangThaiXL;
    }


    public void themXuLyViPham(XuLyViPhamDTO xuLyViPham) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(xuLyViPham);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void xoaXuLyViPham(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            XuLyViPhamDTO xlvp = (XuLyViPhamDTO) session.get(XuLyViPhamDTO.class, id);
            session.delete(xlvp);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public ArrayList<XuLyViPhamDTO> search(String s) {
        ArrayList<XuLyViPhamDTO> list = new ArrayList<>();
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            String hql = "FROM XuLyViPhamDTO WHERE CAST(MaXL AS string) LIKE :keyword OR MaTV LIKE :keyword OR HinhThucXL LIKE :keyword";
            Query<XuLyViPhamDTO> query = session.createQuery(hql, XuLyViPhamDTO.class);
            query.setParameter("keyword", "%" + s + "%");

            list = (ArrayList<XuLyViPhamDTO>) query.getResultList();

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

    public void updateXuLyViPham(XuLyViPhamDTO xuLyViPhamDTO) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            XuLyViPhamDTO existingXuLyViPham = session.get(XuLyViPhamDTO.class, xuLyViPhamDTO.getMaXL());

            if (existingXuLyViPham != null) {
                existingXuLyViPham.setHinhThucXL(xuLyViPhamDTO.getHinhThucXL());
                existingXuLyViPham.setSoTien(xuLyViPhamDTO.getSoTien());
                existingXuLyViPham.setTrangThaiXL(xuLyViPhamDTO.getTrangThaiXL());
                session.update(existingXuLyViPham);

                transaction.commit();
            } else {
                System.out.println("Không tìm thấy đối tượng cần cập nhật.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

        public void updateXuLyTrangThai(XuLyViPhamDTO xuLyViPhamDTO) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            XuLyViPhamDTO existingXuLyViPham = session.get(XuLyViPhamDTO.class, xuLyViPhamDTO.getMaXL());

            if (existingXuLyViPham != null) {
                existingXuLyViPham.setTrangThaiXL(xuLyViPhamDTO.getTrangThaiXL());
                session.update(existingXuLyViPham);

                transaction.commit();
            } else {
                System.out.println("Không tìm thấy đối tượng cần cập nhật.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public XuLyViPhamDTO getXuLyViPhamByMaXL(int maXL) {
        XuLyViPhamDTO xuLyViPhamDTO = null;
        Session session = factory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            xuLyViPhamDTO = session.get(XuLyViPhamDTO.class, maXL);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return xuLyViPhamDTO;
    }
    
    public ArrayList<ThongTinSuDungDTO> listQuaHan() {
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
    
    public static void main(String[] args) {
        XuLyViPhamDAL a = new XuLyViPhamDAL();
        ArrayList<XuLyViPhamDTO> b= a.listXuLyViPham();
        System.out.print(b.size()+"______________");
    }
}
