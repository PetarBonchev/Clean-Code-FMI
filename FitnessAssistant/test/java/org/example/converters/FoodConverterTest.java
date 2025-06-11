package org.example.converters;

import org.example.common.Metric;
import org.example.database.TokenTable;
import org.example.database.dataTypes.Food;
import org.example.repository.concrete.FoodRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


class FoodConverterTest {
    @Test
    void toFood() {
        TokenTable table = new TokenTable();
        ArrayList<String> foodData = new ArrayList<>(Arrays.asList(
                "Apple",
                "Fresh red apple",
                "100",
                "G",
                "1",
                "52",
                "14",
                "0.2",
                "0.3"
        ));
        table.addTokens(foodData);

        FoodConverter converter = new FoodConverter();
        FoodRepository createdRepo = converter.toFood(table);

        assertEquals("Apple", createdRepo.getFoods().get(0).name());
        assertEquals("Fresh red apple",
                createdRepo.getFoods().get(0).description());
        assertEquals(100, createdRepo.getFoods().get(0).amount());
        assertEquals(1, createdRepo.getFoods().get(0).servingsPerContainer());
        assertEquals(52, createdRepo.getFoods().get(0).calories());
        assertEquals(14, createdRepo.getFoods().get(0).carbs());
        assertEquals(0.2, createdRepo.getFoods().get(0).fat());
        assertEquals(0.3, createdRepo.getFoods().get(0).protein());
    }

    @Test
    void toFoodMultipleItems() {
        FoodRepository createdRepo =
                getFoodRepository();

        assertEquals(2, createdRepo.getFoods().size());

        assertEquals("Banana", createdRepo.getFoods().get(1).name());
        assertEquals("Yellow banana",
                createdRepo.getFoods().get(1).description());
        assertEquals(120, createdRepo.getFoods().get(1).amount());
        assertEquals(1, createdRepo.getFoods().get(1).servingsPerContainer());
        assertEquals(105, createdRepo.getFoods().get(1).calories());
        assertEquals(27, createdRepo.getFoods().get(1).carbs());
        assertEquals(0.3, createdRepo.getFoods().get(1).fat());
        assertEquals(1.1, createdRepo.getFoods().get(1).protein());
    }

    private static FoodRepository getFoodRepository() {
        TokenTable table = new TokenTable();

        ArrayList<String> apple = new ArrayList<>(Arrays.asList(
                "Apple",
                "Fresh red apple",
                "100",
                "G",
                "1",
                "52",
                "14",
                "0.2",
                "0.3"
        ));
        table.addTokens(apple);

        ArrayList<String> banana = new ArrayList<>(Arrays.asList(
                "Banana",
                "Yellow banana",
                "120",
                "G",
                "1",
                "105",
                "27",
                "0.3",
                "1.1"
        ));
        table.addTokens(banana);

        FoodConverter converter = new FoodConverter();
        return converter.toFood(table);
    }

    @Test
    void toTokenTable() {
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food(
                "Apple",
                "Fresh red apple",
                100,
                Metric.G,
                1,
                52,
                14,
                0.2,
                0.3
        ));

        FoodRepository foodRepository = new FoodRepository(foods);
        FoodConverter converter = new FoodConverter();
        TokenTable converted = converter.toTokenTable(foodRepository);

        assertEquals("Apple", converted.getTokens().get(0).get(0));
        assertEquals("Fresh red apple", converted.getTokens().get(0).get(1));
        assertEquals("100.0", converted.getTokens().get(0).get(2));
        assertEquals("1.0", converted.getTokens().get(0).get(4));
        assertEquals("52.0", converted.getTokens().get(0).get(5));
        assertEquals("14.0", converted.getTokens().get(0).get(6));
        assertEquals("0.2", converted.getTokens().get(0).get(7));
        assertEquals("0.3", converted.getTokens().get(0).get(8));
    }

    @Test
    void toTokenTableMultipleItems() {
        TokenTable converted = getTokenTable();

        assertEquals(2, converted.getTokens().size());

        assertEquals("Banana", converted.getTokens().get(1).get(0));
        assertEquals("Yellow banana", converted.getTokens().get(1).get(1));
        assertEquals("120.0", converted.getTokens().get(1).get(2));
        assertEquals("1.0", converted.getTokens().get(1).get(4));
        assertEquals("105.0", converted.getTokens().get(1).get(5));
        assertEquals("27.0", converted.getTokens().get(1).get(6));
        assertEquals("0.3", converted.getTokens().get(1).get(7));
        assertEquals("1.1", converted.getTokens().get(1).get(8));
    }

    private static TokenTable getTokenTable() {
        ArrayList<Food> foods = new ArrayList<>();

        foods.add(new Food(
                "Apple",
                "Fresh red apple",
                100,
                Metric.G,
                1,
                52,
                14,
                0.2,
                0.3
        ));

        foods.add(new Food(
                "Banana",
                "Yellow banana",
                120,
                Metric.G,
                1,
                105,
                27,
                0.3,
                1.1
        ));

        FoodRepository foodRepository = new FoodRepository(foods);
        FoodConverter converter = new FoodConverter();
        return converter.toTokenTable(foodRepository);
    }
}
