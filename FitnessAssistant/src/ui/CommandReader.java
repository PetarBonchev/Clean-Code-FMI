package org.example.ui;

import org.example.commands.Command;
import org.example.commands.CommandContainer;

public final class CommandReader {

    private final CommunicationChannel io;
    private CommandContainer commands;

    public CommandReader(CommunicationChannel communicationChannel,
                         CommandContainer commandContainer) {
        io = communicationChannel;
        commands = commandContainer;
    }

    public Command getFromUser() {
        io.writeLine("");
        io.writeLine(commands.getCommandNamesIndexed());
        int index = Integer.parseInt(io.readLine()) - 1;
        return commands.getByIndex(index);
    }

    public void setCommands(CommandContainer newCommands) {
        commands = newCommands;
    }
}
