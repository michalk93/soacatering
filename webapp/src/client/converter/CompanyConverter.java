package client.converter;

import db.HibernateUtil;
import model.Company;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;

/**
 * Created by mkolbusz on 6/7/16.
 */
@FacesConverter(forClass = Company.class, value = "companyConverter")
public class CompanyConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.err.println("Company from id: " + s);
        if(s.isEmpty()){
            System.err.println("Null Error while converting Company to object");
            return null;
        }
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Company.class);
            return criteria.add(Restrictions.eq("companyId", Integer.valueOf(s))).uniqueResult();
        }catch(Exception e) {
            return new Company();
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o == null || !(o instanceof Company)){
            System.err.println("Error while converting Subscription object to String");
            return "";
        }
        Company company = (Company)o;
        return String.valueOf(company.getCompanyId());
    }
}
