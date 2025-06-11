package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.database.dataTypes.Food;
import org.example.database.dataTypes.Meal;
import org.example.database.dataTypes.MealPart;
import org.example.repository.concrete.FoodRepository;
import org.example.repository.concrete.RecipeRepository;
import org.example.ui.CommunicationChannel;

import java.util.ArrayList;

public final class CreateRecipe extends Command {

    private final RecipeRepository recipes;
    private final FoodRepository foods;

    public CreateRecipe(CommunicationChannel io,
                        RecipeRepository recipeRepository,
                        FoodRepository foodRepository) {
        super(io);
        recipes = recipeRepository;
        foods = foodRepository;
    }

    @Override public String getName() {
        return "Create recipe";
    }

    @Override public void execute() {
        getIO().writeLine("Name:");
        String name = getIO().readLine();
        getIO().writeLine("Description(optional):");
        String description = getIO().readLine();
        getIO().writeLine("Servings:");
        double servings = Double.parseDouble(getIO().readLine());
        getIO().writeLine("Items:");
        Meal created = create(name, description, servings, inputFoods());
        recipes.addRecipe(created);
    }

    private ArrayList<MealPart> inputFoods() {
        ArrayList<MealPart> mealParts = new ArrayList<>();
        ViewAllFoods viewAllFoods = new ViewAllFoods(getIO(), foods);

        do {
            getIO().writeLine("All foods list:");
            viewAllFoods.execute();
            int index = Integer.parseInt(getIO().readLine()) - 1;
            getIO().writeLine("Number of servings:");
            double servings = Double.parseDouble(getIO().readLine());

            mealParts.add(new MealPart(foods.getFoods().get(index).name(),
                    servings));
            getIO().writeLine("More? (yes/no)");
        } while (getIO().readLine().equals("yes"));
        return mealParts;
    }

    private Meal create(String name, String description, double servings,
                        ArrayList<MealPart> mealParts) {
        double weight = 0;
        double calories = 0;
        double carbs = 0;
        double fat = 0;
        double protein = 0;

        for (MealPart mealPart : mealParts) {
            Food food = foods.getByName(mealPart.foodName());
            weight += mealPart.amount() * food.amount();
            calories += mealPart.amount() * food.calories();
            carbs += mealPart.amount() * food.carbs();
            fat += mealPart.amount() * food.fat();
            protein += mealPart.amount() * food.protein();
        }

        return new Meal(name, description, weight * servings,
                calories * servings, carbs * servings, fat * servings,
                protein * servings, mealParts);
    }
}
