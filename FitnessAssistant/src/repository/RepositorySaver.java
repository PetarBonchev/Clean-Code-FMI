
package org.example.repository;

import org.example.common.Filenames;
import org.example.converters.FoodConverter;
import org.example.converters.LoggedFoodConverter;
import org.example.converters.LoggedWaterConverter;
import org.example.converters.MealConverter;
import org.example.converters.RecipeConverter;
import org.example.database.LoadSaveManager;
import org.example.database.TokenTable;
import org.example.repository.concrete.FoodRepository;
import org.example.repository.concrete.LoggedFoodRepository;
import org.example.repository.concrete.LoggedWaterRepository;
import org.example.repository.concrete.MealRepository;
import org.example.repository.concrete.RecipeRepository;

import java.io.File;
import java.util.Objects;

public final class RepositorySaver {

    private final LoadSaveManager database;

    public RepositorySaver(LoadSaveManager loadSaveManager) {
        database = loadSaveManager;
    }

    public void save(RepositoryContainer repositoryContainer, String username) {
        String userDirectoryPath = Filenames.getFilesPath() + username + "/";
        ensureDirectoryExists(userDirectoryPath);

        LoggedWaterConverter loggedWaterConverter = new LoggedWaterConverter();
        TokenTable loggedWaterTable = loggedWaterConverter.toTokenTable(
                (LoggedWaterRepository) Objects.requireNonNull(
                        repositoryContainer.getByName(
                                "logged water")));
        database.save(userDirectoryPath
                        + Filenames.getLoggedWaterFilename(), loggedWaterTable);

        FoodConverter foodConverter = new FoodConverter();
        TokenTable foodTable =
                foodConverter.toTokenTable(
                        (FoodRepository) Objects.requireNonNull(
                                repositoryContainer.getByName("food")));
        database.save(userDirectoryPath + Filenames.getFoodFilename(),
                foodTable);

        LoggedFoodConverter loggedFoodConverter = new LoggedFoodConverter();
        TokenTable loggedFoodTable =
                loggedFoodConverter.toTokenTable(
                        (LoggedFoodRepository) Objects.requireNonNull(
                                repositoryContainer.getByName(
                                        "logged food")));
        database.save(userDirectoryPath
                        + Filenames.getLoggedFoodFilename(), loggedFoodTable);

        MealConverter mealConverter = new MealConverter();
        TokenTable mealTable = mealConverter.toTokenTable(
                (MealRepository) Objects.requireNonNull(
                        repositoryContainer.getByName("meal")));
        database.save(userDirectoryPath + Filenames.getMealFilename(),
                mealTable);

        RecipeConverter recipeConverter = new RecipeConverter();
        TokenTable recipeTable = recipeConverter.toTokenTable(
                (RecipeRepository) Objects.requireNonNull(
                        repositoryContainer.getByName("recipe")));
        database.save(userDirectoryPath + Filenames.getRecipeFilename(),
                recipeTable);
    }

    private void ensureDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}
