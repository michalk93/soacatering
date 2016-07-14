package event;

import model.Order;

/**
 * Created by mkolbusz on 7/14/16.
 */
public class OrderChangeStatusEvent extends OrderEvent {

    public OrderChangeStatusEvent(Order order) {
        super(order);
    }

}
