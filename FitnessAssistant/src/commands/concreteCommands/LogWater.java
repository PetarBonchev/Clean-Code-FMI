package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.ui.CommunicationChannel;
import org.example.database.dataTypes.LoggedWater;
import org.example.repository.concrete.LoggedWaterRepository;

import java.time.LocalDate;

public final class LogWater extends Command {
    private final LoggedWaterRepository loggedWater;

    public LogWater(CommunicationChannel io,
                    LoggedWaterRepository loggedWaterRepository) {
        super(io);
        loggedWater = loggedWaterRepository;
    }

    @Override public String getName() {
        return "Drink water";
    }

    @Override public void execute() {
        loggedWater.addLoggedWater(getLoggedWaterInput());
    }

    private LoggedWater getLoggedWaterInput() {
        getIO().writeLine("When?(YYYY-MM-DD)");
        LocalDate date = LocalDate.parse(getIO().readLine());
        getIO().writeLine("How much?(ml)");
        int amount = Integer.parseInt(getIO().readLine());
        return new LoggedWater(date, amount);
    }
}
