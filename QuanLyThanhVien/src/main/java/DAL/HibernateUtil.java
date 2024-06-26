
package DAL;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.service.ServiceRegistry;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = (SessionFactory) UUIDGenerator.buildSessionFactoryUniqueIdentifierGenerator();
    
    private static SessionFactory buildSessionFactory(){
        ServiceRegistry serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
        
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
    public static void close(){
        getSessionFactory().close();
    }

    
}
