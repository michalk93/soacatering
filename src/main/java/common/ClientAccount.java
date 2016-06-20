package common;

import model.Client;

import javax.ejb.Local;
import javax.ejb.Remote;

/**
 * Created by mkolbusz on 6/5/16.
 */
@Remote
public interface ClientAccount {
    boolean register(Client client);
    boolean login(Client client);
}
