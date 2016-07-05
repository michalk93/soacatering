import common.CourseService;
import db.HibernateUtil;
import model.Category;
import model.Course;
import org.hibernate.Session;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by mkolbusz on 6/8/16.
 */
@Stateless
public class CourseServiceImplementation implements CourseService {


    @Override
    public List<Course> getCoursesFromCategory(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Course> courses = session.createQuery("from Course as course where course.category.id = " + category.getId()).list();
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return courses;
    }

    @Override
    public boolean removeCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(course);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return true;
    }


    @Override
    public List<Course> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> courses = session.createCriteria(Course.class).list();
        HibernateUtil.shutdown();
        return courses;
    }

    @Override
    public boolean save(Course course) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(course);
            session.getTransaction().commit();
            HibernateUtil.shutdown();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
