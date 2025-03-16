package general;

import commands.Command;
import commands.CommandExecutor;
import commands.CommandFactory;
import data.*;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        CommandFactory commandFactory = new CommandFactory();
        CommandExecutor commandExecutor = new CommandExecutor();

        while (true) {
            try {
                AvailableCommands.getInstance().getCommandMap().forEach(
                        (index, name) -> System.out.println(index + "." + name)
                );
                Command command = commandFactory.createCommand(getUserCommand());
                commandExecutor.addCommand(command);
                commandExecutor.executeCurrentCommand();
            } catch (BreakLoopException _) {
                System.out.println("Program exited.");
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Integer getUserCommand() {
        Scanner scanner = new Scanner(System.in);
        String userResponse = scanner.nextLine();
        try {
            return Integer.parseInt(userResponse);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid command.");
        }
    }
}
