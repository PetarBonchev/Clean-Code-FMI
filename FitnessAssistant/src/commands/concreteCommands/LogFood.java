package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.common.DailyMealTime;
import org.example.database.dataTypes.Food;
import org.example.database.dataTypes.LoggedFood;
import org.example.repository.concrete.FoodRepository;
import org.example.repository.concrete.LoggedFoodRepository;
import org.example.ui.CommunicationChannel;

import java.time.LocalDate;

public final class LogFood extends Command {

    private final FoodRepository foods;
    private final LoggedFoodRepository loggedFoods;

    public LogFood(CommunicationChannel io, FoodRepository foodRepository,
                   LoggedFoodRepository loggedFoodRepository) {
        super(io);
        foods = foodRepository;
        loggedFoods = loggedFoodRepository;
    }

    @Override public String getName() {
        return "Log food";
    }

    @Override public void execute() {
        getIO().writeLine("When (YYYY-MM-DD):");
        LocalDate date = LocalDate.parse(getIO().readLine());
        getIO().writeLine("When (meal):");
        DailyMealTime mealTime =
                DailyMealTime.valueOf(getIO().readLine().toUpperCase());
        getIO().writeLine("Which food (food id):");
        ViewAllFoods viewAllFoods = new ViewAllFoods(getIO(), foods);
        viewAllFoods.execute();
        int foodIndex = Integer.parseInt(getIO().readLine()) - 1;
        getIO().writeLine("Serving size:");
        double servingSize = Double.parseDouble(getIO().readLine());


        LoggedFood loggedFood = constructLoggedFood(date, mealTime,
                foods.getFoods().get(foodIndex), servingSize);
        loggedFoods.add(loggedFood);
    }

    private LoggedFood constructLoggedFood(LocalDate date,
                                           DailyMealTime mealTime,
                                           Food selectedFood,
                                           double servingSize) {
        double proportion = servingSize / selectedFood.amount();
        return new LoggedFood(date, mealTime,
                selectedFood.name(), selectedFood.amount() * proportion,
                selectedFood.calories() * proportion,
                selectedFood.carbs() * proportion,
                selectedFood.fat() * proportion,
                selectedFood.protein() * proportion);
    }
}
