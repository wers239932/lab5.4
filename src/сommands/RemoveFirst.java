package сommands;

import cli.IOInterface;
import cli.commandExceptions.CommandException;
import StorageInterface.StorageInterface;
import cli.Command;

import java.util.ArrayList;

public class RemoveFirst implements Command {
    private StorageInterface storage;
    public RemoveFirst(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, IOInterface terminal) throws CommandException {
        ArrayList cities = this.storage.getStorage();
        cities.remove(0);
        this.storage.setStorage(cities);
        return new ArrayList<>();
    }
    @Override
    public String getName() {
        return "remove_first";
    }

    @Override
    public String getDescription() {
        return "remove_first : удалить первый элемент из коллекции";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
