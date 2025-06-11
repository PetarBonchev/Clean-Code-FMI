package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.ui.CommunicationChannel;
import org.example.ui.UserSwitcher;

public final class ChangeUser extends Command {

    private final UserSwitcher switcher;

    public ChangeUser(CommunicationChannel io, UserSwitcher userSwitcher) {
        super(io);
        switcher = userSwitcher;
    }

    @Override public String getName() {
        return "Change user";
    }

    @Override public void execute() {
        getIO().writeLine("Who?");
        String username = getIO().readLine();
        switcher.setCurrentUser(username);
    }
}
