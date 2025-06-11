package org.example.repository.concrete;

import org.example.database.dataTypes.LoggedWater;
import org.example.repository.NameableRepository;

import java.util.ArrayList;

public final class LoggedWaterRepository implements NameableRepository {
    private final ArrayList<LoggedWater> loggedWaters;

    public LoggedWaterRepository(ArrayList<LoggedWater> waters) {
        loggedWaters = waters;
    }

    public ArrayList<LoggedWater> getLoggedWaters() {
        return loggedWaters;
    }

    public void addLoggedWater(LoggedWater water) {
        loggedWaters.add(water);
    }

    @Override public String getName() {
        return "logged water";
    }
}
