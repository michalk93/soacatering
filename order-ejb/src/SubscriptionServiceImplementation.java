import common.SubscriptionService;
import db.HibernateUtil;
import model.Subscription;
import model.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by mkolbusz on 6/29/16.
 */
@Stateless
public class SubscriptionServiceImplementation implements SubscriptionService {

    @Override
    public boolean save(Subscription subscription) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(subscription);
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.shutdown();
            return false;
        }
        HibernateUtil.shutdown();
        return true;
    }

    @Override
    public List<Subscription> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Subscription> subscriptions = session.createCriteria(Subscription.class).list();
        HibernateUtil.shutdown();
        return subscriptions;
    }

    @Override
    public List<Subscription> getBelongToUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Subscription> subscriptions = session.createCriteria(Subscription.class).add(Restrictions.eq("user", user)).list();
        HibernateUtil.shutdown();
        return subscriptions;
    }

    @Override
    public boolean update(Subscription subscription) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(subscription);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return true;
    }

    @Override
    public boolean remove(Subscription subscription) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(subscription);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return true;
    }
}
