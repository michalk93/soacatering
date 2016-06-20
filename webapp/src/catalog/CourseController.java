package catalog;

import common.CourseService;
import model.Course;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

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
        courseService.addNewCourse(course);
    }
}
