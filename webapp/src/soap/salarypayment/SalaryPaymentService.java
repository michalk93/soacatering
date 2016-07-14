package soap.salarypayment;

import db.HibernateUtil;
import model.Company;
import model.User;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by mkolbusz on 7/8/16.
 */
@Stateless
@WebService
public class SalaryPaymentService {

    @WebMethod
    public User checkPaymentPermission(@WebParam(name = "authCode") String authCode, @WebParam(name = "email") String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Company company = (Company) session.createCriteria(Company.class).add(Restrictions.eq("authCode", authCode)).uniqueResult();

        if(company == null){
            return null;
        }

        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();

        if(user == null){
            return null;
        }

        if(!user.getCompany().getAuthCode().equals(authCode)){
            return null;
        }

        return user;
    }
}
