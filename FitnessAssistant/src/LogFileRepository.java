import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class LogFileRepository {

    public DayLog getDayLog(String filename, String date) {
        List<String> lines = readLinesFromFile(filename);
        List<DayLog> logData = parseLinesToDayLogData(lines);
        int dateIndex = getIndexOfDayLogByDate(logData, date);
        if(dateIndex == -1) {
            DayLog dayLog = new DayLog();
            dayLog.date = date;
            return dayLog;
        }
        return logData.get(dateIndex);
    }

    public void saveLog(String filename, DayLog dayLog) {
        List<String> lines = readLinesFromFile(filename);
        List<DayLog> logData = parseLinesToDayLogData(lines);
        int dateIndex = getIndexOfDayLogByDate(logData, dayLog.date);
        if(dateIndex == -1) {
            logData.add(dayLog);
        }
        else {
            logData.get(dateIndex).addData(dayLog.data);
        }
        List<String> modifiedLines = parseLogDataToLines(logData);
        writeLinesToFile(filename, modifiedLines);
    }

    private List<String> readLinesFromFile(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException _) {

        }
        return lines;
    }

    private List<DayLog> parseLinesToDayLogData(List<String> lines) {
        List<DayLog> parsedLines = new ArrayList<>();
        for (String line : lines) {
            DayLog dayLog = DayLog.convertStringToDayLog(line);
            parsedLines.add(dayLog);
        }
        return parsedLines;
    }

    private int getIndexOfDayLogByDate(List<DayLog> dayLogs, String date) {
        for(int i = 0; i <dayLogs.size(); i++) {
            if(dayLogs.get(i).date.equals(date)) {
                return i;
            }
        }
        return -1;
    }

    private List<String> parseLogDataToLines(List<DayLog> dayLogs) {
        List<String> parsedLines = new ArrayList<>();
        for (DayLog dayLog : dayLogs) {
            String line = dayLog.makeStringRepresentation();
            parsedLines.add(line);
        }
        return parsedLines;
    }

    private void writeLinesToFile(String filename, List<String> lines) {
        try {
            Files.write(Paths.get(filename), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException _) {

        }
    }

}
