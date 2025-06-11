package org.example.commands.concreteCommands;

import org.example.common.DailyMealTime;
import org.example.database.dataTypes.LoggedFood;
import org.example.database.dataTypes.Meal;
import org.example.database.dataTypes.MealPart;
import org.example.repository.concrete.LoggedFoodRepository;
import org.example.repository.concrete.RecipeRepository;
import org.example.ui.CommunicationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogRecipeTest {

    private RecipeRepository recipeRepository;
    private LoggedFoodRepository loggedFoodRepository;

    @BeforeEach
    void setup() {
        ArrayList<MealPart> mealParts = new ArrayList<>();
        mealParts.add(new MealPart("Flour", 1));
        mealParts.add(new MealPart("Eggs", 2));
        ArrayList<Meal> recipes = new ArrayList<>();
        recipes.add(
                new Meal("Pancakes", "Breakfast favorite", 300, 425, 56, 4.2,
                        36.4, mealParts));
        recipeRepository = new RecipeRepository(recipes);
        loggedFoodRepository = new LoggedFoodRepository(new ArrayList<>());
    }

    @Test
    void getName() {
        LogRecipe logRecipe =
                new LogRecipe(null, recipeRepository, loggedFoodRepository);
        assertEquals("Log recipe", logRecipe.getName());
    }

    @Test
    void execute() {
        String input = "2023-01-01\nBREAKFAST\n1\n2";
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommunicationChannel io = new CommunicationChannel(inputStream,
                new PrintStream(outputStream));

        LogRecipe logRecipe =
                new LogRecipe(io, recipeRepository, loggedFoodRepository);
        logRecipe.execute();

        assertEquals(1, loggedFoodRepository.getLoggedFoods().size());
        LoggedFood loggedFood = loggedFoodRepository.getLoggedFoods().get(0);
        assertEquals(LocalDate.parse("2023-01-01"), loggedFood.date());
        assertEquals(DailyMealTime.BREAKFAST, loggedFood.mealTime());
        assertEquals("Pancakes", loggedFood.foodName());
        assertEquals(850, loggedFood.calories());
    }
}
