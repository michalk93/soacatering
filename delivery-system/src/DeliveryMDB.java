import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by mkolbusz on 7/8/16.
 */
@MessageDriven(name = "DeliveryMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/jms/queue/deliveryQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")

}, mappedName = "jms/deliveryQueue")
public class DeliveryMDB implements MessageListener {

    @Override
    public void onMessage(Message message) {

        if(message instanceof TextMessage){
            TextMessage txtMsg = (TextMessage)message;
            try {
                System.err.println("NOWA DOSTAWA W SYSTEMIE: \n" + txtMsg.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
