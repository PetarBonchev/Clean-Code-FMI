package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.common.DailyMealTime;
import org.example.database.dataTypes.Food;
import org.example.database.dataTypes.LoggedFood;
import org.example.database.dataTypes.Meal;
import org.example.database.dataTypes.MealPart;
import org.example.repository.concrete.FoodRepository;
import org.example.repository.concrete.LoggedFoodRepository;
import org.example.repository.concrete.MealRepository;
import org.example.ui.CommunicationChannel;

import java.time.LocalDate;

public final class LogMeal extends Command {

    private final MealRepository meals;
    private final LoggedFoodRepository loggedFoods;
    private final FoodRepository foods;

    public LogMeal(CommunicationChannel io, MealRepository mealRepository,
                   LoggedFoodRepository loggedFoodRepository,
                   FoodRepository foodRepository) {
        super(io);
        meals = mealRepository;
        loggedFoods = loggedFoodRepository;
        foods = foodRepository;
    }

    @Override public String getName() {
        return "Log meal";
    }

    @Override public void execute() {
        getIO().writeLine("When (YYYY-MM-DD)");
        LocalDate date = LocalDate.parse(getIO().readLine());
        getIO().writeLine("When (meal)");
        DailyMealTime mealTime =
                DailyMealTime.valueOf(getIO().readLine().toUpperCase());
        getIO().writeLine("Which meal (id):");
        ViewAllMeals viewAllMeals = new ViewAllMeals(getIO(), meals);
        viewAllMeals.execute();
        int index = Integer.parseInt(getIO().readLine()) - 1;
        getIO().writeLine("Number of servings:");
        double servings = Double.parseDouble(getIO().readLine());

        logFoods(date, mealTime, meals.getMeals().get(index), servings);
    }

    private void logFoods(LocalDate date, DailyMealTime mealTime, Meal meal,
                          double servings) {
        for (MealPart mealPart : meal.foods()) {
            LoggedFood loggedFood = constructLoggedFood(date, mealTime,
                    foods.getByName(mealPart.foodName()), servings);
            loggedFoods.add(loggedFood);
        }
    }

    private LoggedFood constructLoggedFood(LocalDate date,
                                           DailyMealTime mealTime,
                                           Food selectedFood,
                                           double servings) {
        return new LoggedFood(date, mealTime,
                selectedFood.name(), selectedFood.amount() * servings,
                selectedFood.calories() * servings,
                selectedFood.carbs() * servings,
                selectedFood.fat() * servings,
                selectedFood.protein() * servings);
    }
}
