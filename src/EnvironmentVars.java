import cli.Commands.Command;
import storage.Storage;

import java.util.HashMap;

public class EnvironmentVars {
    private Storage storage;
    private HashMap<String, Command> commandArray;

    public Storage getStorage() {
        return storage;
    }

    public HashMap<String, Command> getCommandArray() {
        return commandArray;
    }
}
