package commands;

import data.AvailableFilenames;
import data.FoodType;
import data.TokenTable;

import java.util.ArrayList;
import java.util.Scanner;

public class CreateFoodCommand extends Command{

    public CreateFoodCommand() {
        relevantDataFilename = AvailableFilenames.foodTypeData;
    }

    @Override
    public TokenTable execute(TokenTable currentData) {
        ArrayList<FoodType> foodData = FoodType.convertTableToFoodTypes(currentData);
        FoodType newFoodType = getUserInput();
        foodData.add(newFoodType);
        return FoodType.convertFoodTypesToTable(foodData);
    }

    private FoodType getUserInput() {
        ArrayList<String> userInput = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name:");
        userInput.add(scanner.nextLine());
        System.out.println("Description (optional):");
        userInput.add(scanner.nextLine());
        System.out.println("Serving Size (g):");
        userInput.add(scanner.nextLine());
        System.out.println("Servings per container:");
        userInput.add(scanner.nextLine());
        System.out.println("Amount per serving:");
        userInput.add(scanner.nextLine());
        System.out.println("Calories (kcal):");
        userInput.add(scanner.nextLine());
        System.out.println("Carbs (g):");
        userInput.add(scanner.nextLine());
        System.out.println("Fat (g):");
        userInput.add(scanner.nextLine());
        System.out.println("Protein (g):");
        userInput.add(scanner.nextLine());
        return new FoodType(userInput);
    }
}
