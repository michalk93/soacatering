package account;

import common.ClientAccount;
import db.HibernateUtil;
import model.Client;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;

/**
 * Created by mkolbusz on 6/5/16.
 */
@Stateless(mappedName = "clientAccount")
public class ClientAccountImplementation implements ClientAccount {

    @Override
    public boolean register(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean login(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Client.class);
        Client c = (Client)criteria.add(Restrictions.eq("email", client.getEmail())).uniqueResult();

        System.out.println(c.getPassword() + " <> " + client.getPassword());
        if(!c.getPassword().equals(client.getPassword())){
            return false;
        }

        return true;
    }
}
