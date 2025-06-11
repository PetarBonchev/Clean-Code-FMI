package org.example.repository.concrete;

import org.example.database.dataTypes.Meal;
import org.example.repository.NameableRepository;

import java.util.ArrayList;

public final class MealRepository implements NameableRepository {

    private final ArrayList<Meal> meals;

    public MealRepository(ArrayList<Meal> recordedMeals) {
        meals = recordedMeals;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

    @Override public String getName() {
        return "meal";
    }
}
