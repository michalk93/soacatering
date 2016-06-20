import common.CourseService;
import db.HibernateUtil;
import model.Course;
import org.hibernate.classic.Session;

import javax.ejb.Stateless;

/**
 * Created by mkolbusz on 6/8/16.
 */
@Stateless
public class CourseServiceImplementation implements CourseService {
    @Override
    public boolean addNewCourse(Course course) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.save(course);
//        session.getTransaction().commit();
//        session.close();
        return true;
    }
}
