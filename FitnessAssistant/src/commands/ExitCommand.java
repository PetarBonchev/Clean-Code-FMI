package commands;

import data.AvailableFilenames;
import data.TokenTable;
import general.BreakLoopException;

import java.util.ArrayList;

public class ExitCommand extends Command {
    public ExitCommand () {
        this.relevantDataFilenames = new String[] {};
    }

    @Override
    public ArrayList<TokenTable> execute(ArrayList<TokenTable> currentData) {
        throw new BreakLoopException("Program exited");
    }
}
