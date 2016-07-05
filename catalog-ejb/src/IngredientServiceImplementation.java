import common.IngredientService;
import db.HibernateUtil;
import model.Ingredient;
import org.hibernate.Session;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by mkolbusz on 6/28/16.
 */
@Stateless
public class IngredientServiceImplementation implements IngredientService {


    @Override
    public boolean save(Ingredient ingredient) {
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(ingredient);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return true;
    }

    @Override
    public List<Ingredient> getAll() {
        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        List<Ingredient> ingredients = session.createCriteria(Ingredient.class).list();
        HibernateUtil.shutdown();
        return ingredients;
    }
}
