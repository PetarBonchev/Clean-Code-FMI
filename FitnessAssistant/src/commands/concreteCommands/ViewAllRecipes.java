package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.database.dataTypes.Meal;
import org.example.repository.concrete.RecipeRepository;
import org.example.ui.CommunicationChannel;

public final class ViewAllRecipes extends Command {

    private final RecipeRepository recipes;

    public ViewAllRecipes(CommunicationChannel io,
                          RecipeRepository recipeRepository) {
        super(io);
        recipes = recipeRepository;
    }

    @Override public String getName() {
        return "View all recipes";
    }

    @Override public void execute() {
        int i = 1;
        for (Meal meal : recipes.getRecipes()) {
            getIO().writeLine(i + ". " + meal.toString());
            i++;
        }
    }
}
