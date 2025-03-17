package commands;

import data.AvailableFilenames;
import data.FoodType;
import data.TokenTable;

import java.util.ArrayList;

public class ViewFoodsCommand extends Command{

    public ViewFoodsCommand() {
        this.relevantDataFilenames = new String[] {AvailableFilenames.foodTypeData};
    }

    @Override
    public ArrayList<TokenTable> execute(ArrayList<TokenTable> currentData) {
        ArrayList<FoodType> currentFoods = FoodType.convertTableToFoodTypes(currentData.getFirst());
        for(int i=0;i<currentFoods.size();i++) {
            System.out.println(String.valueOf(i + 1) + ": " + currentFoods.get(i).getDataAsMessage());
        }
        return currentData;
    }
}
