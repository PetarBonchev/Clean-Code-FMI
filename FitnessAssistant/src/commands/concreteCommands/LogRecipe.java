package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.common.DailyMealTime;
import org.example.database.dataTypes.LoggedFood;
import org.example.database.dataTypes.Meal;
import org.example.repository.concrete.LoggedFoodRepository;
import org.example.repository.concrete.RecipeRepository;
import org.example.ui.CommunicationChannel;

import java.time.LocalDate;

public final class LogRecipe extends Command {

    private final RecipeRepository recipes;
    private final LoggedFoodRepository loggedFoods;

    public LogRecipe(CommunicationChannel io,
                     RecipeRepository recipeRepository,
                     LoggedFoodRepository loggedFoodRepository) {
        super(io);
        recipes = recipeRepository;
        loggedFoods = loggedFoodRepository;
    }

    @Override public String getName() {
        return "Log recipe";
    }

    @Override public void execute() {
        getIO().writeLine("When (YYYY-MM-DD)");
        LocalDate date = LocalDate.parse(getIO().readLine());
        getIO().writeLine("When (meal)");
        DailyMealTime mealTime =
                DailyMealTime.valueOf(getIO().readLine().toUpperCase());
        getIO().writeLine("Which recipe (id):");
        ViewAllRecipes viewAllRecipes = new ViewAllRecipes(getIO(), recipes);
        viewAllRecipes.execute();
        int index = Integer.parseInt(getIO().readLine()) - 1;
        getIO().writeLine("Number of servings:");
        double servings = Double.parseDouble(getIO().readLine());

        logRecipe(date, mealTime, recipes.getRecipes().get(index), servings);
    }

    private void logRecipe(LocalDate date, DailyMealTime mealTime, Meal meal,
                           double servings) {
        LoggedFood loggedFood = new LoggedFood(date, mealTime, meal.name(),
                meal.weight() * servings, meal.calories() * servings,
                meal.carbs() * servings, meal.fat() * servings,
                meal.protein() * servings);

        loggedFoods.add(loggedFood);
    }
}
