package org.example.commands;

import org.example.repository.RepositoryContainer;
import org.example.repository.concrete.LoggedWaterRepository;
import org.example.ui.CommunicationChannel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;


class CommandFactoryTest {

    @Test
    void createCommands() {
        CommunicationChannel io =
                new CommunicationChannel(System.in, System.out);
        RepositoryContainer repo = new RepositoryContainer();
        LoggedWaterRepository waterRepository = new LoggedWaterRepository(null);
        repo.add(waterRepository);

        CommandFactory commandFactory = new CommandFactory(io, repo);
        CommandContainer output = commandFactory.createCommands();

        assertInstanceOf(Command.class, output.getByIndex(0));
    }
}
