package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by mkolbusz on 6/28/16.
 */
@Entity
@Table(name = "courses_ingredients")
@XmlRootElement
public class CourseIngredient implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "course_ingredient_id")
    int courseIngredientId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course = new Course();

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient = new Ingredient();

    @Column(name = "quantity")
    Double quantity;

    public CourseIngredient() {};

    public CourseIngredient(Course course, Ingredient ingredient, Double quantity){
        this.course = course;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public int getCourseIngredientId() {
        return courseIngredientId;
    }

    public void setCourseIngredientId(int courseIngredientId) {
        this.courseIngredientId = courseIngredientId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return this.getIngredient().getName();
    }
}
