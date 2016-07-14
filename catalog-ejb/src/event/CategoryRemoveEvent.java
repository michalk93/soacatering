package event;

import model.Category;

/**
 * Created by mkolbusz on 7/14/16.
 */
public class CategoryRemoveEvent {
    private Category category;

    public CategoryRemoveEvent(Category category){
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }
}
