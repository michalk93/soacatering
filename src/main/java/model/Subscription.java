package model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mkolbusz on 6/29/16.
 */
@Entity
@Table(name = "subscriptions")
public class Subscription implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "subscription_id", nullable = false)
    int subscriptionId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Basic
    @Column(name = "shipping_address", nullable = false, length = 512)
    String shippingAddress;

    @Basic
    @Column(name = "shipping_time", nullable = false)
    Date shippingTime;

    @Basic
    @Column(name = "payment_method", nullable = false)
    String paymentMethod;

    @Basic
    @Column(name = "weekday")
    int weekDay;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "subscription",cascade = CascadeType.ALL)
    List<SubscriptionItem> subscriptionItems = new ArrayList<>();


    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Date shippingTime) {
        this.shippingTime = shippingTime;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public List<SubscriptionItem> getSubscriptionItems() {
        return subscriptionItems;
    }

    public void setSubscriptionItems(List<SubscriptionItem> subscriptionItems) {
        this.subscriptionItems = subscriptionItems;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
