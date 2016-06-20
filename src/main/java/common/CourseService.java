package common;

import model.Course;

import javax.ejb.Remote;

/**
 * Created by mkolbusz on 6/8/16.
 */
@Remote
public interface CourseService {
    boolean addNewCourse(Course course);
}
