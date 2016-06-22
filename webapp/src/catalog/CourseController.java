package catalog;

import common.CourseService;
import model.Category;
import model.Course;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkolbusz on 6/8/16.
 */
@ManagedBean
public class CourseController {
    private Course course;

    @EJB(lookup = "java:jboss/exported/catalog/CourseServiceImplementation!common.CourseService")
    CourseService courseService;

    @PostConstruct
    void init(){
        course = new Course();
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void addNewCourse(){

        System.err.println("Course name: " + course.getName());
        System.err.println("Course ingredients: " + course.getIngredients());
        System.err.println("Course price: " + course.getPrice());

        System.err.println("Course category: " + course.getCategory().getName());

        courseService.addNewCourse(course);
    }

    public List<Course> getCursesFromCategory() {
        Integer categoryId;
        try {
            categoryId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categoryId"));
        }catch(NumberFormatException e) {
            categoryId = null;
        }

        if(categoryId == null) {
            return null;
        }

        Category category = new Category();
        category.setId(categoryId);
        List<Course> courses = courseService.getCoursesFromCategory(category);

        return courses;
    }
}
