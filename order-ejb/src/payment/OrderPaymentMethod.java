package payment;

import model.Order;

/**
 * Created by mkolbusz on 7/14/16.
 */
public interface OrderPaymentMethod {
    void handlePayment(Order order);
}
