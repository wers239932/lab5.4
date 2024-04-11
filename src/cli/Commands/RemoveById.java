package cli.Commands;

import Exceptions.CommandException;
import objectSpace.City;

import java.util.ArrayList;

public class RemoveById implements Command{
    private Storage storage;
    public RemoveById(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, City city) throws CommandException {
        return null;
    }
    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
