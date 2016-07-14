package event;

import model.Order;

/**
 * Created by mkolbusz on 7/14/16.
 */
public class NewOrderEvent extends OrderEvent {

    public NewOrderEvent(Order order) {
        super(order);
    }
}
