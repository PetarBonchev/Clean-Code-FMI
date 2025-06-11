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


class LogWaterTest {

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
        LogWater logWater = new LogWater(null, repository);
        assertEquals("Drink water", logWater.getName());
    }

    @Test
    void execute() {
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream("1212-12-12\n100".getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        CommunicationChannel io =
                new CommunicationChannel(inputStream, printStream);

        LogWater logWater = new LogWater(io, repository);
        logWater.execute();
        assertEquals(repository.getLoggedWaters().get(1).date(),
                LocalDate.parse("1212-12-12"));
        assertEquals(100, repository.getLoggedWaters().get(1).amount());
    }

}
