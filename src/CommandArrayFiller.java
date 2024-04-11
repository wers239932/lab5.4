import cli.Commands.*;
import filework.Storage;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandArrayFiller {
    static ArrayList<Command> commandList;
    public static ArrayList<Command> setBasicCommands(Storage storage)
    {
        commandList = new ArrayList<>();
        commandList.add(new Add(storage));
        commandList.add(new Add(storage));
        commandList.add(new Clear(storage));
        commandList.add(new CountGreaterThanCapital(storage));
        commandList.add(new ExecuteScript(storage));
        commandList.add(new Exit());
        commandList.add(new Help());
        commandList.add(new Info(storage));
        commandList.add(new RemoveAllByCarCode(storage));
        commandList.add(new RemoveById(storage));
        commandList.add(new RemoveFirst(storage));
        commandList.add(new RemoveGreater(storage));
        commandList.add(new RemoveLower(storage));
        commandList.add(new Save(storage));
        commandList.add(new Show(storage));
        commandList.add(new SumOfCarCode(storage));
        commandList.add(new Update(storage));
        commandList.add(new Write());
        return commandList;
    }
}
