package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.common.DailyMealTime;
import org.example.database.dataTypes.LoggedFood;
import org.example.database.dataTypes.LoggedWater;
import org.example.repository.concrete.LoggedFoodRepository;
import org.example.repository.concrete.LoggedWaterRepository;
import org.example.ui.CommunicationChannel;

import java.time.LocalDate;

public final class ViewAllLogged extends Command {

    private final LoggedWaterRepository water;
    private final LoggedFoodRepository foods;

    public ViewAllLogged(CommunicationChannel io,
                         LoggedWaterRepository loggedWaterRepository,
                         LoggedFoodRepository loggedFoodRepository) {
        super(io);
        water = loggedWaterRepository;
        foods = loggedFoodRepository;
    }

    @Override public String getName() {
        return "View all logged";
    }

    @Override public void execute() {
        getIO().writeLine("When (YYYY-MM-DD):");
        LocalDate date = LocalDate.parse(getIO().readLine());

        printLoggedFood(date, DailyMealTime.BREAKFAST);
        printLoggedFood(date, DailyMealTime.LUNCH);
        printLoggedFood(date, DailyMealTime.SNACKS);
        printLoggedFood(date, DailyMealTime.DINNER);

        printLoggedWater(date);
    }

    private void printLoggedFood(LocalDate date, DailyMealTime mealTime) {
        getIO().writeLine(mealTime.toString() + ":");
        boolean foundLog = false;
        for (LoggedFood loggedFood : foods.getLoggedFoods()) {
            if (loggedFood.date().equals(date) && loggedFood.mealTime()
                    .equals(mealTime)) {
                getIO().writeLine(loggedFood.toString());
                foundLog = true;
            }
        }
        if (!foundLog) {
            getIO().writeLine("---");
        }
        getIO().writeLine("");
    }

    private void printLoggedWater(LocalDate date) {
        double waterAmount = 0;
        for (LoggedWater loggedWater : water.getLoggedWaters()) {
            if (loggedWater.date().equals(date)) {
                waterAmount += loggedWater.amount();
            }
        }
        getIO().writeLine("Water: " + waterAmount / 1000 + "L");
    }
}
