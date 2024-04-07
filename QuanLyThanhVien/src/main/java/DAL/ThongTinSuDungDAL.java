package DAL;

import DTO.ThanhVienDTO;
import DTO.ThietBiDTO;
import DTO.ThongTinSuDungDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ThongTinSuDungDAL {
    private static SessionFactory factory;
    ThanhVienDAL tvDAL = new ThanhVienDAL();
    ThietBiDAL tbDAL = new ThietBiDAL();
    
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
    public void VaoKhuVucHocTap(int id){
        Session session = factory.openSession();
        Transaction tx=null;
        try {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<ThongTinSuDungDTO> root = criteriaQuery.from(ThongTinSuDungDTO.class);
            criteriaQuery.select(builder.count(root));
            Long count = session.createQuery(criteriaQuery).getSingleResult();
            ThanhVienDTO idtv = tvDAL.getThanhVienById(id);
            int idTT = count.intValue() + 1;
            Date date = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String TGVao = sdf.format(date);
            
            ThongTinSuDungDTO tt= new ThongTinSuDungDTO(idTT,idtv,date);
            session.save(tt);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void MuonThietBi(ThongTinSuDungDTO tt){
        Session session = factory.openSession();
        Transaction tx=null;
        try {
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
            Root<ThongTinSuDungDTO> root = criteriaQuery.from(ThongTinSuDungDTO.class);
            criteriaQuery.select(builder.count(root));
            Long count = session.createQuery(criteriaQuery).getSingleResult();
            
            
            ThanhVienDTO idtv = tvDAL.getThanhVienById(tt.getMaTV().getMaTV());
            ThietBiDTO idtb= tbDAL.getThanhVienById(tt.getMaTB().getMaTB());
            
            int idTT = count.intValue() + 1;
            
            
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String TGVao = sdf.format(date);
            
            ThongTinSuDungDTO Thongtin= new ThongTinSuDungDTO(idTT,idtv,idtb,tt.getTGMuon(),tt.getTGTra());
            session.save(Thongtin);
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
        ThanhVienDAL tdal=new ThanhVienDAL();
        ThietBiDAL bdal=new ThietBiDAL();
        // Lấy danh sách thông tin sử dụng từ database
//        ArrayList<ThongTinSuDungDTO> danhSach = dal.listThongTinSuDung();
//
//        // In danh sách thông tin sử dụng
//        System.out.println("Danh sách thông tin sử dụng:");
//        for (ThongTinSuDungDTO thongTin : danhSach) {
//            System.out.println("Mã TT: " + thongTin.getMaTT());
//            System.out.println("Mã TV: " + (thongTin.getMaTV() != null ? thongTin.getMaTV().getMaTV() : "null")); // Giả sử bạn muốn in ra mã thành viên
//            System.out.println("Mã TB: " + (thongTin.getMaTB() != null ? thongTin.getMaTB().getMaTB() : "null")); // Giả sử bạn muốn in ra mã thiết bị
//            System.out.println("Thời gian vào: " + (thongTin.getTGVao() != null ? thongTin.getTGVao() : "null"));
//            System.out.println("Thời gian mượn: " + (thongTin.getTGMuon() != null ? thongTin.getTGMuon() : "null"));
//            System.out.println("Thời gian trả: " + (thongTin.getTGTra() != null ? thongTin.getTGTra() : "null"));
//            System.out.println("--------------------------------------");
//        }
//        dal.VaoKhuVucHocTap(1119410001);
            Date date= new Date();
            ThanhVienDTO idtv = tdal.getThanhVienById(1119410001);
            ThietBiDTO idtb= bdal.getThanhVienById(120191);
            ThongTinSuDungDTO tt=new ThongTinSuDungDTO(0,idtv,idtb,date,date);
            dal.MuonThietBi(tt);
    }
}
