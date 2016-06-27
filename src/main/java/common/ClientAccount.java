package common;

import exceptions.UserSessionExistsException;
import model.User;

import javax.ejb.Remote;

/**
 * Created by mkolbusz on 6/5/16.
 */
@Remote
public interface ClientAccount {
    boolean register(User user);
    User login(User user) throws UserSessionExistsException;
    boolean logout(User user);
}
