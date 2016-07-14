package catalog.converter;

import db.HibernateUtil;
import model.Category;
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
@FacesConverter(forClass = Category.class, value = "categoryConverter")
public class CategoryConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s.isEmpty()){
            System.err.println("Null Error while converting Category to object");
            return null;
        }
        try {
            System.err.println("Convert to object: " + s);
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Category.class);
            Category category = (Category) criteria.add(Restrictions.eq("id", Integer.valueOf(s))).uniqueResult();
            return category;
        }catch(Exception e) {
            System.err.println("Error while converting Category to object");
            return new Category();
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o == null || !(o instanceof Category)){
            System.err.println("Error while converting Category object to String");
            return "";
        }
        System.err.println("Convert from object: " + o.toString());
        Category category = (Category)o;
        return String.valueOf(category.getId());
    }
}
