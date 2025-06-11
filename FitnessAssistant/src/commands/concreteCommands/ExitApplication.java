package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.common.BreakLoopException;
import org.example.ui.CommunicationChannel;

public final class ExitApplication extends Command {

    public ExitApplication(CommunicationChannel io) {
        super(io);
    }

    @Override public String getName() {
        return "Exit";
    }

    @Override public void execute() {
        throw new BreakLoopException("Program exited");
    }
}
