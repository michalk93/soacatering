package handler;

import common.MailService;
import event.OrderChangeStatusEvent;
import model.Order;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.QueueConnectionFactory;

/**
 * Created by mkolbusz on 7/14/16.
 */
@Stateless
public class OrderStatusChangeHandler {

    @EJB(lookup = "java:jboss/exported/mail-service-ejb/MailServiceImplementation!common.MailService")
    MailService mailService;

    @Resource(lookup = "java:jboss/factory/DeliveryConnectionFactory", mappedName="deliveryConnectionFactory")
    private QueueConnectionFactory connectionFactory;

    @Resource(mappedName="deliveryQueue", lookup = "java:jboss/jms/queue/deliveryQueue")
    private Destination deliveryQueue;

    @Inject
    private JMSContext context;


    public void handleOrderStatusChange(@Observes OrderChangeStatusEvent changeStatusEvent) {
        Order order = changeStatusEvent.getOrder();

        if(order.getStatus().getName().equals("w dostarczeniu")) {
            context = connectionFactory.createContext();

            String message = "\tAdres: " + order.getShippingAddress() + "\n" +
                    "\tPreferowany czas: " + order.getShippingTime() + "\n" +
                    "\tKomentarz: " + order.getComment();
            context.createProducer().send(deliveryQueue, message);
            context.close();
        }
        else if(order.getStatus().getName().equalsIgnoreCase("anulowane")) {
            mailService.sendMail(order.getUser().getEmail(), "Anulowanie zamówienia",
                    "Zamówienie numer #" + order.getOrderId() + " zostało anulowane. Zachęcamy do złożenia kolejnego zamówienia.");
            return;
        }

        mailService.sendMail(order.getUser().getEmail(), "Zmiana statusu zamówienia",
                "Status zamówienia numer #" + order.getOrderId() + " został zmieniony na " + order.getStatus().getName());
    }
}
