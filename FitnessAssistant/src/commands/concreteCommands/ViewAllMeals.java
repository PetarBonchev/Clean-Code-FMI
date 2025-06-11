package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.database.dataTypes.Meal;
import org.example.repository.concrete.MealRepository;
import org.example.ui.CommunicationChannel;

public final class ViewAllMeals extends Command {

    private final MealRepository meals;

    public ViewAllMeals(CommunicationChannel io,
                        MealRepository mealRepository) {
        super(io);
        meals = mealRepository;
    }

    @Override public String getName() {
        return "View all meals";
    }

    @Override public void execute() {
        int i = 1;
        for (Meal meal : meals.getMeals()) {
            getIO().writeLine(i + ". " + meal.toString());
            i++;
        }
    }
}
