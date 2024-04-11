package cli.Commands;

import Exceptions.CommandException;
import objectSpace.City;

import java.util.ArrayList;

public class Clear implements Command{
    private Storage storage;
    public Clear(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, City city) throws CommandException {
        return null;
    }
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
