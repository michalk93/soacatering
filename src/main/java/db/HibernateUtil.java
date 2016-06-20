package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mkolbusz on 6/5/16.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try {
            return new AnnotationConfiguration().configure().buildSessionFactory();
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

    public static String getHashedPassword(String password) {
        try {
            MessageDigest d = MessageDigest.getInstance("SHA-256");
            d.update(password.getBytes());
            byte byteData[] = d.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
