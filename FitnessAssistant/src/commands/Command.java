package commands;

import data.TokenTable;

import java.util.ArrayList;

public abstract class Command {
    public String[] relevantDataFilenames;

    public abstract ArrayList<TokenTable> execute(ArrayList<TokenTable> currentData);
}
