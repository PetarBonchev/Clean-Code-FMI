package org.example.commands;

import org.example.commands.concreteCommands.LogWater;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandContainerTest {

    @Test
    void getByIndex() {
        CommandContainer commandContainer = new CommandContainer();
        LogWater command = new LogWater(null, null);
        commandContainer.add(command);
        Command fromContainer = commandContainer.getByIndex(0);
        assertEquals(command, fromContainer);
    }

    @Test
    void getCommandNamesIndexed() {
        CommandContainer commandContainer = new CommandContainer();
        LogWater command = new LogWater(null, null);
        commandContainer.add(command);
        commandContainer.add(command);
        String commandsList = commandContainer.getCommandNamesIndexed();
        assertEquals("1. Drink water\n2. Drink water", commandsList);
    }
}
