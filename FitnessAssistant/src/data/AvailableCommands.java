package data;

import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

public class AvailableCommands {
    private static class SingletonHelper {
        private static final AvailableCommands INSTANCE = new AvailableCommands();
    }

    private final Map<Integer, String> commandMap;

    private AvailableCommands() {
        commandMap = new HashMap<>();
        TokenTable tokensForCommands = Database.load(AvailableFilenames.availableCommands);
        for (ArrayList<String> lineTokens : tokensForCommands.tokens) {
            commandMap.put(Integer.parseInt(lineTokens.getFirst()), lineTokens.get(1));
        }
    }

    public static AvailableCommands getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public Map<Integer, String> getCommandMap() {
        return commandMap;
    }

    public Integer getIndexByCommand(String commandName) {
        for (Map.Entry<Integer, String> entry : commandMap.entrySet()) {
            if (entry.getValue().equals(commandName)) {
                return entry.getKey();
            }
        }
        return null;
    }

}


