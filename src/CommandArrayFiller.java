import cli.Commands.*;

import java.util.HashMap;

public class CommandArrayFiller {
    public static void addBasicCommands(CommandExecuter commandExecuter)
    {

        commandExecuter.setCommandArray(new HashMap<>());
        commandExecuter.addCommand(new Add());
        commandExecuter.addCommand(new Clear());
        commandExecuter.addCommand(new CountGreaterThanCapital());
        commandExecuter.addCommand(new ExecuteScript());
        commandExecuter.addCommand(new Exit());
        commandExecuter.addCommand(new Help());
        commandExecuter.addCommand(new Info());
        commandExecuter.addCommand(new RemoveAllByCarCode());
        commandExecuter.addCommand(new RemoveById());
        commandExecuter.addCommand(new RemoveFirst());
        commandExecuter.addCommand(new RemoveGreater());
        commandExecuter.addCommand(new RemoveLower());
        commandExecuter.addCommand(new Save());
        commandExecuter.addCommand(new Show());
        commandExecuter.addCommand(new SumOfCarCode());
        commandExecuter.addCommand(new Update());
        commandExecuter.addCommand(new Write());
    }
}
