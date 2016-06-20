package catalog;

import common.CategoryService;
import model.Category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean
@RequestScoped
public class CategoryController {
    private Category category;
    private Integer parentId;

    @EJB(lookup = "java:jboss/exported/catalog/CategoryServiceImplementation!common.CategoryService")
    CategoryService categoryService;

    @PostConstruct
    void init(){
        category = new Category();
        category.setParent(new Category());
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Category> getAllCategories(){
        return (List<Category>) categoryService.getAllCategories();
    }

    public List<Category> getCategoriesByParentId(){
        try {
            parentId = Integer.valueOf(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categoryId"));
        }catch(NumberFormatException e) {
            parentId = null;
        }

        if(parentId == null){
            List<Category> categories = (List<Category>) categoryService.getCategoriesWithoutParent();
            System.out.println(categories.size());
            return categories;
        }
        return (List<Category>) categoryService.getCategoriesByParentId(parentId);
    }

    public List<Category> getEndCategories(){
        return (List<Category>) categoryService.getEndCategories();
    }

    public void saveNewCategory(){
        categoryService.save(category, parentId);
    }

}