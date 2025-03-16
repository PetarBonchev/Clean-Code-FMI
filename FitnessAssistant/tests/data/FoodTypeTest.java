package data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FoodTypeTest {

    private FoodType foodType;

    @BeforeEach
    void setup() {
        ArrayList<String> contextLine = new ArrayList<>();
        contextLine.add("Apple");
        contextLine.add("A delicious fruit.");
        contextLine.add("100.0");
        contextLine.add("1");
        contextLine.add("52.0");
        contextLine.add("95");
        contextLine.add("25.0");
        contextLine.add("0.3");
        contextLine.add("0.5");
        foodType = new FoodType(contextLine);
    }

    @Test
    void getDataAsMessage_StandardInput() {
        String expectedMessage = "Apple (100.0g; 95 kcal; 25.0g, 0.3g, 0.5g)";
        assertEquals(expectedMessage, foodType.getDataAsMessage());
    }

    @Test
    void getDataAsMessage_EmptyContextLine() {
        ArrayList<String> emptyContextLine = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new FoodType(emptyContextLine);
        });
    }

    @Test
    void getDataAsMessage_NegativeValues() {
        ArrayList<String> negativeContextLine = new ArrayList<>();
        negativeContextLine.add("Apple");
        negativeContextLine.add("A delicious fruit.");
        negativeContextLine.add("-100.0");
        negativeContextLine.add("1");
        negativeContextLine.add("-52.0");
        negativeContextLine.add("-95");
        negativeContextLine.add("-25.0");
        negativeContextLine.add("-0.3");
        negativeContextLine.add("-0.5");
        FoodType negativeFoodType = new FoodType(negativeContextLine);

        String expectedMessage = "Apple (-100.0g; -95 kcal; -25.0g, -0.3g, -0.5g)";
        assertEquals(expectedMessage, negativeFoodType.getDataAsMessage());
    }

    @Test
    void getData_StandardInput() {
        ArrayList<String> expectedData = new ArrayList<>();
        expectedData.add("Apple");
        expectedData.add("A delicious fruit.");
        expectedData.add("100.0");
        expectedData.add("1");
        expectedData.add("52.0");
        expectedData.add("95");
        expectedData.add("25.0");
        expectedData.add("0.3");
        expectedData.add("0.5");
        assertEquals(expectedData, foodType.getData());
    }

    @Test
    void getData_NegativeValues() {
        ArrayList<String> negativeContextLine = new ArrayList<>();
        negativeContextLine.add("Apple");
        negativeContextLine.add("A delicious fruit.");
        negativeContextLine.add("-100.0");
        negativeContextLine.add("1");
        negativeContextLine.add("-52.0");
        negativeContextLine.add("-95");
        negativeContextLine.add("-25.0");
        negativeContextLine.add("-0.3");
        negativeContextLine.add("-0.5");
        FoodType negativeFoodType = new FoodType(negativeContextLine);

        ArrayList<String> expectedData = new ArrayList<>();
        expectedData.add("Apple");
        expectedData.add("A delicious fruit.");
        expectedData.add("-100.0");
        expectedData.add("1");
        expectedData.add("-52.0");
        expectedData.add("-95");
        expectedData.add("-25.0");
        expectedData.add("-0.3");
        expectedData.add("-0.5");
        assertEquals(expectedData, negativeFoodType.getData());
    }

    @Test
    void convertTableToFoodTypes_StandardInput() {
        TokenTable tokenTable = new TokenTable();
        ArrayList<String> tokenLine1 = new ArrayList<>();
        tokenLine1.add("Apple");
        tokenLine1.add("A delicious fruit.");
        tokenLine1.add("100.0");
        tokenLine1.add("1");
        tokenLine1.add("52.0");
        tokenLine1.add("95");
        tokenLine1.add("25.0");
        tokenLine1.add("0.3");
        tokenLine1.add("0.5");
        tokenTable.tokens.add(tokenLine1);

        ArrayList<FoodType> foodTypes = FoodType.convertTableToFoodTypes(tokenTable);

        assertEquals(1, foodTypes.size());
        assertEquals("Apple", foodTypes.get(0).getFoodName());
        assertEquals("A delicious fruit.", foodTypes.get(0).getDescription());
        assertEquals(100.0, foodTypes.get(0).getServingSize());
        assertEquals(1, foodTypes.get(0).getServingsPerContainer());
        assertEquals(52.0, foodTypes.get(0).getAmountPerServing());
        assertEquals(95, foodTypes.get(0).getCalories());
        assertEquals(25.0, foodTypes.get(0).getCarbs());
        assertEquals(0.3, foodTypes.get(0).getFat());
        assertEquals(0.5, foodTypes.get(0).getProtein());
    }

    @Test
    void convertTableToFoodTypes_EmptyTable() {
        TokenTable tokenTable = new TokenTable();
        ArrayList<FoodType> foodTypes = FoodType.convertTableToFoodTypes(tokenTable);

        assertEquals(0, foodTypes.size());
    }

    @Test
    void convertTableToFoodTypes_MultipleEntries() {
        TokenTable tokenTable = new TokenTable();
        ArrayList<String> tokenLine1 = new ArrayList<>();
        tokenLine1.add("Apple");
        tokenLine1.add("A delicious fruit.");
        tokenLine1.add("100.0");
        tokenLine1.add("1");
        tokenLine1.add("52.0");
        tokenLine1.add("95");
        tokenLine1.add("25.0");
        tokenLine1.add("0.3");
        tokenLine1.add("0.5");
        tokenTable.tokens.add(tokenLine1);

        ArrayList<String> tokenLine2 = new ArrayList<>();
        tokenLine2.add("Banana");
        tokenLine2.add("A tropical fruit.");
        tokenLine2.add("120.0");
        tokenLine2.add("1");
        tokenLine2.add("89.0");
        tokenLine2.add("105");
        tokenLine2.add("27.0");
        tokenLine2.add("0.4");
        tokenLine2.add("1.3");
        tokenTable.tokens.add(tokenLine2);

        ArrayList<FoodType> foodTypes = FoodType.convertTableToFoodTypes(tokenTable);

        assertEquals(2, foodTypes.size());
        assertEquals("Apple", foodTypes.get(0).getFoodName());
        assertEquals("Banana", foodTypes.get(1).getFoodName());
    }

    @Test
    void convertFoodTypesToTable_StandardInput() {
        ArrayList<FoodType> foodTypes = new ArrayList<>();
        foodTypes.add(foodType);

        TokenTable tokenTable = FoodType.convertFoodTypesToTable(foodTypes);

        assertEquals(1, tokenTable.tokens.size());
        assertEquals("Apple", tokenTable.tokens.get(0).get(0));
        assertEquals("A delicious fruit.", tokenTable.tokens.get(0).get(1));
        assertEquals("100.0", tokenTable.tokens.get(0).get(2));
        assertEquals("1", tokenTable.tokens.get(0).get(3));
        assertEquals("52.0", tokenTable.tokens.get(0).get(4));
        assertEquals("95", tokenTable.tokens.get(0).get(5));
        assertEquals("25.0", tokenTable.tokens.get(0).get(6));
        assertEquals("0.3", tokenTable.tokens.get(0).get(7));
        assertEquals("0.5", tokenTable.tokens.get(0).get(8));
    }

    @Test
    void convertFoodTypesToTable_EmptyList() {
        ArrayList<FoodType> foodTypes = new ArrayList<>();
        TokenTable tokenTable = FoodType.convertFoodTypesToTable(foodTypes);

        assertEquals(0, tokenTable.tokens.size());
    }

    @Test
    void convertFoodTypesToTable_MultipleEntries() {
        ArrayList<String> contextLine2 = new ArrayList<>();
        contextLine2.add("Banana");
        contextLine2.add("A tropical fruit.");
        contextLine2.add("120.0");
        contextLine2.add("1");
        contextLine2.add("89.0");
        contextLine2.add("105");
        contextLine2.add("27.0");
        contextLine2.add("0.4");
        contextLine2.add("1.3");
        FoodType foodType2 = new FoodType(contextLine2);

        ArrayList<FoodType> foodTypes = new ArrayList<>();
        foodTypes.add(foodType);
        foodTypes.add(foodType2);

        TokenTable tokenTable = FoodType.convertFoodTypesToTable(foodTypes);

        assertEquals(2, tokenTable.tokens.size());
        assertEquals("Apple", tokenTable.tokens.get(0).get(0));
        assertEquals("Banana", tokenTable.tokens.get(1).get(0));
    }
}