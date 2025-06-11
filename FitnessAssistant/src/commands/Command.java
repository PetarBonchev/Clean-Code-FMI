package org.example.commands;

import org.example.ui.CommunicationChannel;

public abstract class Command {

    private final CommunicationChannel communicationChannel;

    /**
     * Provides children with io.
     */
    protected CommunicationChannel getIO() {
        return communicationChannel;
    }

    public Command(CommunicationChannel io) {
        this.communicationChannel = io;
    }

    public abstract String getName();

    public abstract void execute();
}
