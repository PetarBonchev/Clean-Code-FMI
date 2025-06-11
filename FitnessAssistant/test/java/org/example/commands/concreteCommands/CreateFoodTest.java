package org.example.commands.concreteCommands;

import org.example.repository.concrete.FoodRepository;
import org.example.ui.CommunicationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateFoodTest {

    private FoodRepository foodRepository;

    @BeforeEach
    void setup() {
        foodRepository = new FoodRepository(new ArrayList<>());
    }

    @Test
    void getName() {
        CreateFood createFood = new CreateFood(null, foodRepository);
        assertEquals("Create food", createFood.getName());
    }

    @Test
    void execute() {
        String input = "banana\nfruit\n1\n1\n100\n89\n22.8\n0.3\n1.1";
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommunicationChannel io = new CommunicationChannel(inputStream,
                new PrintStream(outputStream));

        CreateFood createFood = new CreateFood(io, foodRepository);
        createFood.execute();

        assertEquals("banana", foodRepository.getFoods().get(0).name());
        assertEquals(89, foodRepository.getFoods().get(0).calories());
        assertEquals(22.8, foodRepository.getFoods().get(0).carbs());
    }
}
