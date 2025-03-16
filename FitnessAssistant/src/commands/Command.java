package commands;

import data.TokenTable;

public abstract class Command {
    public String relevantDataFilename = "";

    public abstract TokenTable execute(TokenTable currentData);
}
