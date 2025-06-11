package org.example.commands.concreteCommands;

import org.example.common.DailyMealTime;
import org.example.common.Metric;
import org.example.database.dataTypes.Food;
import org.example.database.dataTypes.LoggedFood;
import org.example.database.dataTypes.Meal;
import org.example.database.dataTypes.MealPart;
import org.example.repository.concrete.FoodRepository;
import org.example.repository.concrete.LoggedFoodRepository;
import org.example.repository.concrete.MealRepository;
import org.example.ui.CommunicationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogMealTest {

    private MealRepository mealRepository;
    private LoggedFoodRepository loggedFoodRepository;
    private FoodRepository foodRepository;

    @BeforeEach
    void setup() {
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("Chicken", "", 100, Metric.G, 100, 165, 0, 3.6, 31));
        foods.add(new Food("Rice", "", 100, Metric.G, 100, 130, 28, 0.3, 2.7));
        foodRepository = new FoodRepository(foods);

        ArrayList<MealPart> mealParts = new ArrayList<>();
        mealParts.add(new MealPart("Chicken", 1));
        mealParts.add(new MealPart("Rice", 2));
        ArrayList<Meal> meals = new ArrayList<>();
        meals.add(new Meal("Chicken Rice", "Healthy meal", 300, 425, 56, 4.2,
                36.4, mealParts));
        mealRepository = new MealRepository(meals);

        loggedFoodRepository = new LoggedFoodRepository(new ArrayList<>());
    }

    @Test
    void getName() {
        LogMeal logMeal =
                new LogMeal(null, mealRepository, loggedFoodRepository,
                        foodRepository);
        assertEquals("Log meal", logMeal.getName());
    }

    @Test
    void execute() {
        String input = "2023-01-01\nDINNER\n1\n2";
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommunicationChannel io = new CommunicationChannel(inputStream,
                new PrintStream(outputStream));

        LogMeal logMeal = new LogMeal(io, mealRepository, loggedFoodRepository,
                foodRepository);
        logMeal.execute();

        assertEquals(2, loggedFoodRepository.getLoggedFoods().size());

        LoggedFood chicken = loggedFoodRepository.getLoggedFoods().get(0);
        assertEquals(LocalDate.parse("2023-01-01"), chicken.date());
        assertEquals(DailyMealTime.DINNER, chicken.mealTime());
        assertEquals("Chicken", chicken.foodName());
        assertEquals(330, chicken.calories());

        LoggedFood rice = loggedFoodRepository.getLoggedFoods().get(1);
        assertEquals(260, rice.calories());
    }
}
