package data;

import general.Main;
import java.time.LocalDate;
import java.util.ArrayList;

public class WaterLog extends DataVariant {
    private LocalDate date;
    public ArrayList<Integer> drunkAmounts;

    public WaterLog(ArrayList<String> contextLine) {
        super(contextLine);
        if (contextLine.isEmpty()) {
            throw new IllegalArgumentException("WaterLog must have a valid date.");
        }
        this.date = LocalDate.parse(contextLine.getFirst(), Main.DATE_FORMATTER);
        this.drunkAmounts = new ArrayList<>();
        for(int i=1;i<contextLine.size();i++) {
            drunkAmounts.add(Integer.parseInt(contextLine.get(i)));
        }
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String getDataAsMessage() {
        StringBuilder message = new StringBuilder();
        message.append(date.format(Main.DATE_FORMATTER)).append(":").append(System.lineSeparator());
        for (Integer amount : drunkAmounts) {
            message.append(amount).append("ml").append(System.lineSeparator());
        }
        return message.toString();
    }

    @Override
    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(date.format(Main.DATE_FORMATTER));
        for(Integer amount: drunkAmounts) {
            data.add(amount.toString());
        }
        return data;
    }

    public static ArrayList<WaterLog> convertTableToWaterLogs(TokenTable tokenTable) {
        ArrayList<WaterLog> waterData = new ArrayList<>();

        for (ArrayList<String> tokenLine : tokenTable.tokens) {
            WaterLog waterLog = new WaterLog(tokenLine);
            waterData.add(waterLog);
        }

        return waterData;
    }

    public static TokenTable convertWaterLogsToTable(ArrayList<WaterLog> waterLogs) {
        TokenTable tokenTable = new TokenTable();

        for(WaterLog waterLog: waterLogs) {
            tokenTable.tokens.add(waterLog.getData());
        }

        return tokenTable;
    }
}