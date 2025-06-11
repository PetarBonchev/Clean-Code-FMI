package org.example.converters;

import org.example.database.TokenTable;
import org.example.database.dataTypes.LoggedWater;
import org.example.repository.concrete.LoggedWaterRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public final class LoggedWaterConverter {

    public LoggedWaterRepository toLoggedWater(TokenTable data) {
        ArrayList<LoggedWater> waters = new ArrayList<>();
        data.getTokens().forEach(line -> waters.add(
                new LoggedWater(LocalDate.parse(line.get(0)),
                        Integer.parseInt(line.get(1)))));
        return new LoggedWaterRepository(waters);
    }

    public TokenTable toTokenTable(LoggedWaterRepository data) {
        TokenTable tokenTable = new TokenTable();
        data.getLoggedWaters().forEach(water -> tokenTable.addTokens(
                new ArrayList<>(Arrays.asList(
                        water.date().toString(),
                        Integer.toString(water.amount())
                ))
        ));
        return tokenTable;
    }
}
