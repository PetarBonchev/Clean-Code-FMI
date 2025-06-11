package org.example.converters;

import org.example.database.TokenTable;
import org.example.database.dataTypes.LoggedWater;
import org.example.repository.concrete.LoggedWaterRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LoggedWaterConverterTest {

    @Test
    void toLoggedWater() {
        TokenTable table = new TokenTable();
        ArrayList<String> waterLogData = new ArrayList<>();
        waterLogData.add("1212-12-12");
        waterLogData.add("100");
        table.addTokens(waterLogData);

        LoggedWaterConverter converter = new LoggedWaterConverter();
        LoggedWaterRepository createdRepo = converter.toLoggedWater(table);
        assertEquals(100, createdRepo.getLoggedWaters().get(0).amount());
        assertEquals("1212-12-12",
                createdRepo.getLoggedWaters().get(0).date().toString());
    }

    @Test
    void toTokenTable() {
        ArrayList<LoggedWater> waters = new ArrayList<>();
        waters.add(new LoggedWater(LocalDate.parse("1212-12-12"), 100));
        LoggedWaterRepository waterRepository =
                new LoggedWaterRepository(waters);
        LoggedWaterConverter converter = new LoggedWaterConverter();
        TokenTable converted = converter.toTokenTable(waterRepository);
        assertEquals("1212-12-12", converted.getTokens().get(0).get(0));
        assertEquals("100", converted.getTokens().get(0).get(1));
    }
}
