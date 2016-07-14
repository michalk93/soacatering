package account;

import common.ClientAccount;
import common.OrderService;
import common.SubscriptionService;
import model.Order;
import model.Subscription;
import model.User;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mkolbusz on 6/28/16.
 */
//@Startup
//@Singleton
//@Lock(LockType.READ)
public class SOAPSalaryPaymentChecker {

    @Resource
    private TimerService timerService;

    @EJB(lookup = "java:jboss/exported/order/OrderServiceImplementation!common.OrderService")
    OrderService orderService;

    @EJB(lookup = "java:jboss/exported/clientAccount/ClientAccountImplementation!common.ClientAccount")
    ClientAccount clientAccount;

    @PostConstruct
    private void init(){
        final TimerConfig salaryPaymentChecker = new TimerConfig("salaryPaymentChecker", false);
        timerService.createCalendarTimer(new ScheduleExpression().second("*/30").minute("*").hour("*"), salaryPaymentChecker);
    }

    @Timeout
    public void timeout(Timer timer){
        if("salaryPaymentChecker".equals(timer.getInfo())){
            salaryPaymentChecker();
        }
    }

    private void salaryPaymentChecker(){
       List<User> user = clientAccount.getAllUsersWithCompany();
        user.stream().forEach(u -> {
            try {
//                String endpointUrl = "http://localhost:9000/";
//                QName serviceName = new QName(endpointUrl, "SalaryPayment");
//                QName portName = new QName(endpointUrl, "SalaryPaymentPort");
//
//                Service service = Service.create(serviceName);
//                service.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, endpointUrl);
//
//                Dispatch<SOAPMessage> dispatch = service.createDispatch(portName,
//                        SOAPMessage.class, Service.Mode.MESSAGE);
//
//                MessageFactory mf = MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
//
//                SOAPMessage request = mf.createMessage();
//                SOAPPart part = request.getSOAPPart();
//
//
//                SOAPEnvelope env = part.getEnvelope();
//                SOAPHeader header = env.getHeader();
//                SOAPBody body = env.getBody();
//
//                SOAPElement operation = body.addChildElement("invoke", "ns1",
//                        "http://localhost:9000/");
//                SOAPElement value = operation.addChildElement("arg0");
//                value.addTextNode("ping");
//                request.toString();
//                request.saveChanges();
//
///** Invoke the service endpoint. **/
//                SOAPMessage response = dispatch.invoke(request);
            }catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}
