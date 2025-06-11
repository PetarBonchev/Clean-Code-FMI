package org.example.commands.concreteCommands;

import org.example.database.dataTypes.LoggedWater;
import org.example.repository.concrete.LoggedWaterRepository;
import org.example.ui.CommunicationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ViewLoggedWaterTest {

    private LoggedWaterRepository repository;

    @BeforeEach
    void setup() {
        ArrayList<LoggedWater> waters = new ArrayList<>();
        waters.add(new LoggedWater(LocalDate.parse("1212-12-12"),
                1000));
        repository = new LoggedWaterRepository(waters);
    }

    @Test
    void getName() {
        ViewLoggedWater viewLoggedWater = new ViewLoggedWater(null,
                repository);
        assertEquals("Check water", viewLoggedWater.getName());
    }

    @Test
    void execute() {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream("1212-12-12".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        CommunicationChannel io =
                new CommunicationChannel(inputStream, printStream);

        ViewLoggedWater viewLoggedWater = new ViewLoggedWater(io, repository);
        viewLoggedWater.execute();
        assertEquals("When?(YYYY-MM-DD)\n1212-12-12:\n1000ml",
                outputStream.toString().trim().replace("\r\n", "\n"));
    }

    @Test
    void executeNoDateMatch() {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream("1000-12-12".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        CommunicationChannel io =
                new CommunicationChannel(inputStream, printStream);

        ViewLoggedWater viewLoggedWater = new ViewLoggedWater(io, repository);
        viewLoggedWater.execute();
        assertEquals("When?(YYYY-MM-DD)\n1000-12-12:",
                outputStream.toString().trim().replace("\r\n", "\n"));
    }
}
