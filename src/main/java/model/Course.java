package model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mkolbusz on 6/8/16.
 */
@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "course_id", unique = true, nullable = false)
    private int courseId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ingredients", nullable = false)
    private String ingredients;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
