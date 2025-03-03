import java.util.Scanner;

public class CommandReceiver {
    private final UserInputGatherer userInput;
    private final CommandFactory commandFactory;
    private final Scanner scanner;

    CommandReceiver(UserInputGatherer userInputGatherer, CommandFactory commandFactory) {
        this.userInput = userInputGatherer;
        this.commandFactory = commandFactory;
        scanner = new Scanner(System.in);
    }

    public void executeUserCommands() {
        while (true) {
            displayCommandOptions();
            String userCommand = this.scanner.nextLine();
            DayLog details = this.userInput.getDetails(userCommand);
            Command currentCommand = this.commandFactory.create(userCommand, details);
            if(currentCommand != null) {
                currentCommand.execute();
            }
        }
    }

    private void displayCommandOptions() {
        System.out.println("1.Drink water");
        System.out.println("2.Check water");
    }
}
