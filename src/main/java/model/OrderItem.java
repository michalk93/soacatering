package model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mkolbusz on 6/28/16.
 */
@Entity
@Table(name = "orders_items")
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private int orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    public OrderItem() {}

    public OrderItem(Order order, Course course){
        this.order = order;
        this.course = course;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
