package account;

import common.CompanyService;
import db.HibernateUtil;
import model.Company;
import org.hibernate.Session;

import javax.ejb.Stateless;
import java.util.UUID;

/**
 * Created by mkolbusz on 7/7/16.
 */

@Stateless(mappedName = "companyService", name = "companyService")
public class CompanyServiceImplementation implements CompanyService {
    @Override
    public boolean save(Company company) {
        company.setAuthCode(UUID.randomUUID().toString());
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(company);
            session.getTransaction().commit();
            HibernateUtil.shutdown();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
