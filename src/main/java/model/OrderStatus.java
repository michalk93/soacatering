package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mkolbusz on 7/3/16.
 */
@Entity
@Table(name = "orders_statuses")
public class OrderStatus implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "status_id")
    int statusID;

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    List<Order> orders;

    public OrderStatus(){}

    public OrderStatus(String name) {
        this.setName(name);
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getName().toLowerCase();
    }


    @Override
    public boolean equals(Object obj) {
        OrderStatus status = (OrderStatus)obj;
        if(status.getName().equals(this.getName())){
            return true;
        }
        return false;
    }
}
