package commands;

public class CommandFactory {

    public Command createCommand(int commandIndex) {
        return switch (commandIndex) {
            case 1 -> new DrinkWaterCommand();
            case 2 -> new CheckWaterCommand();
            case 3 -> new CreateFoodCommand();
            case 4 -> new ViewFoodsCommand();
            case 5 -> new EatFoodCommand();
            case 6 -> new ViewLogFoodCommand();
            case 7 -> new ExitCommand();
            default -> throw new IllegalArgumentException("Invalid command.");
        };
    }
}
