import common.OrderService;
import db.HibernateUtil;
import model.Order;
import model.OrderStatus;
import model.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

/**
 * Created by mkolbusz on 6/28/16.
 */
@Stateless
public class OrderServiceImplementation implements OrderService {
    @Override
    public boolean save(Order order) {
        order.setCreatedAt(new Date());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        OrderStatus status = (OrderStatus) session.createCriteria(OrderStatus.class).add(Restrictions.eq("name", "nowe")).uniqueResult();
        order.setStatus(status);
        session.save(order);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return true;
    }

    @Override
    public void cancel(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        OrderStatus status = (OrderStatus) session.createCriteria(OrderStatus.class).add(Restrictions.eq("name", "anulowane")).uniqueResult();
        System.err.println("STATUS: " + status);
        order.setStatus(status);
        session.update(order);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }

    @Override
    public List getByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List orders = session.createCriteria(Order.class).add(Restrictions.eq("user", user)).list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return orders;
    }

    @Override
    public List getOrderStatuses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List statuses = session.createCriteria(OrderStatus.class).list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return statuses;
    }

    @Override
    public List<Order> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List statuses = session.createCriteria(Order.class).list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return statuses;
    }

    @Override
    public boolean update(Order order) {
        order.setCreatedAt(new Date());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return true;
    }
}
