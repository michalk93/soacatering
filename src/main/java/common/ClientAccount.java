package common;

import exceptions.UserSessionExistsException;
import model.Company;
import model.User;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by mkolbusz on 6/5/16.
 */
@Remote
public interface ClientAccount {
    boolean register(User user);
    User login(User user) throws UserSessionExistsException;
    boolean logout(User user);
    boolean changePassword(String newPassword, User user);
    boolean registerCompany(User user, String authCode);

    List getAllCompanies();

    List<User> getAllUsersWithCompany();
}
