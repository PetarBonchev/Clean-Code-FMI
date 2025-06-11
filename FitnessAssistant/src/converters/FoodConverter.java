package org.example.converters;

import org.example.common.Metric;
import org.example.database.TokenTable;
import org.example.database.dataTypes.Food;
import org.example.repository.concrete.FoodRepository;

import java.util.ArrayList;
import java.util.Arrays;

public final class FoodConverter {

    public FoodRepository toFood(TokenTable data) {
        ArrayList<Food> foods = new ArrayList<>();
        data.getTokens().forEach(line -> foods.add(
                new Food(line.get(0), line.get(1),
                        Double.parseDouble(line.get(2)),
                        Metric.valueOf(line.get(3).toUpperCase()),
                        Double.parseDouble(line.get(4)),
                        Double.parseDouble(line.get(5)),
                        Double.parseDouble(line.get(6)),
                        Double.parseDouble(line.get(7)),
                        Double.parseDouble(line.get(8)))));
        return new FoodRepository(foods);
    }

    public TokenTable toTokenTable(FoodRepository data) {
        TokenTable tokenTable = new TokenTable();
        data.getFoods().forEach(food -> tokenTable.addTokens(new ArrayList<>(
                Arrays.asList(food.name(), food.description(),
                        Double.toString(food.amount()),
                        food.metric().toString(),
                        Double.toString(food.servingsPerContainer()),
                        Double.toString(food.calories()),
                        Double.toString(food.carbs()),
                        Double.toString(food.fat()),
                        Double.toString(food.protein())))));
        return tokenTable;
    }
}
