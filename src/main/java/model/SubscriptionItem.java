package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by mkolbusz on 6/29/16.
 */
@Entity
@Table(name = "subscriptions_courses")
@XmlRootElement
public class SubscriptionItem implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "subscription_course_id")
    private int subscriptionItemId;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    public SubscriptionItem(){}

    public SubscriptionItem(Subscription subscription, Course course){
        this.subscription = subscription;
        this.course = course;
    }

    public int getSubscriptionItemId() {
        return subscriptionItemId;
    }

    public void setSubscriptionItemId(int subscriptionItemId) {
        this.subscriptionItemId = subscriptionItemId;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
