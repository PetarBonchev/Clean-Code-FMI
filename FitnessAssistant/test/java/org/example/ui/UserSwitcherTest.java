package org.example.ui;

import org.example.commands.CommandContainer;
import org.example.database.LoadSaveManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class UserSwitcherTest {

    private UserSwitcher userSwitcher;

    @BeforeEach
    void setup() {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommunicationChannel io = new CommunicationChannel(inputStream,
                new PrintStream(outputStream));
        LoadSaveManager loadSaveManager = new LoadSaveManager();
        userSwitcher = new UserSwitcher(io, loadSaveManager);
    }

    @Test
    void getCurrentUser() {
        assertEquals("default", userSwitcher.getCurrentUser());
    }

    @Test
    void getCommands() {
        assertNull(userSwitcher.getCommands());
    }

    @Test
    void setCommandReader() {
        CommandContainer commandContainer = new CommandContainer();
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream("".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommunicationChannel io = new CommunicationChannel(inputStream,
                new PrintStream(outputStream));
        CommandReader commandReader = new CommandReader(io, commandContainer);
        userSwitcher.setCommandReader(commandReader);
        assertNull(userSwitcher.getCommands());
    }
}
