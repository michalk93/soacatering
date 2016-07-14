import common.OrderService;
import db.HibernateUtil;
import event.NewOrderEvent;
import event.OrderChangeStatusEvent;
import event.OrderEvent;
import model.Order;
import model.OrderStatus;
import model.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by mkolbusz on 6/28/16.
 */
@Stateless
public class OrderServiceImplementation implements OrderService {

    @Inject
    Event<OrderEvent> event;


    @Override
    public boolean save(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            order.setCreatedAt(new Date());
            OrderStatus status = (OrderStatus) session.createCriteria(OrderStatus.class).add(Restrictions.eq("name", "nowe")).uniqueResult();
            order.setStatus(status);
            event.fire(new NewOrderEvent(order));
            session.save(order);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return true;
    }

    @Override
    public void cancel(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        OrderStatus status = (OrderStatus) session.createCriteria(OrderStatus.class).add(Restrictions.eq("name", "anulowane")).uniqueResult();
        HibernateUtil.shutdown();
        order.setStatus(status);
        changeStatus(order);
    }

    @Override
    public List getByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List orders = session.createCriteria(Order.class).add(Restrictions.eq("user", user)).addOrder(org.hibernate.criterion.Order.desc("createdAt")).list();
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
        List statuses = session.createCriteria(Order.class).addOrder(org.hibernate.criterion.Order.desc("orderId")).list();
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

    @Override
    public boolean changeStatus(Order order) {
        this.update(order);
        event.fire(new OrderChangeStatusEvent(order));
        return true;
    }


}
