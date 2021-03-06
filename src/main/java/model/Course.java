package model;


import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mkolbusz on 6/8/16.
 */
@Entity
@Table(name = "courses")
@XmlRootElement
public class Course implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "course_id", unique = true, nullable = false)
    int courseId;


    @Column(name = "name", nullable = false)
    String name = "";

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<CourseIngredient> courseIngredients = new HashSet<>();

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @Column(name = "image")
    String image;


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

    public Set<CourseIngredient> getCourseIngredients() {
        return courseIngredients;
    }

    public void setCourseIngredients(Set<CourseIngredient> courseIngredients) {
        this.courseIngredients = courseIngredients;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "[" + this.getCourseId() + "]" + this.getName() + " " + this.getPrice();
    }

    @Override
    public boolean equals(Object obj) {
        return ((Course)obj).getCourseId() == this.getCourseId();
    }

    @Override
    public int hashCode() {
        return this.getCourseId();
    }
}
