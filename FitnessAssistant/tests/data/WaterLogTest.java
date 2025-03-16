package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WaterLogTest {

    private WaterLog waterLog;

    @BeforeEach
    void setup() {
        ArrayList<String> contextLine = new ArrayList<>();
        contextLine.add("01/10/2023");
        contextLine.add("500");
        contextLine.add("300");
        waterLog = new WaterLog(contextLine);
    }

    @Test
    void getDataAsMessage_StandardInput() {
        String expectedMessage = "01/10/2023:" + System.lineSeparator() +
                "500ml" + System.lineSeparator() +
                "300ml" + System.lineSeparator();
        assertEquals(expectedMessage, waterLog.getDataAsMessage());
    }

    @Test
    void getDataAsMessage_EmptyContextLine() {
        ArrayList<String> emptyContextLine = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new WaterLog(emptyContextLine);
        });
    }

    @Test
    void getDataAsMessage_NegativeDrunkAmounts() {
        ArrayList<String> negativeContextLine = new ArrayList<>();
        negativeContextLine.add("01/10/2023");
        negativeContextLine.add("-500");
        negativeContextLine.add("-300");
        WaterLog negativeWaterLog = new WaterLog(negativeContextLine);

        String expectedMessage = "01/10/2023:" + System.lineSeparator() +
                "-500ml" + System.lineSeparator() +
                "-300ml" + System.lineSeparator();
        assertEquals(expectedMessage, negativeWaterLog.getDataAsMessage());
    }

    @Test
    void getDataAsTokenLine_StandardInput() {
        String expectedTokenLine = "10:01/10/2023 3:500 3:300 ";
        assertEquals(expectedTokenLine, waterLog.getDataAsTokenLine());
    }

    @Test
    void getDataAsTokenLine_EmptyDrunkAmounts() {
        ArrayList<String> emptyWaterLogDate = new ArrayList<>();
        emptyWaterLogDate.add("01/10/2023");
        WaterLog emptyWaterLog = new WaterLog(emptyWaterLogDate);
        String expectedTokenLine = "10:01/10/2023 ";
        assertEquals(expectedTokenLine, emptyWaterLog.getDataAsTokenLine());
    }

    @Test
    void getDataAsTokenLine_NegativeDrunkAmounts() {
        ArrayList<String> negativeContextLine = new ArrayList<>();
        negativeContextLine.add("01/10/2023");
        negativeContextLine.add("-500");
        negativeContextLine.add("-300");
        WaterLog negativeWaterLog = new WaterLog(negativeContextLine);

        String expectedTokenLine = "10:01/10/2023 4:-500 4:-300 ";
        assertEquals(expectedTokenLine, negativeWaterLog.getDataAsTokenLine());
    }

    @Test
    void getData_StandardInput() {
        ArrayList<String> expectedData = new ArrayList<>();
        expectedData.add("01/10/2023");
        expectedData.add("500");
        expectedData.add("300");
        assertEquals(expectedData, waterLog.getData());
    }

    @Test
    void getData_NegativeDrunkAmounts() {
        ArrayList<String> negativeContextLine = new ArrayList<>();
        negativeContextLine.add("01/10/2023");
        negativeContextLine.add("-500");
        negativeContextLine.add("-300");
        WaterLog negativeWaterLog = new WaterLog(negativeContextLine);

        ArrayList<String> expectedData = new ArrayList<>();
        expectedData.add("01/10/2023");
        expectedData.add("-500");
        expectedData.add("-300");
        assertEquals(expectedData, negativeWaterLog.getData());
    }

    @Test
    void convertTableToWaterLogs_StandardInput() {
        TokenTable tokenTable = new TokenTable();
        ArrayList<String> tokenLine1 = new ArrayList<>();
        tokenLine1.add("01/10/2023");
        tokenLine1.add("500");
        tokenLine1.add("300");
        tokenTable.tokens.add(tokenLine1);

        ArrayList<WaterLog> waterLogs = WaterLog.convertTableToWaterLogs(tokenTable);

        assertEquals(1, waterLogs.size());
        assertEquals(LocalDate.parse("01/10/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")), waterLogs.get(0).getDate());
        assertEquals(500, waterLogs.get(0).drunkAmounts.get(0));
        assertEquals(300, waterLogs.get(0).drunkAmounts.get(1));
    }

    @Test
    void convertTableToWaterLogs_EmptyTable() {
        TokenTable tokenTable = new TokenTable();
        ArrayList<WaterLog> waterLogs = WaterLog.convertTableToWaterLogs(tokenTable);

        assertEquals(0, waterLogs.size());
    }

    @Test
    void convertTableToWaterLogs_MultipleEntries() {
        TokenTable tokenTable = new TokenTable();
        ArrayList<String> tokenLine1 = new ArrayList<>();
        tokenLine1.add("01/10/2023");
        tokenLine1.add("500");
        tokenLine1.add("300");
        tokenTable.tokens.add(tokenLine1);

        ArrayList<String> tokenLine2 = new ArrayList<>();
        tokenLine2.add("02/10/2023");
        tokenLine2.add("700");
        tokenLine2.add("200");
        tokenTable.tokens.add(tokenLine2);

        ArrayList<WaterLog> waterLogs = WaterLog.convertTableToWaterLogs(tokenTable);

        assertEquals(2, waterLogs.size());
        assertEquals(LocalDate.parse("01/10/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")), waterLogs.get(0).getDate());
        assertEquals(LocalDate.parse("02/10/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")), waterLogs.get(1).getDate());
    }

    @Test
    void convertWaterLogsToTable_StandardInput() {
        ArrayList<WaterLog> waterLogs = new ArrayList<>();
        waterLogs.add(waterLog);

        TokenTable tokenTable = WaterLog.convertWaterLogsToTable(waterLogs);

        assertEquals(1, tokenTable.tokens.size());
        assertEquals("01/10/2023", tokenTable.tokens.get(0).get(0));
        assertEquals("500", tokenTable.tokens.get(0).get(1));
        assertEquals("300", tokenTable.tokens.get(0).get(2));
    }

    @Test
    void convertWaterLogsToTable_EmptyList() {
        ArrayList<WaterLog> waterLogs = new ArrayList<>();
        TokenTable tokenTable = WaterLog.convertWaterLogsToTable(waterLogs);

        assertEquals(0, tokenTable.tokens.size());
    }

    @Test
    void convertWaterLogsToTable_MultipleEntries() {
        ArrayList<String> contextLine2 = new ArrayList<>();
        contextLine2.add("02/10/2023");
        contextLine2.add("700");
        contextLine2.add("200");
        WaterLog waterLog2 = new WaterLog(contextLine2);

        ArrayList<WaterLog> waterLogs = new ArrayList<>();
        waterLogs.add(waterLog);
        waterLogs.add(waterLog2);

        TokenTable tokenTable = WaterLog.convertWaterLogsToTable(waterLogs);

        assertEquals(2, tokenTable.tokens.size());
        assertEquals("01/10/2023", tokenTable.tokens.get(0).get(0));
        assertEquals("02/10/2023", tokenTable.tokens.get(1).get(0));
    }
}