package org.example.converters;

import org.example.common.DailyMealTime;
import org.example.database.TokenTable;
import org.example.database.dataTypes.LoggedFood;
import org.example.repository.concrete.LoggedFoodRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LoggedFoodConverterTest {

    @Test
    void toLoggedFood() {
        TokenTable table = new TokenTable();
        ArrayList<String> loggedFoodData = new ArrayList<>(Arrays.asList(
                "2025-05-18",
                "BREAKFAST",
                "Oatmeal",
                "150",
                "120",
                "20",
                "3",
                "5"
        ));
        table.addTokens(loggedFoodData);

        LoggedFoodConverter converter = new LoggedFoodConverter();
        LoggedFoodRepository repository = converter.toLoggedFood(table);

        assertEquals(1, repository.getLoggedFoods().size());

        LoggedFood loggedFood = repository.getLoggedFoods().get(0);
        assertEquals(LocalDate.parse("2025-05-18"), loggedFood.date());
        assertEquals(DailyMealTime.BREAKFAST, loggedFood.mealTime());
        assertEquals("Oatmeal", loggedFood.foodName());
        assertEquals(150, loggedFood.amount());
        assertEquals(120, loggedFood.calories());
        assertEquals(20, loggedFood.carbs());
        assertEquals(3, loggedFood.fat());
        assertEquals(5, loggedFood.protein());
    }

    @Test
    void toLoggedFoodMultipleItems() {
        LoggedFoodRepository repository =
                getLoggedFoodRepository();

        assertEquals(3, repository.getLoggedFoods().size());

        LoggedFood loggedFood = repository.getLoggedFoods().get(1);
        assertEquals(LocalDate.parse("2025-05-18"), loggedFood.date());
        assertEquals(DailyMealTime.LUNCH, loggedFood.mealTime());
        assertEquals("Chicken Salad", loggedFood.foodName());
        assertEquals(300, loggedFood.amount());
        assertEquals(350, loggedFood.calories());
        assertEquals(15, loggedFood.carbs());
        assertEquals(12, loggedFood.fat());
        assertEquals(40, loggedFood.protein());

        loggedFood = repository.getLoggedFoods().get(2);
        assertEquals(LocalDate.parse("2025-05-19"), loggedFood.date());
        assertEquals(DailyMealTime.BREAKFAST, loggedFood.mealTime());
        assertEquals("Scrambled Eggs", loggedFood.foodName());
    }

    private static LoggedFoodRepository getLoggedFoodRepository() {
        TokenTable table = new TokenTable();

        ArrayList<String> breakfast = new ArrayList<>(Arrays.asList(
                "2025-05-18",
                "BREAKFAST",
                "Oatmeal",
                "150",
                "120",
                "20",
                "3",
                "5"
        ));
        table.addTokens(breakfast);

        ArrayList<String> lunch = new ArrayList<>(Arrays.asList(
                "2025-05-18",
                "LUNCH",
                "Chicken Salad",
                "300",
                "350",
                "15",
                "12",
                "40"
        ));
        table.addTokens(lunch);

        ArrayList<String> nextDayBreakfast = new ArrayList<>(Arrays.asList(
                "2025-05-19",
                "BREAKFAST",
                "Scrambled Eggs",
                "200",
                "180",
                "2",
                "12",
                "16"
        ));
        table.addTokens(nextDayBreakfast);

        LoggedFoodConverter converter = new LoggedFoodConverter();
        return converter.toLoggedFood(table);
    }

    @Test
    void toTokenTable() {
        TokenTable tokenTable = getTable();

        assertEquals(1, tokenTable.getTokens().size());
        List<String> tokens = tokenTable.getTokens().get(0);

        assertEquals("2025-05-18", tokens.get(0));
        assertEquals("BREAKFAST", tokens.get(1));
        assertEquals("Oatmeal", tokens.get(2));
        assertEquals("150.0", tokens.get(3));
        assertEquals("120.0", tokens.get(4));
        assertEquals("20.0", tokens.get(5));
        assertEquals("3.0", tokens.get(6));
        assertEquals("5.0", tokens.get(7));
    }

    private static TokenTable getTable() {
        ArrayList<LoggedFood> foods = new ArrayList<>();
        foods.add(new LoggedFood(
                LocalDate.parse("2025-05-18"),
                DailyMealTime.BREAKFAST,
                "Oatmeal",
                150,
                120,
                20,
                3,
                5
        ));

        LoggedFoodRepository repository = new LoggedFoodRepository(foods);
        LoggedFoodConverter converter = new LoggedFoodConverter();

        return converter.toTokenTable(repository);
    }

    @Test
    void toTokenTableMultipleItems() {
        TokenTable tokenTable = getTokenTable();

        assertEquals(3, tokenTable.getTokens().size());

        List<String> tokens = tokenTable.getTokens().get(1);

        assertEquals("2025-05-18", tokens.get(0));
        assertEquals("LUNCH", tokens.get(1));
        assertEquals("Chicken Salad", tokens.get(2));
        assertEquals("300.0", tokens.get(3));
        assertEquals("350.0", tokens.get(4));
        assertEquals("15.0", tokens.get(5));
        assertEquals("12.0", tokens.get(6));
        assertEquals("40.0", tokens.get(7));

        tokens = tokenTable.getTokens().get(2);
        assertEquals("2025-05-19", tokens.get(0));
        assertEquals("BREAKFAST", tokens.get(1));
        assertEquals("Scrambled Eggs", tokens.get(2));
    }

    private static TokenTable getTokenTable() {
        ArrayList<LoggedFood> foods = new ArrayList<>();

        foods.add(new LoggedFood(
                LocalDate.parse("2025-05-18"),
                DailyMealTime.BREAKFAST,
                "Oatmeal",
                150,
                120,
                20,
                3,
                5
        ));

        foods.add(new LoggedFood(
                LocalDate.parse("2025-05-18"),
                DailyMealTime.LUNCH,
                "Chicken Salad",
                300,
                350,
                15,
                12,
                40
        ));

        foods.add(new LoggedFood(
                LocalDate.parse("2025-05-19"),
                DailyMealTime.BREAKFAST,
                "Scrambled Eggs",
                200,
                180,
                2,
                12,
                16
        ));

        LoggedFoodRepository repository = new LoggedFoodRepository(foods);
        LoggedFoodConverter converter = new LoggedFoodConverter();

        TokenTable tokenTable = converter.toTokenTable(repository);
        return tokenTable;
    }

    @Test
    void toTokenTableAllMealTimes() {
        ArrayList<LoggedFood> foods = new ArrayList<>();

        DailyMealTime[] mealTimes = {
                DailyMealTime.BREAKFAST,
                DailyMealTime.LUNCH,
                DailyMealTime.DINNER,
                DailyMealTime.SNACKS
        };

        for (DailyMealTime mealTime : mealTimes) {
            foods.add(new LoggedFood(
                    LocalDate.parse("2025-05-18"),
                    mealTime,
                    "Test Food",
                    100,
                    100,
                    10,
                    5,
                    5
            ));
        }

        LoggedFoodRepository repository = new LoggedFoodRepository(foods);
        LoggedFoodConverter converter = new LoggedFoodConverter();

        TokenTable tokenTable = converter.toTokenTable(repository);

        assertEquals(4, tokenTable.getTokens().size());

        for (int i = 0; i < mealTimes.length; i++) {
            assertEquals(mealTimes[i].toString(), tokenTable.getTokens().
                    get(i).get(1));
        }
    }
}
