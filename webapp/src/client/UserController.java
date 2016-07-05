package client;

import common.OrderService;
import db.HibernateUtil;
import model.Order;
import model.Subscription;
import model.User;
import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by mkolbusz on 6/28/16.
 */
@ManagedBean
@RequestScoped
public class UserController {
    User user;

    @EJB(lookup = "java:jboss/exported/order/OrderServiceImplementation!common.OrderService")
    OrderService orderService;

    @PostConstruct
    public void init(){
        user = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders(){
        return (List<Order>)orderService.getByUser(user);
    }

    public List<Subscription> getSubscriptions(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Subscription> subscriptions = user.getSubscriptions();
        session.getTransaction().commit();
        return subscriptions;
    }
}
