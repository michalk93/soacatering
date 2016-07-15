package account;

import common.ClientAccount;
import db.HibernateUtil;
import exceptions.UserSessionExistsException;
import model.Company;
import model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jboss.security.auth.spi.Util;


import javax.ejb.Stateless;
import java.util.List;


/**
 * Created by mkolbusz on 6/5/16.
 */
@Stateless(mappedName = "orderService")
public class ClientAccountImplementation implements ClientAccount {
    @Override
    public boolean register(User user) {
        user.setPassword(getHashedPassword(user.getPassword()));
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.createSQLQuery("INSERT INTO roles VALUES (null, 'USER', '"+user.getEmail()+"')").executeUpdate();
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

    @Override
    public boolean changePassword(String newPassword, User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        user = (User)criteria.add(Restrictions.eq("email", user.getEmail())).uniqueResult();

        boolean result;
        if(user != null) {
            user.setPassword(getHashedPassword(newPassword));
            session.update(user);
            result = true;
        }else {
            result = false;
        }

        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return result;
    }

    @Override
    public boolean registerCompany(User user, String authCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Company.class);
        Company company = (Company)criteria.add(Restrictions.eq("companyId", user.getCompany().getCompanyId())).uniqueResult();

        boolean result;
        if(company != null) {
            if(company.getAuthCode().equals(authCode)){
                session.update(user);
                result = true;
            }else {
                result = false;
            }
        }else {
            result = false;
        }

        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return result;
    }

    @Override
    public List getAllCompanies() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List companies = session.createCriteria(Company.class).list();
        HibernateUtil.shutdown();
        return companies;
    }

    @Override
    public List<User> getAllUsersWithCompany() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List users = session.createCriteria(User.class).add(Restrictions.isNotNull("company")).list();
        HibernateUtil.shutdown();
        return users;
    }


}
