package catalog.converter;

import db.HibernateUtil;
import model.Category;
import model.Course;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.io.Serializable;

/**
 * Created by mkolbusz on 6/7/16.
 */
@FacesConverter(forClass = Course.class, value = "courseConverter")
public class CourseConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.err.println("Course convert from id: " + s);
        if(s.isEmpty()){
            System.err.println("Null Error while converting Ingredient to object");
            return null;
        }
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Course.class);
            Course course = (Course) criteria.add(Restrictions.eq("courseId", Integer.valueOf(s))).uniqueResult();
            return course;
        }catch(Exception e) {
            return new Category();
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o == null || !(o instanceof Course)){
            System.err.println("Error while converting Course object to String");
            return "";
        }
        Course course = (Course)o;
        return String.valueOf(course.getCourseId());
    }
}
