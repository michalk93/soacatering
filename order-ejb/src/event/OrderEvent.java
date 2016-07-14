package event;

import model.Order;

/**
 * Created by mkolbusz on 7/14/16.
 */
public abstract class OrderEvent {
    private Order order;

    public OrderEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
