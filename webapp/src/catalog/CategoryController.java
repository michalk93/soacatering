package catalog;

import common.CategoryService;
import model.Category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PostPersist;
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

    public void save(){
        categoryService.save(category);

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Kategoria zaktualizowana", "Nowa nazwa: " + category.getName());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void remove(Category category) {
        categoryList.remove(category);
        categoryService.remove(category);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Kategoria usunięta", "Nazwa: " + category.getName());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}