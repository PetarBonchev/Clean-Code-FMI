package org.example.common;

public final class Filenames {
    private static final String FILES_PATH =
            "src/main/java/org/example/database/userFiles/";
    private static final String LOGGED_WATER_FILENAME = "loggedWater.txt";
    private static final String FOOD_FILENAME = "foods.txt";
    private static final String LOGGED_FOOD_FILENAME = "loggedFoods.txt";
    private static final String MEAL_FILENAME = "meals.txt";
    private static final String RECIPE_FILENAME = "recipes.txt";

    private Filenames() { }

    public static String getFilesPath() {
        return FILES_PATH;
    }

    public static String getLoggedWaterFilename() {
        return LOGGED_WATER_FILENAME;
    }

    public static String getFoodFilename() {
        return FOOD_FILENAME;
    }

    public static String getLoggedFoodFilename() {
        return LOGGED_FOOD_FILENAME;
    }

    public static String getMealFilename() {
        return MEAL_FILENAME;
    }

    public static String getRecipeFilename() {
        return RECIPE_FILENAME;
    }
}
