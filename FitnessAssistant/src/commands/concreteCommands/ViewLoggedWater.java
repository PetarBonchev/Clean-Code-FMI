package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.ui.CommunicationChannel;
import org.example.repository.concrete.LoggedWaterRepository;

import java.time.LocalDate;

public final class ViewLoggedWater extends Command {
    private final LoggedWaterRepository loggedWater;

    public ViewLoggedWater(CommunicationChannel io,
                           LoggedWaterRepository loggedWaterRepository) {
        super(io);
        loggedWater = loggedWaterRepository;
    }

    @Override public String getName() {
        return "Check water";
    }

    @Override
    public void execute() {
        LocalDate date = getDateInput();
        getIO().writeLine(date.toString() + ":");
        loggedWater.getLoggedWaters().forEach(water -> {
            if (water.date().equals(date)) {
                getIO().writeLine(water.toString());
            }
        });
    }

    private LocalDate getDateInput() {
        getIO().writeLine("When?(YYYY-MM-DD)");
        return LocalDate.parse(getIO().readLine());
    }
}
