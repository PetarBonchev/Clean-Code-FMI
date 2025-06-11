package org.example.commands.concreteCommands;

import org.example.common.Metric;
import org.example.database.dataTypes.Food;
import org.example.repository.concrete.FoodRepository;
import org.example.repository.concrete.MealRepository;
import org.example.ui.CommunicationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateMealTest {

    private MealRepository mealRepository;
    private FoodRepository foodRepository;

    @BeforeEach
    void setup() {
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("chicken", "", 12, Metric.G, 100, 165, 0, 3.6, 31));
        foods.add(new Food("rice", "", 12, Metric.G, 100, 130, 28, 0.3, 2.7));
        foodRepository = new FoodRepository(foods);
        mealRepository = new MealRepository(new ArrayList<>());
    }

    @Test
    void getName() {
        CreateMeal createMeal =
                new CreateMeal(null, mealRepository, foodRepository);
        assertEquals("Create meal", createMeal.getName());
    }

    @Test
    void execute() {
        String input = "Chicken Rice\nHealthy meal\n2\n1\n1\nyes\n2\n0.5\nno";
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommunicationChannel io = new CommunicationChannel(inputStream,
                new PrintStream(outputStream));

        CreateMeal createMeal =
                new CreateMeal(io, mealRepository, foodRepository);
        createMeal.execute();

        assertEquals("Chicken Rice", mealRepository.getMeals().get(0).name());
        assertEquals(2, mealRepository.getMeals().get(0).foods().size());
    }
}
