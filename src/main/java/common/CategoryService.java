package common;

import model.Category;

import javax.ejb.Remote;
import java.util.Collection;
import java.util.List;

/**
 * Created by mkolbusz on 6/5/16.
 */
@Remote
public interface CategoryService {
    Category getCategoryById(int id);
    List getAll();

    boolean save(Category category);
    boolean remove(Category category);
}
