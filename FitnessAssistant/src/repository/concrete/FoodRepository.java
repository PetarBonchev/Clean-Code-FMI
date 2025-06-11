package org.example.repository.concrete;

import org.example.database.dataTypes.Food;
import org.example.repository.NameableRepository;

import java.util.ArrayList;

public final class FoodRepository implements NameableRepository {

    private final ArrayList<Food> foods;

    public FoodRepository(ArrayList<Food> foodTypes) {
        foods = foodTypes;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public Food getByName(String name) {
        for (Food food : foods) {
            if (food.name().equals(name)) {
                return food;
            }
        }
        throw new RuntimeException("Food not found.");
    }

    @Override public String getName() {
        return "food";
    }
}
