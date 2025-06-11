package org.example.database.dataTypes;

import java.util.List;

public record Meal(String name, String description,
                   double weight, double calories, double carbs,
                   double fat, double protein, List<MealPart> foods) {

    public String toString() {
        return  name + " (" + weight + "g; " + calories + "kcal, " + carbs
                + "g, " + fat + "g, " + protein + "g)";
    }
}
