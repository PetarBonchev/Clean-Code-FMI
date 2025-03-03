import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DayLogTest {

    @Test
    public void testAddData() {
        DayLog dayLogReceiver = new DayLog();
        dayLogReceiver.data.add("1");
        DayLog dayLogDataGiver = new DayLog();
        dayLogDataGiver.data.add("12");
        dayLogReceiver.addData(dayLogDataGiver.data);
        ArrayList<String> expectedData = new ArrayList<>();
        expectedData.add("1");
        expectedData.add("12");
        assertEquals(dayLogReceiver.data, expectedData);
    }

    @Test
    public void testMakeStringRepresentation() {
        DayLog dayLog = new DayLog();
        dayLog.date = "12/12/2112";
        dayLog.data.add("1");
        dayLog.data.add("12");
        String dayLogRepresentation = dayLog.makeStringRepresentation();
        assertEquals("12/12/2112 1 12", dayLogRepresentation);
    }

    @Test
    public void testConvertStringToDayLog() {
        DayLog dayLog = new DayLog();
        dayLog.date = "12/12/2112";
        dayLog.data.add("1");
        dayLog.data.add("12");
        DayLog convertedDayLog = DayLog.convertStringToDayLog("12/12/2112 1 12");
        assertEquals(dayLog, convertedDayLog);
    }
}