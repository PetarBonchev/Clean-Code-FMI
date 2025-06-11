package org.example.commands.concreteCommands;

import org.example.common.DailyMealTime;
import org.example.database.dataTypes.LoggedFood;
import org.example.database.dataTypes.LoggedWater;
import org.example.repository.concrete.LoggedFoodRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

class ViewAllLoggedTest {

    private LoggedWaterRepository waterRepository;
    private LoggedFoodRepository foodRepository;

    @BeforeEach
    void setup() {
        ArrayList<LoggedWater> waters = new ArrayList<>();
        waters.add(new LoggedWater(LocalDate.parse("2023-01-01"), 500));
        waters.add(new LoggedWater(LocalDate.parse("2022-01-01"), 500));

        waterRepository = new LoggedWaterRepository(waters);

        ArrayList<LoggedFood> foods = new ArrayList<>();
        foods.add(new LoggedFood(LocalDate.parse("2023-01-01"),
                DailyMealTime.BREAKFAST, "Oatmeal", 100, 389, 66.3, 6.9, 16.9));
        foods.add(new LoggedFood(LocalDate.parse("2023-01-01"),
                DailyMealTime.LUNCH, "Salad", 150, 120, 10.5, 5.2, 8.3));
        foods.add(new LoggedFood(LocalDate.parse("2022-01-01"),
                DailyMealTime.LUNCH, "Salad", 150, 120, 10.5, 5.2, 8.3));
        foodRepository = new LoggedFoodRepository(foods);
    }

    @Test
    void getName() {
        ViewAllLogged viewAllLogged =
                new ViewAllLogged(null, waterRepository, foodRepository);
        assertEquals("View all logged", viewAllLogged.getName());
    }

    @Test
    void execute() {
        String input = "2023-01-01";
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommunicationChannel io = new CommunicationChannel(inputStream,
                new PrintStream(outputStream));

        ViewAllLogged viewAllLogged =
                new ViewAllLogged(io, waterRepository, foodRepository);
        viewAllLogged.execute();

        String output = outputStream.toString();
        assertTrue(output.contains("BREAKFAST:"));
        assertTrue(output.contains("Oatmeal"));
        assertTrue(output.contains("LUNCH:"));
        assertTrue(output.contains("Salad"));
        assertTrue(output.contains("SNACKS:"));
        assertTrue(output.contains("---"));
        assertTrue(output.contains("DINNER:"));
        assertTrue(output.contains("---"));
        assertTrue(output.contains("Water: 0.5L"));
    }
}
