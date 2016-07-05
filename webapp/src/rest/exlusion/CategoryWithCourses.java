package rest.exlusion;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import model.Course;

/**
 * Created by mkolbusz on 6/29/16.
 */
public class CategoryWithCourses implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return (fieldAttributes.getDeclaringClass() == Course.class && fieldAttributes.getName().equalsIgnoreCase("category"));
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
