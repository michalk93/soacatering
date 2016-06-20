package common;

import model.Category;

import javax.ejb.Remote;
import java.util.Collection;

/**
 * Created by mkolbusz on 6/5/16.
 */
@Remote
public interface CategoryService {
    Category getCategoryById(int id);
    Collection getAllCategories();
    Collection getCategoriesWithoutParent();
    Collection getCategoriesByParentId(Integer parentId);
    Collection getEndCategories();

    boolean save(Category category, Integer parentId);
}
