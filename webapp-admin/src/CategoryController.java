import common.CategoryService;
import model.Category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class CategoryController {
    private Category category;
    private int parentId;

    @EJB(lookup = "java:jboss/exported/catalog-ejb/CategoryServiceImplementation!common.CategoryService")
    CategoryService categoryService;

    @PostConstruct
    void init(){
        category = new Category();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<Category> getAllCategories(){
        return (List<Category>) categoryService.getAllCategories();
    }

    public void saveNewCategory(){
        categoryService.save(category, parentId);
    }
}