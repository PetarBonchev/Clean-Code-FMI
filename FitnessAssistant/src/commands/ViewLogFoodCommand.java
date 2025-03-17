package commands;

import data.AvailableFilenames;
import data.FoodLog;
import data.FoodType;
import data.TokenTable;
import general.Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewLogFoodCommand extends Command{

    public ViewLogFoodCommand() {
        relevantDataFilenames = new String[] {AvailableFilenames.foodTypeData, AvailableFilenames.foodLogs};
    }

    @Override
    public ArrayList<TokenTable> execute(ArrayList<TokenTable> currentData) {
        ArrayList<FoodType> foodTypes = FoodType.convertTableToFoodTypes(currentData.getFirst());
        ArrayList<FoodLog> foodLogs = FoodLog.convertTableToFoodLogs(currentData.get(1));
        System.out.println("When (date):");
        LocalDate userDate = LocalDate.parse(new Scanner(System.in).nextLine(), Main.DATE_FORMATTER);
        for(FoodLog foodLog : foodLogs) {
            if(foodLog.getDate().equals(userDate)) {
                System.out.println(foodTypes.get(foodLog.getFoodTypeIndex()).getMessageWithCustomWeight(foodLog.getWeightOfFood()));
            }
        }
        return currentData;
    }
}
