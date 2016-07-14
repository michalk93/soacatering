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
    List<Course> getCoursesFromCategory(Category category);
    boolean remove(Course course);
    List<Course> getAll();
    boolean save(Course course);

    Course getById(Integer id);

    List<Course> getTop10();
}
