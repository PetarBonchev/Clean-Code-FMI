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
            String userCommand = this.scanner.nextLine();
            DayLog details = this.userInput.getDetails(userCommand);
            Command currentCommand = this.commandFactory.create(userCommand, details);
            currentCommand.execute();
        }
    }
}
