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
        ArrayList<TokenTable> commandData = new ArrayList<>();
        for(String filename : currentCommand.relevantDataFilenames) {
            commandData.add(Database.load(filename));
        }
        ArrayList<TokenTable> modifiedData = currentCommand.execute(commandData);
        for (int i = 0; i < modifiedData.size(); i++) {
            Database.save(currentCommand.relevantDataFilenames[i], modifiedData.get(i));
        }
    }
}
