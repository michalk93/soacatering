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
    public boolean addNewCourse(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(course);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<Course> getCoursesFromCategory(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> courses = session.createQuery("from Course as course where course.category.id = " + category.getId()).list();
        HibernateUtil.shutdown();
        return courses;
    }
}
