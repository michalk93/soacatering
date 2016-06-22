package common;

import model.Category;
import model.Course;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by mkolbusz on 6/8/16.
 */
@Remote
public interface CourseService {
    boolean addNewCourse(Course course);
    List<Course> getCoursesFromCategory(Category category);
}
