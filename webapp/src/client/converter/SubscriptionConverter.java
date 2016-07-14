package client.converter;

import db.HibernateUtil;
import model.Category;
import model.Course;
import model.Ingredient;
import model.Subscription;
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
@FacesConverter(forClass = Subscription.class, value = "subscriptionConverter")
public class SubscriptionConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.err.println("Course subscription from id: " + s);
        if(s.isEmpty()){
            System.err.println("Null Error while converting Subscription to object");
            return null;
        }
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Subscription.class);
            return criteria.add(Restrictions.eq("subscriptionId", Integer.valueOf(s))).uniqueResult();
        }catch(Exception e) {
            return new Subscription();
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o == null || !(o instanceof Subscription)){
            System.err.println("Error while converting Subscription object to String");
            return "";
        }
        Subscription subscription = (Subscription)o;
        return String.valueOf(subscription.getSubscriptionId());
    }
}
