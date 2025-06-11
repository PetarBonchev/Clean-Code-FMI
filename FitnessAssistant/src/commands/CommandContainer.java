package org.example.commands;

import java.util.ArrayList;

public final class CommandContainer {
    private final ArrayList<Command> commands = new ArrayList<>();

    public void add(Command command) {
        commands.add(command);
    }

    public Command getByIndex(int index) {
        return commands.get(index);
    }

    public String getCommandNamesIndexed() {
        StringBuilder commandText = new StringBuilder();
        for (int i = 0; i < commands.size(); i++) {
            commandText.append(i + 1).append(". ")
                    .append(commands.get(i).getName());
            if (i < commands.size() - 1) {
                commandText.append("\n");
            }
        }
        return commandText.toString();
    }
}
