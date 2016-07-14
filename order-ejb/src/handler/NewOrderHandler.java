package handler;

import event.NewOrderEvent;
import model.Order;
import payment.OrderPaymentMethod;
import payment.PaymentMethodFactory;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

/**
 * Created by mkolbusz on 7/14/16.
 */
@Stateless
public class NewOrderHandler {

    public void handleNewOrderEvent(@Observes NewOrderEvent newOrderEvent) {
        System.out.println("handleNewOrderEvent");
    }

    public void orderPayment(@Observes NewOrderEvent orderEvent) {
        System.out.println("orderPayment");
        Order order = orderEvent.getOrder();
        OrderPaymentMethod paymentMethod = PaymentMethodFactory.getPaymentMethod(order.getPaymentMethod());
        paymentMethod.handlePayment(order);
    }
}
