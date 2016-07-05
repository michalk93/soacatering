package model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by mkolbusz on 6/5/16.
 */
@Entity
@Table(name = "categories")
@XmlRootElement
public class Category implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @Column(name = "category_id")
    @GeneratedValue
    int id;

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    Set<Course> courses = new HashSet<>();

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.getName();
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object obj) {
        Category category = (Category) obj;
        if(category.getId() == this.getId()){
            return true;
        }
        if(!category.getName().equalsIgnoreCase(this.getName())){
            return false;
        }
        return true;
    }
}
