package data;

import java.util.ArrayList;

public class FoodType extends DataVariant {
    private String foodName;
    private String description;
    private double servingSize;
    private int servingsPerContainer;
    private double amountPerServing;
    private int calories;
    private double carbs;
    private double fat;
    private double protein;

    public FoodType(ArrayList<String> data) {
        super(data);
        if (data.size() != 9) {
            throw new IllegalArgumentException("FoodType requires exactly 9 arguments.");
        }

        foodName = data.get(0);
        description = data.get(1);
        servingSize = Double.parseDouble(data.get(2));
        servingsPerContainer = Integer.parseInt(data.get(3));
        amountPerServing = Double.parseDouble(data.get(4));
        calories = Integer.parseInt(data.get(5));
        carbs = Double.parseDouble(data.get(6));
        fat = Double.parseDouble(data.get(7));
        protein = Double.parseDouble(data.get(8));
    }

    public String getFoodName() {
        return foodName;
    }

    public String getDescription() {
        return description;
    }

    public double getServingSize() {
        return servingSize;
    }

    public int getServingsPerContainer() {
        return servingsPerContainer;
    }

    public double getAmountPerServing() {
        return amountPerServing;
    }

    public int getCalories() {
        return calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFat() {
        return fat;
    }

    public double getProtein() {
        return protein;
    }

    @Override
    public String getDataAsMessage() {
        return foodName + " (" +
                servingSize + "g; " +
                calories + " kcal; " +
                carbs + "g, " +
                fat + "g, " +
                protein + "g)";
    }

    @Override
    public ArrayList<String> getData() {
        ArrayList<String> foodTypeData = new ArrayList<>();
        foodTypeData.add(foodName);
        foodTypeData.add(description);
        foodTypeData.add(String.valueOf(servingSize));
        foodTypeData.add(String.valueOf(servingsPerContainer));
        foodTypeData.add(String.valueOf(amountPerServing));
        foodTypeData.add(String.valueOf(calories));
        foodTypeData.add(String.valueOf(carbs));
        foodTypeData.add(String.valueOf(fat));
        foodTypeData.add(String.valueOf(protein));
        return foodTypeData;
    }

    public String getMessageWithCustomWeight(double weight) {
        double proportion = weight / servingSize;
        return weight + "g X " +
                foodName + " (Total: " +
                weight + "g; " +
                proportion * calories + "kcal; " +
                proportion * carbs + "g, " +
                proportion * fat + "g, " +
                proportion * protein + "g)";
    }

    public static ArrayList<FoodType> convertTableToFoodTypes(TokenTable tokenTable) {
        ArrayList<FoodType> foodData = new ArrayList<>();

        for (ArrayList<String> tokenLine : tokenTable.tokens) {
            FoodType foodType = new FoodType(tokenLine);
            foodData.add(foodType);
        }

        return foodData;
    }

    public static TokenTable convertFoodTypesToTable(ArrayList<FoodType> foodTypes) {
        TokenTable tokenTable = new TokenTable();

        for (FoodType foodType : foodTypes) {
            tokenTable.tokens.add(foodType.getData());
        }

        return tokenTable;
    }
}