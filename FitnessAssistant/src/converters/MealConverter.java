package org.example.converters;

import org.example.database.TokenTable;
import org.example.database.dataTypes.Meal;
import org.example.database.dataTypes.MealPart;
import org.example.repository.concrete.MealRepository;

import java.util.ArrayList;
import java.util.Arrays;

public final class MealConverter {

    public MealRepository toMealRepository(TokenTable data) {
        ArrayList<Meal> meals = new ArrayList<>();
        data.getTokens().forEach(line -> {
            ArrayList<MealPart> mealParts = new ArrayList<>();
            for (int i = 7; i < line.size(); i += 2) {
                mealParts.add(new MealPart(line.get(i),
                        Double.parseDouble(line.get(i + 1))));
            }
            meals.add(new Meal(
                    line.get(0),
                    line.get(1),
                    Double.parseDouble(line.get(2)),
                    Double.parseDouble(line.get(3)),
                    Double.parseDouble(line.get(4)),
                    Double.parseDouble(line.get(5)),
                    Double.parseDouble(line.get(6)),
                    mealParts
            ));
        });
        return new MealRepository(meals);
    }

    public TokenTable toTokenTable(MealRepository data) {
        TokenTable tokenTable = new TokenTable();
        data.getMeals().forEach(meal -> {
            ArrayList<String> tokens = new ArrayList<>(Arrays.asList(
                    meal.name(),
                    meal.description(),
                    Double.toString(meal.weight()),
                    Double.toString(meal.calories()),
                    Double.toString(meal.carbs()),
                    Double.toString(meal.fat()),
                    Double.toString(meal.protein())
            ));
            meal.foods().forEach(part -> {
                tokens.add(part.foodName());
                tokens.add(Double.toString(part.amount()));
            });
            tokenTable.addTokens(tokens);
        });
        return tokenTable;
    }
}
