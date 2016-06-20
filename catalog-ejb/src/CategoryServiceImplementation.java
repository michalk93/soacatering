import common.CategoryService;
import db.HibernateUtil;
import model.Category;
import org.hibernate.Criteria;
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
        session.close();
        return category;
    }

    @Override
    public Collection getAllCategories() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Category> categories = session.createCriteria(Category.class).list();
        if(categories == null) {
            categories = new ArrayList<>();
        }
        session.close();
        return categories;
    }

    @Override
    public Collection getCategoriesWithoutParent() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Category.class);
        List<Category> categories = criteria.add(Restrictions.isNull("parent")).list();
        session.close();
        return categories;
    }

    @Override
    public Collection getCategoriesByParentId(Integer parentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria=session.createCriteria(Category.class);
        List<Category> categories = criteria.add(Restrictions.eq("parent.id", parentId)).list();
        System.err.println("getCategoriesByParentId: " + categories.size());
        session.close();
        return categories;
    }

    @Override
    public Collection getEndCategories() {
        System.out.println("METHOD: getEndCategories");
        //Session session = HibernateUtil.getSessionFactory().openSession();
//        List<Category> categories = session.createQuery("from Category as cat where cat.id not in (select cat2.parent.id from Category cat2)").list();
//        for(Category category : categories) {
//            System.out.println("CATEGORY: " + category.getName());
//        }
        return new ArrayList<>();
    }

    @Override
    public boolean save(Category category, Integer parentId) {
        System.out.println("CATEGORY: " + category.getName());
        if(parentId != null){
            category.setParent(getCategoryById(parentId));
        }else {
            category.setParent(null);
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(category);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
