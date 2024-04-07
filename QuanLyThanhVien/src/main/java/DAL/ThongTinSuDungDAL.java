package DAL;

import DTO.ThongTinSuDungDTO;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ThongTinSuDungDAL {
    private static SessionFactory factory;
    
    public ThongTinSuDungDAL() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Failed to create sessionFactory object" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public ArrayList<ThongTinSuDungDTO> listThongTinSuDung() {
        Session session = factory.openSession();
        ArrayList<ThongTinSuDungDTO> list = new ArrayList<>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            list = (ArrayList<ThongTinSuDungDTO>) session.createQuery("FROM ThongTinSuDungDTO").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public void themThongTinSuDung(ThongTinSuDungDTO thongTin) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(thongTin);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void capNhatThongTinSuDung(ThongTinSuDungDTO thongTin) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(thongTin);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void xoaThongTinSuDung(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ThongTinSuDungDTO thongTin = (ThongTinSuDungDTO) session.get(ThongTinSuDungDTO.class, id);
            session.delete(thongTin);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public static void main(String[] args) {
        ThongTinSuDungDAL dal = new ThongTinSuDungDAL();

        // Lấy danh sách thông tin sử dụng từ database
        ArrayList<ThongTinSuDungDTO> danhSach = dal.listThongTinSuDung();

        // In danh sách thông tin sử dụng
        System.out.println("Danh sách thông tin sử dụng:");
        for (ThongTinSuDungDTO thongTin : danhSach) {
            System.out.println("Mã TT: " + thongTin.getMaTT());
            System.out.println("Mã TV: " + (thongTin.getMaTV() != null ? thongTin.getMaTV().getMaTV() : "null")); // Giả sử bạn muốn in ra mã thành viên
            System.out.println("Mã TB: " + (thongTin.getMaTB() != null ? thongTin.getMaTB().getMaTB() : "null")); // Giả sử bạn muốn in ra mã thiết bị
            System.out.println("Thời gian vào: " + (thongTin.getTGVao() != null ? thongTin.getTGVao() : "null"));
            System.out.println("Thời gian mượn: " + (thongTin.getTGMuon() != null ? thongTin.getTGMuon() : "null"));
            System.out.println("Thời gian trả: " + (thongTin.getTGTra() != null ? thongTin.getTGTra() : "null"));
            System.out.println("--------------------------------------");
        }
    }
}
