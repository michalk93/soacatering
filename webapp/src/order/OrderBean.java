package order;

import common.CourseService;
import common.OrderService;
import model.Course;
import model.Order;
import model.OrderStatus;
import model.User;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PrePassivate;
import javax.faces.application.FacesMessage;
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

    @EJB(lookup = "java:jboss/exported/order/OrderServiceImplementation!common.OrderService")
    private OrderService orderService;

    @EJB(lookup = "java:jboss/exported/catalog/CourseServiceImplementation!common.CourseService")
    CourseService courseService;

    @PostConstruct
    public void init(){
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

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pozycja dodana do koszyka", course.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void removeItem(Course course){
        this.courseList.remove(course);
    }

    public String order(){
        User user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        order.setUser(user);
        order.addCourses(courseList);
        orderService.save(order);
        courseList.clear();

        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/top10", new FacesMessage("TOP10", "Nastąpiła zmiana w TOP10"));
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
        orderService.changeStatus(_order);
    }

    public void setDialogComment(Order _order) {
        this.order = _order;
    }

    public void update(){
        orderService.update(order);
        this.order = new Order();
    }

}
