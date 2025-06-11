package org.example.database.dataTypes;

import org.example.common.Metric;

public record Food(String name, String description,
                   double amount, Metric metric,
                   double servingsPerContainer,
                   double calories, double carbs,
                   double fat, double protein) {

    public String toString() {
        return name + " (" + amount + metric.toString().toLowerCase() + "; "
                + calories + "kcal, " + carbs
                + " units, " + fat + " units, " + protein + " units)";
    }
}
