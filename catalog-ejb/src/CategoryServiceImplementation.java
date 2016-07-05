import common.CategoryService;
import db.HibernateUtil;
import model.Category;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mkolbusz on 6/5/16.
 */
@Stateless
public class CategoryServiceImplementation implements CategoryService {

    public Category getCategoryById(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Category.class);
        Category category = (Category) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        HibernateUtil.shutdown();
        return category;
    }

    @Override
    public List getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Category> categories = session.createCriteria(Category.class).list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return categories;
    }


    @Override
    public boolean save(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.save(category);
        HibernateUtil.shutdown();
        return true;
    }

    @Override
    public boolean remove(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.delete(category);
        HibernateUtil.shutdown();
        return true;
    }
}
