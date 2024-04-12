package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.City;
import storage.Storage;

import java.util.ArrayList;

public class RemoveGreater implements Command{
    private Storage storage;
    public RemoveGreater(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        return null;
    }
    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный";
    }
    @Override
    public Boolean getNeedObject() {
        return true;
    }
}
