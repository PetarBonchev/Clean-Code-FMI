package commands;

import data.AvailableFilenames;
import data.TokenTable;
import data.WaterLog;
import general.Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CheckWaterCommand extends Command{

    public CheckWaterCommand() {
        this.relevantDataFilename = AvailableFilenames.waterData;
    }

    @Override
    public TokenTable execute(TokenTable currentData) {
        ArrayList<WaterLog> waterData = WaterLog.convertTableToWaterLogs(currentData);
        LocalDate userDate = getUserDate();
        int desiredWaterDataIndex = getDesiredWaterDataIndex(waterData, userDate);
        if(desiredWaterDataIndex == -1) {
            System.out.println("No drinks recorded on this date.");
        }
        else {
            System.out.println(waterData.get(desiredWaterDataIndex).getDataAsMessage());
        }
        return currentData;
    }

    private int getDesiredWaterDataIndex(ArrayList<WaterLog> waterData, LocalDate userDate) {
        for(int i=0;i<waterData.size();i++) {
            if(waterData.get(i).getDate().equals(userDate)) {
                return i;
            }
        }
        return -1;
    }

    private LocalDate getUserDate() {
        System.out.println("When?");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        return LocalDate.parse(response, Main.DATE_FORMATTER);
    }
}
