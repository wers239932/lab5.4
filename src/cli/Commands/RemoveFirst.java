package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.City;
import storage.Storage;
import storage.StorageInterface;

import java.util.ArrayList;

public class RemoveFirst implements Command{
    private StorageInterface storage;
    public RemoveFirst(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        ArrayList cities = this.storage.getStorage();
        cities.removeFirst();
        this.storage.setStorsge(cities);
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
