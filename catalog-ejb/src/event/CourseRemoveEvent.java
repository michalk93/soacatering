package event;

import model.Course;

/**
 * Created by mkolbusz on 7/14/16.
 */
public class CourseRemoveEvent {
    private Course course;

    public CourseRemoveEvent(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
}
