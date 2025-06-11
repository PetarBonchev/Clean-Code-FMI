package org.example.ui;

import org.example.commands.Command;
import org.example.common.BreakLoopException;
import org.example.database.LoadSaveManager;

public final class ApplicationRunner {

    private final CommunicationChannel io;
    private final UserSwitcher userSwitcher;
    private CommandReader commandReader;

    public ApplicationRunner(CommunicationChannel communicationChannel,
                             LoadSaveManager loadSaveManager) {
        io = communicationChannel;
        userSwitcher = new UserSwitcher(communicationChannel, loadSaveManager);
    }

    public void run() {
        initialize();
        runLoop();
        userSwitcher.save();
    }

    private void initialize() {
        userSwitcher.load();
        commandReader = new CommandReader(io, userSwitcher.getCommands());
        userSwitcher.setCommandReader(commandReader);
    }

    private void runLoop() {
        while (true) {
            try {
                Command selectedCommand = commandReader.getFromUser();
                selectedCommand.execute();
            } catch (BreakLoopException e) {
                io.writeLine(e.getMessage());
                break;
            } catch (Exception e) {
                io.writeLine("Error! " + e.getMessage() + "\nTry again.\n");
            }
        }
    }
}
