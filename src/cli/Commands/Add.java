package cli.Commands;

import Exceptions.CommandException;
import objectSpace.City;
import storage.Storage;

import java.util.ArrayList;

public class Add implements Command{
    private Storage storage;
    public Add(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args) throws CommandException {
        return null;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }

    @Override
    public Boolean getNeedObject() {
        return true;
    }
}
