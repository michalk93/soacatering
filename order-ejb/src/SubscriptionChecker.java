import common.OrderService;
import common.SubscriptionService;
import model.Order;
import model.Subscription;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by mkolbusz on 6/28/16.
 */
//@Startup
//@Singleton
@Lock(LockType.READ)
public class SubscriptionChecker {

    @Resource
    private TimerService timerService;

    @EJB(lookup = "java:jboss/exported/order/SubscriptionServiceImplementation!common.SubscriptionService")
    SubscriptionService subscriptionService;

    @EJB(lookup = "java:jboss/exported/order/OrderServiceImplementation!common.OrderService")
    OrderService orderService;

    @PostConstruct
    private void init(){
        final TimerConfig subscriptionChecker = new TimerConfig("subscriptionChecker", false);
        timerService.createCalendarTimer(new ScheduleExpression().second("*/30").minute("*").hour("*"), subscriptionChecker);
    }

    @Timeout
    public void timeout(Timer timer){
        if("subscriptionChecker".equals(timer.getInfo())){
            subscriptionChecker();
        }
    }

    private void subscriptionChecker(){
        List<Subscription> subscriptions = subscriptionService.getAll();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        subscriptions.stream().forEach(s -> {
            if(s.getWeekDay() == dayOfWeek){
                orderService.save(createOrder(s));
            }
        });
    }

    private Order createOrder(Subscription subscription){
        Order order = new Order();
        subscription.getSubscriptionItems().stream().forEach(i -> {
            order.addCourse(i.getCourse());
        });
        order.setUser(subscription.getUser());
        order.setCreatedAt(new Date());
        order.setShippingAddress(subscription.getShippingAddress());
        order.setShippingTime(subscription.getShippingTime());
        return order;
    }
}
