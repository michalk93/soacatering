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
        System.out.println("Adding new course");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(course);
        session.getTransaction().commit();
        session.close();
        System.out.println("New course was added");
        return true;
    }

    @Override
    public List<Course> getCoursesFromCategory(Category category) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Course> courses = session.createQuery("from Course as course where course.category.id = " + category.getId()).list();
        courses.stream().forEach(c -> {
            System.out.println("Course: " + c.getName() + ", " + c.getPrice());
        });
        HibernateUtil.shutdown();
        return courses;
    }
}
