package catalog;

import db.HibernateUtil;
import model.Category;
import model.Ingredient;
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
@FacesConverter(forClass = Ingredient.class, value = "ingredientConverter")
public class IngredientConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s.isEmpty()){
            System.err.println("Null Error while converting Ingredient to object");
            return null;
        }
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Ingredient.class);
            Ingredient ingredient = (Ingredient) criteria.add(Restrictions.eq("ingredientId", Integer.valueOf(s))).uniqueResult();
            return ingredient;
        }catch(Exception e) {
            return new Category();
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o == null || !(o instanceof Ingredient)){
            System.err.println("Error while converting Ingredient object to String");
            return "";
        }
        Ingredient ingredient = (Ingredient)o;
        return String.valueOf(ingredient.getIngredientId());
    }
}
