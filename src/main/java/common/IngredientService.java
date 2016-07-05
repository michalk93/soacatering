package common;

import model.Ingredient;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by mkolbusz on 6/28/16.
 */
@Remote
public interface IngredientService {
    boolean save(Ingredient ingredient);
    List<Ingredient> getAll();
}
