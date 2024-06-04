package htw.berlin.wi.prog2.domain;

import java.math.BigDecimal;

abstract class AbstractIngredient implements Ingredient {

    public String name;
    public BigDecimal price;
    public int calories;

    public AbstractIngredient(String name, BigDecimal price, int calories) {
        this.name = name;
        this.price = price;
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
    public String toString() { return this.getName(); }


}
