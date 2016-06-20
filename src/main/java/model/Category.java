package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by mkolbusz on 6/5/16.
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @Column(name = "category_id")
    @GeneratedValue
    int id;

    @Column(name = "name")
    String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "parent_category_id")
    Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    Set<Category> children = new LinkedHashSet<Category>();

    @OneToMany(mappedBy = "category_id")
    Set<Course> courses = new LinkedHashSet<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        String result = name;
        if(parent != null){
            result += ",{ parent: " + this.parent.getId() + " - " + this.parent.getName() + "}";
        }
        if(children != null) {
            result += ", children: " + this.children.size();
        }
        return result;
    }
}
