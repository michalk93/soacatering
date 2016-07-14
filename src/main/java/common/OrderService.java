package common;

import model.Order;
import model.OrderStatus;
import model.User;
import org.apache.tools.ant.taskdefs.condition.Or;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by mkolbusz on 6/28/16.
 */
@Remote
public interface OrderService {
    boolean save(Order order);
    void cancel(Order order);
    List getByUser(User user);
    List getOrderStatuses();

    List<Order> getAll();

    boolean update(Order order);

    boolean changeStatus(Order order);

    boolean payFromSalary(Order order);
}
