package org.example.repository.concrete;

import org.example.database.dataTypes.LoggedFood;
import org.example.repository.NameableRepository;

import java.util.ArrayList;

public final class LoggedFoodRepository implements NameableRepository {

    private final ArrayList<LoggedFood> foods;

    public LoggedFoodRepository(ArrayList<LoggedFood> loggedFoods) {
        foods = loggedFoods;
    }

    public ArrayList<LoggedFood> getLoggedFoods() {
        return foods;
    }

    public void add(LoggedFood food) {
        foods.add(food);
    }

    @Override public String getName() {
        return "logged food";
    }
}
