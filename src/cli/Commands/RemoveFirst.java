package cli.Commands;

import Exceptions.CommandException;
import objectSpace.City;

import java.util.ArrayList;

public class RemoveFirst implements Command{
    private Storage storage;
    public RemoveFirst(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, City city) throws CommandException {
        return null;
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