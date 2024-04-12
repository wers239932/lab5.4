package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.City;
import storage.Storage;

import java.util.ArrayList;

public class RemoveLower implements Command{
    private Storage storage;
    public RemoveLower(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        return null;
    }
    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescription() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
    @Override
    public Boolean getNeedObject() {
        return true;
    }
}
