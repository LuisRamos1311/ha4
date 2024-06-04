package htw.berlin.wi.prog2.domain;

import htw.berlin.wi.prog2.data.Menu; // TODO Diesen Import sollten Sie nach Teilaufgabe 4 entfernen können

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BurgerBuilder {

    private List<Ingredient> ingredients = new ArrayList<>();

    public enum CreationStyle {
        PRECOMPUTED,
        DYNAMICALLY_COMPUTED
    }

    private CreationStyle creationStyle;

    public void setCreationStyle(CreationStyle creationStyle) {
        this.creationStyle = creationStyle;
    }

    public BurgerBuilder add(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public Burger build() {
        checkRules();
        return creationStyle == CreationStyle.PRECOMPUTED ? buildPrecomputed() : buildDynamicallyComputed();
    }

    private void checkRules() {
        if(ingredients.size() < 2) throw new IllegalBurgerException("Nicht genügend Zutaten");

        // TODO hier mit ingredient.getCategory() statt mit Menu.bases und Menu.sauces arbeiten
        //Stream operations: filtering after every ingredient with category BASE, then we turn the result into a list and check if bigger than 1
        if(ingredients.stream().filter(ingredient -> ingredient.getCategory() == Ingredient.Category.BASE).toList().size() > 1)
            throw new IllegalBurgerException("Zwei mal Brot-Basis in einem Burger geht nicht");
        //Stream operations: filtering after every ingredient with category SAUCE, then we turn the result into a list and check if empty
        if(ingredients.stream().filter(ingredient -> ingredient.getCategory() == Ingredient.Category.SAUCE).toList().isEmpty())
            throw new IllegalBurgerException("Ein Burger braucht mindestens eine Sauce");
    }

    private Burger buildPrecomputed() {
        BigDecimal price = BigDecimal.ZERO;
        int calories = 0;
        List<String> ingredientNames = new ArrayList<>();

        for (Ingredient ing : ingredients) {
            price = price.add(ing.getPrice());
            calories += ing.getCalories();
            ingredientNames.add(ing.getName());
        }
        ingredients.clear();

        return new PrecomputedBurger(price, calories, ingredientNames);
    }

    private Burger buildDynamicallyComputed() {
        List<Ingredient> ingsToPass = List.copyOf(ingredients);
        ingredients.clear();
        return new DynamicallyComputedBurger(ingsToPass);
    }

    public BurgerBuilder(CreationStyle creationStyle) {
        this.creationStyle = creationStyle;
    }

    public BurgerBuilder() {
        this(CreationStyle.PRECOMPUTED);
    }
}
