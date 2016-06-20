package catalog;

import db.HibernateUtil;
import model.Category;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by mkolbusz on 6/7/16.
 */
@FacesConverter(forClass = Category.class, value = "category.idConverter")
public class CategoryConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Category.class);
        Category category = (Category)criteria.add(Restrictions.eq("category_id", Integer.valueOf(s))).uniqueResult();
        return category;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Category category = (Category)o;
        return String.valueOf(category.getId());
    }
}
