package catalog;

import common.IngredientService;
import model.Ingredient;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkolbusz on 6/28/16.
 */
@ManagedBean
@ViewScoped
public class IngredientController {
    Ingredient ingredient;

    List<Ingredient> ingredientList = new ArrayList<>();


    @EJB(lookup = "java:jboss/exported/catalog/IngredientServiceImplementation!common.IngredientService")
    IngredientService ingredientService;

    @PostConstruct
    public void init(){
        ingredient = new Ingredient();
        ingredientList = ingredientService.getAll();
    }



    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientService.getAll();
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void save(){
        ingredientService.save(ingredient);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Składnik dodany", ingredient.getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        init();
    }
}
