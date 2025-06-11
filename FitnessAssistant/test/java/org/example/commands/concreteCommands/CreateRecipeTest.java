package org.example.commands.concreteCommands;

import org.example.common.Metric;
import org.example.database.dataTypes.Food;
import org.example.repository.concrete.FoodRepository;
import org.example.repository.concrete.RecipeRepository;
import org.example.ui.CommunicationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateRecipeTest {

    private RecipeRepository recipeRepository;
    private FoodRepository foodRepository;

    @BeforeEach
    void setup() {
        ArrayList<Food> foods = new ArrayList<>();
        foods.add(new Food("flour", "", 12, Metric.G, 100, 364, 76, 1, 10));
        foods.add(new Food("eggs", "", 12, Metric.G, 50, 143, 0.7, 9.5, 12.6));
        foodRepository = new FoodRepository(foods);
        recipeRepository = new RecipeRepository(new ArrayList<>());
    }

    @Test
    void getName() {
        CreateRecipe createRecipe =
                new CreateRecipe(null, recipeRepository, foodRepository);
        assertEquals("Create recipe", createRecipe.getName());
    }

    @Test
    void execute() {
        String input = "banica\nDelicious pastry\n4\n1\n2\nyes\n2\n1\nno";
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommunicationChannel io = new CommunicationChannel(inputStream,
                new PrintStream(outputStream));

        CreateRecipe createRecipe =
                new CreateRecipe(io, recipeRepository, foodRepository);
        createRecipe.execute();

        assertEquals("banica", recipeRepository.getRecipes().get(0).name());
        assertEquals(2, recipeRepository.getRecipes().get(0).foods().size());
    }
}
