package org.example.database.dataTypes;

import org.example.common.DailyMealTime;

import java.time.LocalDate;

public record LoggedFood(LocalDate date,
                         DailyMealTime mealTime, String foodName,
                         double amount, double calories,
                         double carbs, double fat,
                         double protein) {

    public String toString() {
        return amount + "units X " + foodName
                + " (Total:" + amount + "units; " + calories
                + "kcal, " + carbs + "units, " + fat + "units, " + protein
                + "units)";
    }
}
