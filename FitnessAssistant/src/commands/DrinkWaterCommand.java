package commands;

import data.AvailableFilenames;
import data.TokenTable;
import data.WaterLog;

import java.util.ArrayList;
import java.util.Scanner;

public class DrinkWaterCommand extends Command {

    public DrinkWaterCommand() {
        this.relevantDataFilenames = new String[] {AvailableFilenames.waterData};
    }

    @Override
    public ArrayList<TokenTable> execute(ArrayList<TokenTable> currentData) {
        ArrayList<WaterLog> waterData = WaterLog.convertTableToWaterLogs(currentData.getFirst());
        WaterLog newLog = getUserInput();
        ArrayList<WaterLog> modifiedWaterData = modifyWaterData(waterData, newLog);
        ArrayList<TokenTable> modifiedTables = new ArrayList<>();
        modifiedTables.add(WaterLog.convertWaterLogsToTable(modifiedWaterData));
        return modifiedTables;
    }

    private WaterLog getUserInput() {
        ArrayList<String> userInput = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("When?");
        userInput.add(scanner.nextLine());
        System.out.println("How much?");
        userInput.add(scanner.nextLine());
        return new WaterLog(userInput);
    }

    private ArrayList<WaterLog> modifyWaterData(ArrayList<WaterLog> currentWaterData, WaterLog newLog) {
        boolean foundDate = false;
        for (WaterLog waterLog : currentWaterData) {
            if (newLog.getDate().equals(waterLog.getDate())) {
                waterLog.drunkAmounts.addAll(newLog.drunkAmounts);
                foundDate = true;
            }
        }
        if (!foundDate) {
            currentWaterData.add(newLog);
        }
        return currentWaterData;
    }
}
