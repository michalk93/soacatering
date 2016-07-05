package catalog;

import common.CategoryService;
import model.Category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class CategoryController {
    private Category category;

    private List<Category> categoryList = new ArrayList<>();

    @EJB(lookup = "java:jboss/exported/catalog/CategoryServiceImplementation!common.CategoryService")
    CategoryService categoryService;

    @PostConstruct
    void init(){
        category = new Category();
        categoryList = categoryService.getAll();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }


    public List<Category> getCategories(){
        return categoryService.getAll();
    }

    public void saveNewCategory(){
        categoryService.save(category);
    }

    public void removeCategory(Category category) {
        categoryList.remove(category);
        categoryService.remove(category);
    }

}