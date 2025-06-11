package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.common.Metric;
import org.example.database.dataTypes.Food;
import org.example.repository.concrete.FoodRepository;
import org.example.ui.CommunicationChannel;

public final class CreateFood extends Command {

    private final FoodRepository foods;

    public CreateFood(CommunicationChannel io, FoodRepository foodRepository) {
        super(io);
        foods = foodRepository;
    }

    @Override public String getName() {
        return "Create food";
    }

    @Override public void execute() {
        foods.addFood(getFoodFromUser());
    }

    private Food getFoodFromUser() {
        getIO().writeLine("Name:");
        String name = getIO().readLine();
        getIO().writeLine("Description(optional):");
        String description = getIO().readLine();
        getIO().writeLine("""
                Choose a measurement type?
                >1. Grams + servings
                >2. Milliliters + servings
                >3. Pieces + servings
                """);
        int choice = Integer.parseInt(getIO().readLine()) - 1;
        getIO().writeLine("Serving size(" + Metric.values()[choice] + "):");
        double servingSize = Double.parseDouble(getIO().readLine());
        getIO().writeLine("Servings per container:");
        double servings = Double.parseDouble(getIO().readLine());
        getIO().writeLine("Calories (kcal):");
        double calories = Double.parseDouble(getIO().readLine());
        getIO().writeLine("Carbs (" + Metric.values()[choice] + "):");
        double carbs = Double.parseDouble(getIO().readLine());
        getIO().writeLine("Fat (" + Metric.values()[choice] + "):");
        double fat = Double.parseDouble(getIO().readLine());
        getIO().writeLine("Protein (" + Metric.values()[choice] + "):");
        double protein = Double.parseDouble(getIO().readLine());

        return new Food(name, description, servingSize, Metric.values()[choice],
                servings, calories, carbs, fat, protein);
    }
}
