import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;

/**
 * Created by mkolbusz on 6/28/16.
 */
//@Startup
//@Singleton
@Lock(LockType.READ)
public class InventoryChecker {

    @Resource
    private TimerService timerService;

    @PostConstruct
    private void init(){
        final TimerConfig checkInventory = new TimerConfig("checkInventory", false);
        timerService.createCalendarTimer(new ScheduleExpression().second("*/10").minute("*").hour("*"), checkInventory);
    }

    @Timeout
    public void timeout(Timer timer){
        if("checkInventory".equals(timer.getInfo())){
            checkInventory();
        }
    }

    private void checkInventory(){
        System.out.println("Checking inventory...");
    }
}
