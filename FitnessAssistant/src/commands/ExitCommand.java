package commands;

import data.TokenTable;
import general.BreakLoopException;

public class ExitCommand extends Command {

    @Override
    public TokenTable execute(TokenTable currentData) {
        throw new BreakLoopException("Program exited");
    }
}
