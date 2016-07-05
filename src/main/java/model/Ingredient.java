package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by mkolbusz on 6/28/16.
 */
@Entity
@Table(name = "ingredients")
@XmlRootElement
public class Ingredient implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    int ingredientId;

    @Column(name = "name", unique = true)
    String name = "";

    @Column(name = "quantity")
    Double quantity;

    @Column(name = "alert_quantity")
    Double alertQantity;


    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getAlertQantity() {
        return alertQantity;
    }

    public void setAlertQantity(Double alertQantity) {
        this.alertQantity = alertQantity;
    }


    @Override
    public boolean equals(Object obj) {
        Ingredient ingredient = (Ingredient)obj;
        if(this.getName().equals(ingredient.getName())){
            return true;
        }
        return false;
    }
}
