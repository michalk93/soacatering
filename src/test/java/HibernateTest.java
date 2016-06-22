import db.HibernateUtil;
import model.Category;
import model.Course;
import org.hibernate.Session;

/**
 * Created by mkolbusz on 6/20/16.
 */
public class HibernateTest {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Category category = (Category)session.load(Category.class, 1);

        Course course = new Course();
        course.setName("Zupa grzybowa");
        course.setIngredients("ziemniaki, grzyby: borowiki, kurki");
        course.setPrice(14.99);
        course.setCategory(category);

        session.save(course);
        session.getTransaction().commit();

        course = (Course)session.load(Course.class, 1);
        System.out.println(course.getName() + " - " + course.getCategory().getName());

        session.close();

    }
}
