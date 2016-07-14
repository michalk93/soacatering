package rest.exlusion;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import model.Course;
import model.CourseIngredient;

/**
 * Created by mkolbusz on 6/29/16.
 */
public class CategoryWithCourses implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        if(fieldAttributes.getDeclaringClass() == Course.class && fieldAttributes.getName().equalsIgnoreCase("category")){
            return true;
        }

        if(fieldAttributes.getDeclaringClass() == CourseIngredient.class && fieldAttributes.getName().equalsIgnoreCase("course")){
            return true;
        }

        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
