package commands;

import data.Database;
import data.TokenTable;

import java.util.ArrayList;

public class CommandExecutor {

    private final ArrayList<Command> commands;

    public CommandExecutor() {
        commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCurrentCommand() {
        Command currentCommand = commands.getFirst();
        commands.removeFirst();
        TokenTable commandData = Database.load(currentCommand.relevantDataFilename);
        TokenTable modifiedData = currentCommand.execute(commandData);
        Database.save(currentCommand.relevantDataFilename, modifiedData);
    }
}
