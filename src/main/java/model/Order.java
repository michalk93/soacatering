package model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mkolbusz on 6/28/16.
 */
@Entity
@Table(name = "orders", schema = "soacatering")
@XmlRootElement
public class Order implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "order_id", nullable = false)
    int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Basic
    @Column(name = "created_at", nullable = false)
    Date createdAt;

    @Basic
    @Column(name = "shipping_address", nullable = false, length = 512)
    String shippingAddress;

    @Basic
    @Column(name = "shipping_time", nullable = false)
    Date shippingTime;

    @Basic
    @Column(name = "comment", nullable = true)
    String comment;

    @ManyToOne
    @JoinColumn(name = "status_id")
    OrderStatus status;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    List<OrderItem> orderItems = new ArrayList<>();


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void addCourses(List<Course> courses){
        for (Course c : courses) {
            orderItems.add(new OrderItem(this, c));
        }
    }

    public void addCourse(Course course) {
        orderItems.add(new OrderItem(this, course));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order that = (Order) o;

        if (orderId != that.orderId) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (shippingAddress != null ? !shippingAddress.equals(that.shippingAddress) : that.shippingAddress != null)
            return false;
        if (shippingTime != null ? !shippingTime.equals(that.shippingTime) : that.shippingTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (shippingAddress != null ? shippingAddress.hashCode() : 0);
        result = 31 * result + (shippingTime != null ? shippingTime.hashCode() : 0);
        return result;
    }
}
