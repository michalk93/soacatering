package order;

import common.OrderService;
import model.Course;
import model.Order;
import model.OrderStatus;
import model.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PrePassivate;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.facelets.FaceletContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mkolbusz on 6/27/16.
 */
@ManagedBean
@SessionScoped
public class OrderBean {
    private List<Course> courseList = new ArrayList<>();
    private Order order;
    private User user;

    @EJB(lookup = "java:jboss/exported/order/OrderServiceImplementation!common.OrderService")
    private OrderService orderService;

    @PostConstruct
    public void init(){
        user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        order = new Order();
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void addItem(Course course){
        this.courseList.add(course);
    }

    public void removeItem(Course course){
        this.courseList.remove(course);
    }

    public String order(){
        order.setUser(user);
        order.addCourses(courseList);
        orderService.save(order);
        courseList.clear();
        return "orderConfirm?faces-redirect=true";
    }

    public void cancel(Order order) {
        orderService.cancel(order);
    }

    public List<OrderStatus> getOrderStatuses(){
        return orderService.getOrderStatuses();
    }

    public List<Order> getAllOrders(){
        return orderService.getAll();
    }

    public void changeStatus(Order _order) {
        _order.setStatus(order.getStatus());
        System.err.println(_order.getOrderItems() + ": " +_order.getStatus().getName() + " - " + _order.getStatus().getStatusID());
        orderService.update(_order);
    }

    public void setDialogComment(Order _order) {
        this.order = _order;
    }

    public void update(){
        orderService.update(order);
        this.order = new Order();
    }

}
