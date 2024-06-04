package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

abstract class AbstractIngredient implements Ingredient {

    public String name;
    public BigDecimal price;
    public int calories;

    public AbstractIngredient(String name, BigDecimal price, int calories) {
        this.name = name.trim();
        this.price = price.setScale(2, RoundingMode.HALF_UP);
        this.calories = calories;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public Category getCategory(){
        return null;
    }

    @Override
    public String toString() { return this.getName(); }

    //Comparison between two objects (name, price, calories)
    //because we are calling the ingredient interface, we need to instantiate the AbstractIngredient class
    //Price comparison: compareTo Method equals 0 means they are the same
    @Override
    public boolean equals(Ingredient ingredient) {
        AbstractIngredient abstractIngredient = (AbstractIngredient) ingredient;
        return abstractIngredient.price.compareTo(price) == 0 && this.getName().equals(ingredient.getName()) && this.getCalories() == abstractIngredient.getCalories();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, calories);
    }

}
