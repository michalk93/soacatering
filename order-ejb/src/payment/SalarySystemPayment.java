package payment;

import model.Order;

import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.io.BufferedOutputStream;
import java.io.StringReader;

/**
 * Created by mkolbusz on 7/14/16.
 */
public class SalarySystemPayment implements OrderPaymentMethod {

    @Override
    public void handlePayment(Order order) {
        order.setPaymentMethod("POTRĄCENIE");
        payFromSalary(order);
    }

    public boolean payFromSalary(Order order) {
        String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:hs=\"http://paymentsystem.soap/\">\n" +
                "<soapenv:Header></soapenv:Header>\n" +
                "<soapenv:Body>\n" +
                "<hs:getMoneyFromSalary>\n" +
                "    <email>"+order.getUser().getEmail()+"</email>\n" +
                "    <orderId>"+ order.getOrderId() +"</orderId>" +
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

                if(body.getElementsByTagName("return").item(0).getTextContent().equals("true")){
                    order.setPaymentMethod("SYSTEM PRZYJĄŁ POTRĄCENIE");
                }else {
                    return false;
                }

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
