package order;

import db.HibernateUtil;
import model.Category;
import model.Course;
import model.OrderStatus;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;

@FacesConverter(forClass = OrderStatus.class, value = "orderStatusConverter")
public class OrderStatusConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.err.println("OrderStatus convert from id: " + s);
        if(s.isEmpty()){
            System.err.println("Null Error while converting OrderStatus to object");
            return null;
        }
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(OrderStatus.class);
            OrderStatus status = (OrderStatus) criteria.add(Restrictions.eq("statusID", Integer.valueOf(s))).uniqueResult();
            return status;
        }catch(Exception e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid status"));
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o == null || !(o instanceof OrderStatus)){
            System.err.println("Error while converting OrderStatus object to String");
            return null;
        }
        OrderStatus status = (OrderStatus) o;
        return String.valueOf(status.getStatusID());
    }
}
