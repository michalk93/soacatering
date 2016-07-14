package handler;

import common.MailService;
import db.HibernateUtil;
import event.CategoryRemoveEvent;
import model.Course;
import model.Subscription;
import model.SubscriptionItem;
import org.hibernate.Session;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by mkolbusz on 7/14/16.
 */
@Stateless
public class CategoryEventsHandler {

    @EJB(lookup = "java:jboss/exported/mail-service-ejb/MailServiceImplementation!common.MailService")
    private MailService mailService;

    public void removeCategoryHandler(@Observes CategoryRemoveEvent removeEvent) {
        Set<Course> courses = removeEvent.getCategory().getCourses();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Subscription> subscriptionList = session.createCriteria(Subscription.class).list();
        deleteSubscriptionsContainingCourses(courses, subscriptionList, session);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }

    private void deleteSubscriptionsContainingCourses(Set<Course> courses, List<Subscription> subscriptions, Session session){
        subscriptions.stream().forEach(subscription -> {
            Optional<SubscriptionItem> result = subscription.getSubscriptionItems().stream()
                    .filter(subscriptionItem -> courses.contains(subscriptionItem.getCourse())).findFirst();
            if(result.isPresent()){
                session.delete(subscription);
                sendInfoEmail(subscription);
            }
        });
    }

    private void sendInfoEmail(Subscription subscription){
        mailService.sendMail(subscription.getUser().getEmail(), "Anulowanie subskrybcji",
                "Subskrybcja o numerze #" + subscription.getSubscriptionId() + " została anulowana. " +
                        "Zachęcamy do storzenia kolejnej subskrybcji");
    }
}
