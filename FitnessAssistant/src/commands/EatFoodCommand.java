package commands;

import data.AvailableFilenames;
import data.FoodLog;
import data.FoodType;
import data.TokenTable;

import java.util.ArrayList;
import java.util.Scanner;

public class EatFoodCommand extends Command{

    public EatFoodCommand () {
        relevantDataFilenames = new String[] {AvailableFilenames.foodTypeData, AvailableFilenames.foodLogs};
    }

    @Override
    public ArrayList<TokenTable> execute(ArrayList<TokenTable> currentData) {
        ArrayList<FoodType> foodTypes = FoodType.convertTableToFoodTypes(currentData.getFirst());
        FoodLog userLog = getUserFoodLog(foodTypes);
        ArrayList<FoodLog> foodLogs = FoodLog.convertTableToFoodLogs(currentData.get(1));
        foodLogs.add(userLog);
        System.out.println(foodTypes.get(userLog.getFoodTypeIndex()).getMessageWithCustomWeight(userLog.getWeightOfFood()));
        ArrayList<TokenTable> modifiedData = new ArrayList<>();
        modifiedData.add(FoodType.convertFoodTypesToTable(foodTypes));
        modifiedData.add(FoodLog.convertFoodLogsToTable(foodLogs));
        return modifiedData;
    }

    private FoodLog getUserFoodLog(ArrayList<FoodType> availableFoodTypes) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> userInput = new ArrayList<>();
        System.out.println("When (date):");
        userInput.add(scanner.nextLine());
        System.out.println("When (meal):");
        userInput.add(scanner.nextLine());
        System.out.println("Which food (food id):");
        for(int i=0;i<availableFoodTypes.size();i++) {
            System.out.print(i + 1 + ".");
            System.out.println(availableFoodTypes.get(i).getDataAsMessage());
        }
        userInput.add(String.valueOf(Integer.parseInt(scanner.nextLine()) - 1));
        System.out.println("Serving size:");
        userInput.add(scanner.nextLine());
        return new FoodLog(userInput);
    }
}
