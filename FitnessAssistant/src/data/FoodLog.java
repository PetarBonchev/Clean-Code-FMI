package data;

import general.Main;

import java.time.LocalDate;
import java.util.ArrayList;

public class FoodLog extends DataVariant{

    public static enum MealType {
        BREAKFAST,
        LUNCH,
        SNACKS,
        DINNER
    }

    private LocalDate date;
    private MealType mealType;
    private int foodTypeIndex;
    private double weightOfFood;

    public FoodLog(ArrayList<String> data) {
        super(data);
        if (data.size() != 4) {
            throw new IllegalArgumentException("FoodType requires exactly 4 arguments.");
        }
        try {
            this.date = LocalDate.parse(data.getFirst(), Main.DATE_FORMATTER);
        } catch (Exception _) {
            throw new IllegalArgumentException("FoodLog must have a valid date.");
        }
        mealType = MealType.valueOf(data.get(1).toUpperCase());
        foodTypeIndex = Integer.parseInt(data.get(2));
        weightOfFood = Double.parseDouble(data.get(3));
    }

    public int getFoodTypeIndex() {
        return foodTypeIndex;
    }

    public double getWeightOfFood() {
        return weightOfFood;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getDataAsMessage() {
        return date.format(Main.DATE_FORMATTER) + ", " +
                mealType + ", " +
                foodTypeIndex + ", " +
                weightOfFood;
    }

    @Override
    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(date.format(Main.DATE_FORMATTER));
        data.add(String.valueOf(mealType));
        data.add(String.valueOf(foodTypeIndex));
        data.add(String.valueOf(weightOfFood));
        return data;
    }

    public static ArrayList<FoodLog> convertTableToFoodLogs(TokenTable tokenTable) {
        ArrayList<FoodLog> foodData = new ArrayList<>();

        for (ArrayList<String> tokenLine : tokenTable.tokens) {
            FoodLog foodLog = new FoodLog(tokenLine);
            foodData.add(foodLog);
        }

        return foodData;
    }

    public static TokenTable convertFoodLogsToTable(ArrayList<FoodLog> foodLogs) {
        TokenTable tokenTable = new TokenTable();

        for(FoodLog foodLog: foodLogs) {
            tokenTable.tokens.add(foodLog.getData());
        }

        return tokenTable;
    }
}
