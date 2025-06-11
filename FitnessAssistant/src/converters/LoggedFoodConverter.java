package org.example.converters;

import org.example.common.DailyMealTime;
import org.example.database.TokenTable;
import org.example.database.dataTypes.LoggedFood;
import org.example.repository.concrete.LoggedFoodRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public final class LoggedFoodConverter {

    public LoggedFoodRepository toLoggedFood(TokenTable data) {
        ArrayList<LoggedFood> foods = new ArrayList<>();
        data.getTokens().forEach(line -> foods.add(
                new LoggedFood(
                        LocalDate.parse(line.get(0)),
                        DailyMealTime.valueOf(line.get(1).toUpperCase()),
                        line.get(2),
                        Double.parseDouble(line.get(3)),
                        Double.parseDouble(line.get(4)),
                        Double.parseDouble(line.get(5)),
                        Double.parseDouble(line.get(6)),
                        Double.parseDouble(line.get(7))
                )));
        return new LoggedFoodRepository(foods);
    }

    public TokenTable toTokenTable(LoggedFoodRepository data) {
        TokenTable tokenTable = new TokenTable();
        data.getLoggedFoods().forEach(food -> tokenTable.addTokens(
                new ArrayList<>(Arrays.asList(
                        food.date().toString(),
                        food.mealTime().toString(),
                        food.foodName(),
                        Double.toString(food.amount()),
                        Double.toString(food.calories()),
                        Double.toString(food.carbs()),
                        Double.toString(food.fat()),
                        Double.toString(food.protein())
                ))
        ));
        return tokenTable;
    }
}
