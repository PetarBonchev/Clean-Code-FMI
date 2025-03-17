package commands;

import data.AvailableFilenames;
import data.FoodType;
import data.TokenTable;

import java.util.ArrayList;
import java.util.Scanner;

public class CreateFoodCommand extends Command{

    public CreateFoodCommand() {
        this.relevantDataFilenames = new String[] {AvailableFilenames.foodTypeData};
    }

    @Override
    public ArrayList<TokenTable> execute(ArrayList<TokenTable> currentData) {
        ArrayList<FoodType> foodData = FoodType.convertTableToFoodTypes(currentData.getFirst());
        FoodType newFoodType = getUserInput();
        foodData.add(newFoodType);
        ArrayList<TokenTable> modifiedTables = new ArrayList<>();
        modifiedTables.add(FoodType.convertFoodTypesToTable(foodData));
        return modifiedTables;
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
