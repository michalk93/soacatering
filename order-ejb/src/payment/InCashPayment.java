package payment;

import model.Order;

/**
 * Created by mkolbusz on 7/14/16.
 */
public class InCashPayment implements OrderPaymentMethod {

    @Override
    public void handlePayment(Order order) {
        order.setPaymentMethod("GOTÓWKA");
    }
}
