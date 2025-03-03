import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandFactoryTest {
    @Test
    public void testCreationDrinkWaterCommand() {
        CommandFactory commandFactory = new CommandFactory();
        DayLog dayLog = new DayLog();
        Command createdCommand = commandFactory.create("1", dayLog);
        assertInstanceOf(DrinkRegistration.class, createdCommand);
    }

    @Test
    public void testCreationCheckWaterCommand() {
        CommandFactory commandFactory = new CommandFactory();
        DayLog dayLog = new DayLog();
        Command createdCommand = commandFactory.create("2", dayLog);
        assertInstanceOf(DrinkDisplay.class, createdCommand);
    }

    @Test
    public void testCreationInvalidCommand() {
        CommandFactory commandFactory = new CommandFactory();
        DayLog dayLog = new DayLog();
        Command createdCommand = commandFactory.create("random text", dayLog);
        assertNull(createdCommand);
    }
}