package org.example.converters;

import org.example.database.TokenTable;
import org.example.database.dataTypes.Meal;
import org.example.database.dataTypes.MealPart;
import org.example.repository.concrete.MealRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MealConverterTest {

    @Test
    void toMealRepository() {
        MealRepository repository = getRepository();

        assertEquals(1, repository.getMeals().size());

        Meal meal = repository.getMeals().get(0);
        assertEquals("Breakfast Bowl", meal.name());
        assertEquals("Healthy breakfast with fruits", meal.description());
        assertEquals(350, meal.weight());
        assertEquals(250, meal.calories());
        assertEquals(40, meal.carbs());
        assertEquals(8, meal.fat());
        assertEquals(5, meal.protein());

        List<MealPart> mealParts = meal.foods();
        assertEquals(3, mealParts.size());

        assertEquals("Apple", mealParts.get(0).foodName());
        assertEquals(100, mealParts.get(0).amount());

        assertEquals("Yogurt", mealParts.get(1).foodName());
        assertEquals(150, mealParts.get(1).amount());

        assertEquals("Granola", mealParts.get(2).foodName());
        assertEquals(50, mealParts.get(2).amount());
    }

    private static MealRepository getRepository() {
        TokenTable table = new TokenTable();
        ArrayList<String> mealData = new ArrayList<>(Arrays.asList(
                "Breakfast Bowl",
                "Healthy breakfast with fruits",
                "350",
                "250",
                "40",
                "8",
                "5",
                "Apple",
                "100",
                "Yogurt",
                "150",
                "Granola",
                "50"
        ));
        table.addTokens(mealData);

        MealConverter converter = new MealConverter();
        MealRepository repository = converter.toMealRepository(table);
        return repository;
    }

    @Test
    void toMealRepositoryMultipleItems() {
        MealRepository repository = getMealRepository();

        assertEquals(2, repository.getMeals().size());

        Meal meal = repository.getMeals().get(1);
        assertEquals("Lunch Salad", meal.name());
        assertEquals("Fresh vegetable salad", meal.description());
        assertEquals(400, meal.weight());
        assertEquals(320, meal.calories());
        assertEquals(25, meal.carbs());
        assertEquals(15, meal.fat());
        assertEquals(12, meal.protein());

        List<MealPart> mealParts = meal.foods();
        assertEquals(4, mealParts.size());

        assertEquals("Lettuce", mealParts.get(0).foodName());
        assertEquals(150, mealParts.get(0).amount());

        assertEquals("Chicken", mealParts.get(1).foodName());
        assertEquals(120, mealParts.get(1).amount());

        assertEquals("Tomato", mealParts.get(2).foodName());
        assertEquals(80, mealParts.get(2).amount());

        assertEquals("Dressing", mealParts.get(3).foodName());
        assertEquals(30, mealParts.get(3).amount());
    }

    private static MealRepository getMealRepository() {
        TokenTable table = new TokenTable();

        ArrayList<String> breakfast = new ArrayList<>(Arrays.asList(
                "Breakfast Bowl",
                "Healthy breakfast with fruits",
                "350",
                "250",
                "40",
                "8",
                "5",
                "Apple",
                "100",
                "Yogurt",
                "150"
        ));
        table.addTokens(breakfast);

        ArrayList<String> lunch = new ArrayList<>(Arrays.asList(
                "Lunch Salad",
                "Fresh vegetable salad",
                "400",
                "320",
                "25",
                "15",
                "12",
                "Lettuce",
                "150",
                "Chicken",
                "120",
                "Tomato",
                "80",
                "Dressing",
                "30"
        ));
        table.addTokens(lunch);

        MealConverter converter = new MealConverter();
        return converter.toMealRepository(table);
    }

    @Test
    void toTokenTable() {
        TokenTable tokenTable = getTable();

        assertEquals(1, tokenTable.getTokens().size());
        List<String> tokens = tokenTable.getTokens().get(0);

        assertEquals("Breakfast Bowl", tokens.get(0));
        assertEquals("Healthy breakfast with fruits", tokens.get(1));
        assertEquals("350.0", tokens.get(2));
        assertEquals("250.0", tokens.get(3));
        assertEquals("40.0", tokens.get(4));
        assertEquals("8.0", tokens.get(5));
        assertEquals("5.0", tokens.get(6));
        assertEquals("Apple", tokens.get(7));
        assertEquals("100.0", tokens.get(8));
        assertEquals("Yogurt", tokens.get(9));
        assertEquals("150.0", tokens.get(10));
        assertEquals("Granola", tokens.get(11));
        assertEquals("50.0", tokens.get(12));
    }

    private static TokenTable getTable() {
        ArrayList<MealPart> mealParts = new ArrayList<>();
        mealParts.add(new MealPart("Apple", 100));
        mealParts.add(new MealPart("Yogurt", 150));
        mealParts.add(new MealPart("Granola", 50));

        ArrayList<Meal> meals = new ArrayList<>();
        meals.add(new Meal(
                "Breakfast Bowl",
                "Healthy breakfast with fruits",
                350,
                250,
                40,
                8,
                5,
                mealParts
        ));

        MealRepository repository = new MealRepository(meals);
        MealConverter converter = new MealConverter();

        return converter.toTokenTable(repository);
    }

    @Test
    void toTokenTableMultipleItems() {
        TokenTable tokenTable = getTokenTable();

        assertEquals(2, tokenTable.getTokens().size());

        List<String> tokens = tokenTable.getTokens().get(1);

        assertEquals("Lunch Salad", tokens.get(0));
        assertEquals("Fresh vegetable salad", tokens.get(1));
        assertEquals("400.0", tokens.get(2));
        assertEquals("320.0", tokens.get(3));
        assertEquals("25.0", tokens.get(4));
        assertEquals("15.0", tokens.get(5));
        assertEquals("12.0", tokens.get(6));
        assertEquals("Lettuce", tokens.get(7));
        assertEquals("150.0", tokens.get(8));
        assertEquals("Chicken", tokens.get(9));
        assertEquals("120.0", tokens.get(10));
        assertEquals("Tomato", tokens.get(11));
        assertEquals("80.0", tokens.get(12));
        assertEquals("Dressing", tokens.get(13));
        assertEquals("30.0", tokens.get(14));
    }

    private static TokenTable getTokenTable() {
        ArrayList<Meal> meals = new ArrayList<>();

        ArrayList<MealPart> breakfastParts = new ArrayList<>();
        breakfastParts.add(new MealPart("Apple", 100));
        breakfastParts.add(new MealPart("Yogurt", 150));

        meals.add(new Meal(
                "Breakfast Bowl",
                "Healthy breakfast with fruits",
                350,
                250,
                40,
                8,
                5,
                breakfastParts
        ));

        ArrayList<MealPart> lunchParts = new ArrayList<>();
        lunchParts.add(new MealPart("Lettuce", 150));
        lunchParts.add(new MealPart("Chicken", 120));
        lunchParts.add(new MealPart("Tomato", 80));
        lunchParts.add(new MealPart("Dressing", 30));

        meals.add(new Meal(
                "Lunch Salad",
                "Fresh vegetable salad",
                400,
                320,
                25,
                15,
                12,
                lunchParts
        ));

        MealRepository repository = new MealRepository(meals);
        MealConverter converter = new MealConverter();

        return converter.toTokenTable(repository);
    }

    @Test
    void toMealRepositoryEmptyMealParts() {
        TokenTable table = new TokenTable();
        ArrayList<String> mealData = new ArrayList<>(Arrays.asList(
                "Empty Meal",
                "Meal with no parts",
                "200",
                "100",
                "20",
                "5",
                "3"
        ));
        table.addTokens(mealData);

        MealConverter converter = new MealConverter();
        MealRepository repository = converter.toMealRepository(table);

        assertEquals(1, repository.getMeals().size());

        Meal meal = repository.getMeals().get(0);
        assertEquals("Empty Meal", meal.name());
        assertEquals(0, meal.foods().size());
    }
}
