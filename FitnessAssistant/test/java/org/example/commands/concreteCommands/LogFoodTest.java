package org.example.commands.concreteCommands;

import org.example.common.DailyMealTime;
import org.example.common.Metric;
import org.example.database.dataTypes.Food;
import org.example.database.dataTypes.LoggedFood;
import org.example.repository.concrete.FoodRepository;
import org.example.repository.concrete.LoggedFoodRepository;
import org.example.ui.CommunicationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogFoodTest {

    private FoodRepository foodRepository;
    private LoggedFoodRepository loggedFoodRepository;

    @BeforeEach
    void setup() {
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("Banana", "", 100,
                Metric.G, 100, 89, 22.8, 0.3, 1.1));
        foodRepository = new FoodRepository(foods);

        ArrayList<LoggedFood> loggedFoods = new ArrayList<>();
        loggedFoods.add(new LoggedFood(LocalDate.parse("2023-01-01"),
                DailyMealTime.BREAKFAST,
                "Oatmeal", 100, 389, 66.3, 6.9, 16.9));
        loggedFoodRepository = new LoggedFoodRepository(loggedFoods);
    }

    @Test
    void getName() {
        LogFood logFood =
                new LogFood(null, foodRepository, loggedFoodRepository);
        assertEquals("Log food", logFood.getName());
    }

    @Test
    void execute() {
        String input = "2023-01-02\nLUNCH\n1\n50";
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommunicationChannel io = new CommunicationChannel(inputStream,
                new PrintStream(outputStream));

        LogFood logFood = new LogFood(io, foodRepository, loggedFoodRepository);
        logFood.execute();

        LoggedFood loggedFood = loggedFoodRepository.getLoggedFoods().get(1);
        assertEquals(LocalDate.parse("2023-01-02"), loggedFood.date());
        assertEquals(DailyMealTime.LUNCH, loggedFood.mealTime());
        assertEquals("Banana", loggedFood.foodName());
        assertEquals(44.5, loggedFood.calories());
    }
}
