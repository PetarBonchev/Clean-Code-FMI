package org.example.repository;

import org.example.common.Filenames;
import org.example.converters.FoodConverter;
import org.example.converters.LoggedFoodConverter;
import org.example.converters.LoggedWaterConverter;
import org.example.converters.MealConverter;
import org.example.converters.RecipeConverter;
import org.example.database.LoadSaveManager;
import org.example.database.TokenTable;

public final class RepositoryFactory {

    private final LoadSaveManager database;

    public RepositoryFactory(LoadSaveManager loadSaveManager) {
        database = loadSaveManager;
    }

    public RepositoryContainer createRepositories(String username) {
        RepositoryContainer repositories = new RepositoryContainer();

        TokenTable tokenTable = database.load(Filenames.getFilesPath()
                + username + "/"
                + Filenames.getLoggedWaterFilename());
        LoggedWaterConverter loggedWaterConverter = new LoggedWaterConverter();
        repositories.add(loggedWaterConverter.toLoggedWater(tokenTable));

        tokenTable = database.load(Filenames.getFilesPath()
                + username + "/"
                + Filenames.getFoodFilename());
        FoodConverter foodConverter = new FoodConverter();
        repositories.add(foodConverter.toFood(tokenTable));

        tokenTable = database.load(Filenames.getFilesPath()
                + username + "/"
                + Filenames.getLoggedFoodFilename());
        LoggedFoodConverter loggedFoodConverter = new LoggedFoodConverter();
        repositories.add(loggedFoodConverter.toLoggedFood(tokenTable));

        tokenTable = database.load(Filenames.getFilesPath()
                + username + "/"
                + Filenames.getMealFilename());
        MealConverter mealConverter = new MealConverter();
        repositories.add(mealConverter.toMealRepository(tokenTable));

        tokenTable = database.load(Filenames.getFilesPath()
                + username + "/"
                + Filenames.getRecipeFilename());
        RecipeConverter recipeConverter = new RecipeConverter();
        repositories.add(recipeConverter.toRecipeRepository(tokenTable));

        return repositories;
    }
}
