package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by mkolbusz on 6/5/16.
 */

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try {
            System.out.println("BUILD HIBERNATE SESSION");
            Configuration configuration = new Configuration();
            configuration.configure();
            return configuration.buildSessionFactory();
        }catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw  new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void shutdown(){
        getSessionFactory().close();
    }
}
