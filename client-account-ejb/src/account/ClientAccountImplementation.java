package account;

import common.ClientAccount;
import db.HibernateUtil;
import exceptions.UserSessionExistsException;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.security.auth.spi.Util;


import javax.ejb.Stateless;


/**
 * Created by mkolbusz on 6/5/16.
 */
@Stateless(mappedName = "clientAccount")
public class ClientAccountImplementation implements ClientAccount {

    @Override
    public boolean register(User user) {
        user.setPassword(getHashedPassword(user.getPassword()));
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return true;
    }

    private String getHashedPassword(String password) {
        return Util.createPasswordHash("SHA-256", "BASE64", null, null, password);
    }

    @Override
    public User login(User user) throws UserSessionExistsException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        user = (User)criteria.add(Restrictions.eq("email", user.getEmail())).uniqueResult();

        if(user == null || user.getIsLogged() == 1) {
            HibernateUtil.shutdown();
            throw new UserSessionExistsException();
        }

        user.setIsLogged(1);
        session.update(user);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return user;
    }

    @Override
    public boolean logout(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        user = (User)criteria.add(Restrictions.eq("email", user.getEmail())).uniqueResult();

        if(user != null && user.getIsLogged() == 1) {
            user.setIsLogged(0);
            session.update(user);
        }

        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return true;
    }





}
