package org.example.repository.concrete;

import org.example.database.dataTypes.Meal;
import org.example.repository.NameableRepository;

import java.util.ArrayList;

public final class RecipeRepository implements NameableRepository {

    private final ArrayList<Meal> recipes;

    public RecipeRepository(ArrayList<Meal> recordedRecipes) {
        recipes = recordedRecipes;
    }

    public ArrayList<Meal> getRecipes() {
        return recipes;
    }

    public void addRecipe(Meal recipe) {
        recipes.add(recipe);
    }

    @Override public String getName() {
        return "recipe";
    }
}
