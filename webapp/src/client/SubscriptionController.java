package client;

import common.CourseService;
import common.SubscriptionService;
import model.*;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

/**
 * Created by mkolbusz on 6/29/16.
 */
@ManagedBean
@ViewScoped
public class SubscriptionController {
    Subscription subscription;

    List<Subscription> subscriptions;

    @EJB(lookup = "java:jboss/exported/catalog/CourseServiceImplementation!common.CourseService")
    CourseService courseService;

    @EJB(lookup = "java:jboss/exported/order/SubscriptionServiceImplementation!common.SubscriptionService")
    SubscriptionService subscriptionService;

    @PostConstruct
    public void init(){
        subscription = new Subscription();
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public List<Course> getCourses(){
        return courseService.getAll();
    }

    public void addCourse(Course course){
        subscription.getSubscriptionItems().add(new SubscriptionItem(subscription, course));
        FacesMessage msg = new FacesMessage("Pozycja dodana pomyślnie", course.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void removeCourse(Course course){
        System.err.println("TO REMOVE: " + course);
        subscription.getSubscriptionItems().remove(course);
    }

    public void save(){
        User user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        subscription.setUser(user);
        if(subscriptionService.save(subscription)){
            FacesMessage msg = new FacesMessage("Subskrybcja zapisana pomyślnie");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }else {
            FacesMessage msg = new FacesMessage("Wystąpiły błędy podczas zapisu");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void update(){
        subscriptionService.update(subscription);
    }

    public void remove(Subscription subscription){
        User user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        user.getSubscriptions().remove(subscription);
        subscriptionService.remove(subscription);
    }

    public List<Subscription> getUserSubscriptions(){
        User user = (User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        return subscriptionService.getBelongToUser(user);
    }
}
