package org.example.commands;

import org.example.commands.concreteCommands.CreateFood;
import org.example.commands.concreteCommands.CreateMeal;
import org.example.commands.concreteCommands.CreateRecipe;
import org.example.commands.concreteCommands.ExitApplication;
import org.example.commands.concreteCommands.LogFood;
import org.example.commands.concreteCommands.LogMeal;
import org.example.commands.concreteCommands.LogRecipe;
import org.example.commands.concreteCommands.LogWater;
import org.example.commands.concreteCommands.ViewAllFoods;
import org.example.commands.concreteCommands.ViewAllMeals;
import org.example.commands.concreteCommands.ViewAllLogged;
import org.example.commands.concreteCommands.ViewAllRecipes;
import org.example.commands.concreteCommands.ViewLoggedWater;
import org.example.repository.concrete.FoodRepository;
import org.example.repository.concrete.LoggedFoodRepository;
import org.example.repository.concrete.MealRepository;
import org.example.repository.concrete.RecipeRepository;
import org.example.ui.CommunicationChannel;
import org.example.repository.RepositoryContainer;
import org.example.repository.concrete.LoggedWaterRepository;

public final class CommandFactory {

    private final CommunicationChannel io;
    private final RepositoryContainer repositories;

    public CommandFactory(CommunicationChannel communicationChannel,
                          RepositoryContainer repositoryContainer) {
        io = communicationChannel;
        repositories = repositoryContainer;
    }

    public CommandContainer createCommands() {
        CommandContainer commandContainer = new CommandContainer();

        LogWater logWater = new LogWater(io,
                (LoggedWaterRepository) repositories.getByName("logged water"));
        ViewLoggedWater viewLoggedWater = new ViewLoggedWater(io,
                (LoggedWaterRepository) repositories.getByName("logged water"));
        CreateFood createFood = new CreateFood(io,
                (FoodRepository) repositories.getByName("food"));
        ViewAllFoods viewAllFoods = new ViewAllFoods(io,
                (FoodRepository) repositories.getByName("food"));
        LogFood logFood =
                new LogFood(io, (FoodRepository) repositories.getByName(
                        "food"), (LoggedFoodRepository) repositories.getByName(
                        "logged food"));
        ExitApplication exitApplication = new ExitApplication(io);

        ViewAllLogged viewAllLogged = new ViewAllLogged(io,
                (LoggedWaterRepository) repositories.getByName("logged water"),
                (LoggedFoodRepository) repositories.getByName("logged food"));

        CreateMeal createMeal = new CreateMeal(io,
                (MealRepository) repositories.getByName("meal"),
                (FoodRepository) repositories.getByName("food"));

        ViewAllMeals viewAllMeals = new ViewAllMeals(io,
                (MealRepository) repositories.getByName("meal"));

        LogMeal logMeal = new LogMeal(io,
                (MealRepository) repositories.getByName("meal"),
                (LoggedFoodRepository) repositories.getByName("logged food"),
                (FoodRepository) repositories.getByName("food"));

        CreateRecipe createRecipe = new CreateRecipe(io,
                (RecipeRepository) repositories.getByName("recipe"),
                (FoodRepository) repositories.getByName("food"));

        ViewAllRecipes viewAllRecipes = new ViewAllRecipes(io,
                (RecipeRepository) repositories.getByName("recipe"));

        LogRecipe logRecipe = new LogRecipe(io,
                (RecipeRepository) repositories.getByName("recipe"),
                (LoggedFoodRepository) repositories.getByName("logged food"));

        commandContainer.add(logWater);
        commandContainer.add(viewLoggedWater);
        commandContainer.add(createFood);
        commandContainer.add(viewAllFoods);
        commandContainer.add(logFood);
        commandContainer.add(viewAllLogged);
        commandContainer.add(createMeal);
        commandContainer.add(viewAllMeals);
        commandContainer.add(logMeal);
        commandContainer.add(createRecipe);
        commandContainer.add(viewAllRecipes);
        commandContainer.add(logRecipe);
        commandContainer.add(exitApplication);

        return commandContainer;
    }
}
