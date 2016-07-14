package rest.exlusion;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import model.Course;
import model.OrderItem;
import model.OrderStatus;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mkolbusz on 7/15/16.
 */
public class OrderExclusion implements ExclusionStrategy{
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        if (fieldAttributes.getDeclaringClass() == User.class && !fieldAttributes.getName().equalsIgnoreCase("id")){
            return true;
        }

        List<String> exclusion = new ArrayList<>();
        Collections.addAll(exclusion, "order");
        if (fieldAttributes.getDeclaringClass() == OrderItem.class &&
                exclusion.stream().filter(e -> e.equalsIgnoreCase(fieldAttributes.getName())).findFirst().isPresent()){
            return true;
        }

        exclusion = new ArrayList<>();
        Collections.addAll(exclusion, "category", "courseIngredients");
        if (fieldAttributes.getDeclaringClass() == Course.class &&
                exclusion.stream().filter(e -> e.equalsIgnoreCase(fieldAttributes.getName())).findFirst().isPresent()){
            return true;
        }

        if (fieldAttributes.getDeclaringClass() == OrderStatus.class && fieldAttributes.getName().equalsIgnoreCase("orders")){
            return true;
        }

        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
