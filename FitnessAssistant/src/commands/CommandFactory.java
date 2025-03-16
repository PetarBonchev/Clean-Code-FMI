package commands;

public class CommandFactory {

    public Command createCommand(int commandIndex) {
        return switch (commandIndex) {
            case 1 -> new DrinkWaterCommand();
            case 2 -> new CheckWaterCommand();
            case 3 -> new ExitCommand();
            default -> throw new IllegalArgumentException("Invalid command.");
        };
    }
}
