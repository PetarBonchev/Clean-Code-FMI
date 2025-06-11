package org.example.converters;

import org.example.database.TokenTable;
import org.example.database.dataTypes.Meal;
import org.example.database.dataTypes.MealPart;
import org.example.repository.concrete.RecipeRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeConverterTest {

    @Test
    void toRecipeRepository() {
        RecipeRepository repository = getRepository();

        assertEquals(1, repository.getRecipes().size());

        Meal recipe = repository.getRecipes().get(0);
        assertEquals("Chicken Soup", recipe.name());
        assertEquals("Homemade chicken soup with vegetables",
                recipe.description());
        assertEquals(500, recipe.weight());
        assertEquals(350, recipe.calories());
        assertEquals(30, recipe.carbs());
        assertEquals(12, recipe.fat());
        assertEquals(25, recipe.protein());

        List<MealPart> ingredients = recipe.foods();
        assertEquals(5, ingredients.size());

        assertEquals("Chicken", ingredients.get(0).foodName());
        assertEquals(200, ingredients.get(0).amount());

        assertEquals("Carrot", ingredients.get(1).foodName());
        assertEquals(50, ingredients.get(1).amount());

        assertEquals("Celery", ingredients.get(2).foodName());
        assertEquals(30, ingredients.get(2).amount());

        assertEquals("Onion", ingredients.get(3).foodName());
        assertEquals(40, ingredients.get(3).amount());

        assertEquals("Water", ingredients.get(4).foodName());
        assertEquals(300, ingredients.get(4).amount());
    }

    private static RecipeRepository getRepository() {
        TokenTable table = new TokenTable();
        ArrayList<String> recipeData = new ArrayList<>(Arrays.asList(
                "Chicken Soup",
                "Homemade chicken soup with vegetables",
                "500",
                "350",
                "30",
                "12",
                "25",
                "Chicken",
                "200",
                "Carrot",
                "50",
                "Celery",
                "30",
                "Onion",
                "40",
                "Water",
                "300"
        ));
        table.addTokens(recipeData);

        RecipeConverter converter = new RecipeConverter();
        return converter.toRecipeRepository(table);
    }

    @Test
    void toRecipeRepositoryMultipleItems() {
        RecipeRepository repository =
                getRecipeRepository();

        assertEquals(2, repository.getRecipes().size());

        Meal recipe = repository.getRecipes().get(1);
        assertEquals("Chocolate Cake", recipe.name());
        assertEquals("Rich chocolate dessert", recipe.description());
        assertEquals(300, recipe.weight());
        assertEquals(450, recipe.calories());
        assertEquals(65, recipe.carbs());
        assertEquals(22, recipe.fat());
        assertEquals(8, recipe.protein());

        List<MealPart> ingredients = recipe.foods();
        assertEquals(5, ingredients.size());

        assertEquals("Flour", ingredients.get(0).foodName());
        assertEquals(100, ingredients.get(0).amount());

        assertEquals("Sugar", ingredients.get(1).foodName());
        assertEquals(80, ingredients.get(1).amount());

        assertEquals("Chocolate", ingredients.get(2).foodName());
        assertEquals(60, ingredients.get(2).amount());

        assertEquals("Butter", ingredients.get(3).foodName());
        assertEquals(40, ingredients.get(3).amount());

        assertEquals("Eggs", ingredients.get(4).foodName());
        assertEquals(50, ingredients.get(4).amount());
    }

    private static RecipeRepository getRecipeRepository() {
        TokenTable table = new TokenTable();

        ArrayList<String> soup = new ArrayList<>(Arrays.asList(
                "Chicken Soup",
                "Homemade chicken soup with vegetables",
                "500",
                "350",
                "30",
                "12",
                "25",
                "Chicken",
                "200",
                "Vegetables",
                "150"
        ));
        table.addTokens(soup);

        ArrayList<String> cake = new ArrayList<>(Arrays.asList(
                "Chocolate Cake",
                "Rich chocolate dessert",
                "300",
                "450",
                "65",
                "22",
                "8",
                "Flour",
                "100",
                "Sugar",
                "80",
                "Chocolate",
                "60",
                "Butter",
                "40",
                "Eggs",
                "50"
        ));
        table.addTokens(cake);

        RecipeConverter converter = new RecipeConverter();
        return converter.toRecipeRepository(table);
    }

    @Test
    void toTokenTable() {
        TokenTable tokenTable = getTable();

        assertEquals(1, tokenTable.getTokens().size());
        List<String> tokens = tokenTable.getTokens().get(0);

        assertEquals("Chicken Soup", tokens.get(0));
        assertEquals("Homemade chicken soup with vegetables", tokens.get(1));
        assertEquals("500.0", tokens.get(2));
        assertEquals("350.0", tokens.get(3));
        assertEquals("30.0", tokens.get(4));
        assertEquals("12.0", tokens.get(5));
        assertEquals("25.0", tokens.get(6));
        assertEquals("Chicken", tokens.get(7));
        assertEquals("200.0", tokens.get(8));
        assertEquals("Carrot", tokens.get(9));
        assertEquals("50.0", tokens.get(10));
        assertEquals("Celery", tokens.get(11));
        assertEquals("30.0", tokens.get(12));
        assertEquals("Onion", tokens.get(13));
        assertEquals("40.0", tokens.get(14));
        assertEquals("Water", tokens.get(15));
        assertEquals("300.0", tokens.get(16));
    }

    private static TokenTable getTable() {
        ArrayList<MealPart> ingredients = new ArrayList<>();
        ingredients.add(new MealPart("Chicken", 200));
        ingredients.add(new MealPart("Carrot", 50));
        ingredients.add(new MealPart("Celery", 30));
        ingredients.add(new MealPart("Onion", 40));
        ingredients.add(new MealPart("Water", 300));

        ArrayList<Meal> recipes = new ArrayList<>();
        recipes.add(new Meal(
                "Chicken Soup",
                "Homemade chicken soup with vegetables",
                500,
                350,
                30,
                12,
                25,
                ingredients
        ));

        RecipeRepository repository = new RecipeRepository(recipes);
        RecipeConverter converter = new RecipeConverter();

        return converter.toTokenTable(repository);
    }

    @Test
    void toTokenTableMultipleItems() {
        TokenTable tokenTable = getTokenTable();

        assertEquals(2, tokenTable.getTokens().size());

        List<String> tokens = tokenTable.getTokens().get(1);

        assertEquals("Chocolate Cake", tokens.get(0));
        assertEquals("Rich chocolate dessert", tokens.get(1));
        assertEquals("300.0", tokens.get(2));
        assertEquals("450.0", tokens.get(3));
        assertEquals("65.0", tokens.get(4));
        assertEquals("22.0", tokens.get(5));
        assertEquals("8.0", tokens.get(6));
        assertEquals("Flour", tokens.get(7));
        assertEquals("100.0", tokens.get(8));
        assertEquals("Sugar", tokens.get(9));
        assertEquals("80.0", tokens.get(10));
        assertEquals("Chocolate", tokens.get(11));
        assertEquals("60.0", tokens.get(12));
        assertEquals("Butter", tokens.get(13));
        assertEquals("40.0", tokens.get(14));
        assertEquals("Eggs", tokens.get(15));
        assertEquals("50.0", tokens.get(16));
    }

    private static TokenTable getTokenTable() {
        ArrayList<Meal> recipes = new ArrayList<>();

        ArrayList<MealPart> soupIngredients = new ArrayList<>();
        soupIngredients.add(new MealPart("Chicken", 200));
        soupIngredients.add(new MealPart("Vegetables", 150));

        recipes.add(new Meal(
                "Chicken Soup",
                "Homemade chicken soup with vegetables",
                500,
                350,
                30,
                12,
                25,
                soupIngredients
        ));

        ArrayList<MealPart> cakeIngredients = new ArrayList<>();
        cakeIngredients.add(new MealPart("Flour", 100));
        cakeIngredients.add(new MealPart("Sugar", 80));
        cakeIngredients.add(new MealPart("Chocolate", 60));
        cakeIngredients.add(new MealPart("Butter", 40));
        cakeIngredients.add(new MealPart("Eggs", 50));

        recipes.add(new Meal(
                "Chocolate Cake",
                "Rich chocolate dessert",
                300,
                450,
                65,
                22,
                8,
                cakeIngredients
        ));

        RecipeRepository repository = new RecipeRepository(recipes);
        RecipeConverter converter = new RecipeConverter();

        return converter.toTokenTable(repository);
    }

    @Test
    void toRecipeRepositoryEmptyIngredients() {
        TokenTable table = new TokenTable();
        ArrayList<String> recipeData = new ArrayList<>(Arrays.asList(
                "Empty Recipe",
                "Recipe with no ingredients",
                "200",
                "0",
                "0",
                "0",
                "0"
        ));
        table.addTokens(recipeData);

        RecipeConverter converter = new RecipeConverter();
        RecipeRepository repository = converter.toRecipeRepository(table);

        assertEquals(1, repository.getRecipes().size());

        Meal recipe = repository.getRecipes().get(0);
        assertEquals("Empty Recipe", recipe.name());
        assertEquals(0, recipe.foods().size());
    }
}
