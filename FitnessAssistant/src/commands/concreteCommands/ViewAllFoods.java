package org.example.commands.concreteCommands;

import org.example.commands.Command;
import org.example.repository.concrete.FoodRepository;
import org.example.ui.CommunicationChannel;

public final class ViewAllFoods extends Command {

    private final FoodRepository foods;

    public ViewAllFoods(CommunicationChannel io,
                        FoodRepository foodRepository) {
        super(io);
        foods = foodRepository;
    }

    @Override public String getName() {
        return "View all foods";
    }

    @Override public void execute() {
        for (int i = 0; i < foods.getFoods().size(); i++) {
            getIO().writeLine(
                    (i + 1) + "." + foods.getFoods().get(i).toString());
        }
    }
}
