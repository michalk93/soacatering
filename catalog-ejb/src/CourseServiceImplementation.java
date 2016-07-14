import common.CourseService;
import db.HibernateUtil;
import event.CourseRemoveEvent;
import model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.criterion.Order;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkolbusz on 6/8/16.
 */
@Stateless
public class CourseServiceImplementation implements CourseService {

    @Inject
    Event<CourseRemoveEvent> event;

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
    public boolean remove(Course course) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(course);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        event.fire(new CourseRemoveEvent(course));
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

    @Override
    public Course getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Course course = (Course) session.createCriteria(Course.class).add(Restrictions.eq("courseId", id)).uniqueResult();
        HibernateUtil.shutdown();
        return course;
    }

    @Override
    public List<Course> getTop10() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ProjectionList projectionList = Projections.projectionList().add(Projections.groupProperty("course")).add(Projections.alias(Projections.rowCount(),"count"));
        Criteria criteria = session.createCriteria(OrderItem.class).setProjection(projectionList);
        List<Course[]> results = criteria.addOrder(Order.desc("count")).list();
        HibernateUtil.shutdown();

        List<Course> resultCourses = new ArrayList<>();
        for(Object[] result : results) {
            resultCourses.add((Course)result[0]);
        }

        return resultCourses;
    }
}
