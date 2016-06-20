package model;


import javax.persistence.*;

/**
 * Created by mkolbusz on 6/8/16.
 */
@Entity
@Table(name = "courses", uniqueConstraints = {
        @UniqueConstraint(columnNames = "course_id")}
)
public class Course {

    @Id
    @GeneratedValue
    @Column(name = "course_id", unique = true, nullable = false)
    private int courseId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ingredients", nullable = false)
    private String ingredients;

    @Column(name = "category_id")
    @OneToOne(mappedBy = "id")
    private Category category;


    @Column(name = "price")
    private Double price;


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

//    public int getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(int categoryId) {
//        this.categoryId = categoryId;
//    }

        public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
