import common.OrderService;
import db.HibernateUtil;
import event.OrderChangeStatusEvent;
import event.OrderEvent;
import model.Order;
import model.OrderStatus;
import model.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.io.BufferedOutputStream;
import java.io.StringReader;
import java.util.Date;
import java.util.List;

/**
 * Created by mkolbusz on 6/28/16.
 */
@Stateless
public class OrderServiceImplementation implements OrderService {

    @Inject
    Event<OrderEvent> event;


    @Override
    public boolean save(Order order) {
        order.setCreatedAt(new Date());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        OrderStatus status = (OrderStatus) session.createCriteria(OrderStatus.class).add(Restrictions.eq("name", "nowe")).uniqueResult();
        order.setStatus(status);
        paymentMethodInvoke(order);
        session.save(order);
        session.getTransaction().commit();
        HibernateUtil.shutdown();

        return true;
    }

    private void paymentMethodInvoke(Order order) {
        if(order.getPaymentMethod().equalsIgnoreCase("potrącenie")){
            if(order.getUser().getCompany() != null) {
                payFromSalary(order);
            }else {
                order.setPaymentMethod("GOTÓWKA");
            }
        }
    }

    @Override
    public void cancel(Order order) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        OrderStatus status = (OrderStatus) session.createCriteria(OrderStatus.class).add(Restrictions.eq("name", "anulowane")).uniqueResult();
        HibernateUtil.shutdown();
        order.setStatus(status);
        changeStatus(order);
    }

    @Override
    public List getByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List orders = session.createCriteria(Order.class).add(Restrictions.eq("user", user)).list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return orders;
    }

    @Override
    public List getOrderStatuses() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List statuses = session.createCriteria(OrderStatus.class).list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return statuses;
    }

    @Override
    public List<Order> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List statuses = session.createCriteria(Order.class).list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return statuses;
    }

    @Override
    public boolean update(Order order) {
        order.setCreatedAt(new Date());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return true;
    }

    @Override
    public boolean changeStatus(Order order) {
        this.update(order);


        event.fire(new OrderChangeStatusEvent(order));

        return true;
    }

    @Override
    public boolean payFromSalary(Order order) {
        String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hs=\"http://paymentsystem.soap/\">\n" +
                "<soapenv:Header></soapenv:Header>\n" +
                "<soapenv:Body>\n" +
                "<hs:getMoneyFromSalary>\n" +
                "    <email>"+order.getUser().getEmail()+"</email>\n" +
                "    <authCode>code</authCode>\n" +
                "    <money>"+order.getTotal()+"</money>\n" +
                "</hs:getMoneyFromSalary>\n" +
                "\n" +
                "</soapenv:Body>\n" +
                "</soapenv:Envelope>";

        try {
            String targetNameSpace = "http://paymentsystem.soap/";
            QName serviceName = new QName(targetNameSpace, "SalaryPaymentService");
            QName portName = new QName(targetNameSpace, "SalaryPaymentPort");
            String endpointUrl = "http://localhost:8080/salarySystem/SalaryPayment";
            String SOAPAction = "";

            SOAPMessage response = invoke(serviceName, portName, endpointUrl, SOAPAction, request);
            SOAPBody body = response.getSOAPBody();
            if (body.hasFault()) {
                System.out.println(body.getFault());
            } else {
                BufferedOutputStream out = new BufferedOutputStream(System.err);
                response.writeTo(out);
                out.flush();
                System.err.println();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private SOAPMessage invoke(QName serviceName, QName portName, String endpointUrl,
                                     String soapActionUri, String data) throws Exception {
        Service service = Service.create(serviceName);
        service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointUrl);

        Dispatch dispatch = service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);

        // The soapActionUri is set here. otherwise we get a error on .net based services.
        dispatch.getRequestContext().put(Dispatch.SOAPACTION_USE_PROPERTY, true);
        dispatch.getRequestContext().put(Dispatch.SOAPACTION_URI_PROPERTY, soapActionUri);

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage message = messageFactory.createMessage();

        SOAPPart soapPart = message.getSOAPPart();
        SOAPEnvelope envelope = soapPart.getEnvelope();
        SOAPBody body = envelope.getBody();

        StreamSource preppedMsgSrc = new StreamSource(new StringReader(data));
        soapPart.setContent(preppedMsgSrc);

        message.saveChanges();

        System.out.println(message.getSOAPBody().getFirstChild().getTextContent());
        SOAPMessage response = (SOAPMessage) dispatch.invoke(message);

        return response;
    }
}
