import common.CategoryService;
import db.HibernateUtil;
import event.CategoryRemoveEvent;
import model.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by mkolbusz on 6/5/16.
 */
@Stateless
public class CategoryServiceImplementation implements CategoryService {

    @Inject
    Event<CategoryRemoveEvent> events;

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
        session.beginTransaction();
        session.saveOrUpdate(category);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return true;
    }

    @Override
    public boolean remove(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(category);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        events.fire(new CategoryRemoveEvent(category));
        return true;
    }
}
