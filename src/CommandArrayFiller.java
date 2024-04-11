import cli.Commands.*;

import java.util.HashMap;

public class CommandArrayFiller {
    static HashMap<String,Command> commandList;
    public static HashMap<String,Command> setBasicCommands()
    {
        commandList = new HashMap<>();
        addCommand(new Add());
        addCommand(new Clear());
        addCommand(new CountGreaterThanCapital());
        addCommand(new ExecuteScript());
        addCommand(new Exit());
        addCommand(new Help());
        addCommand(new Info());
        addCommand(new RemoveAllByCarCode());
        addCommand(new RemoveById());
        addCommand(new RemoveFirst());
        addCommand(new RemoveGreater());
        addCommand(new RemoveLower());
        addCommand(new Save());
        addCommand(new Show());
        addCommand(new SumOfCarCode());
        addCommand(new Update());
        addCommand(new Write());
        return commandList;
    }
    public static void addCommand(Command command)
    {
        commandList.put(command.getName(), command);
    }
}
