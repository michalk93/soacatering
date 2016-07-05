package common;

import model.Subscription;
import model.User;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by mkolbusz on 6/29/16.
 */
@Remote
public interface SubscriptionService {
    boolean save(Subscription subscription);
    List<Subscription> getAll();
    List<Subscription> getBelongToUser(User user);
    boolean update(Subscription subscription);

    boolean remove(Subscription subscription);
}
