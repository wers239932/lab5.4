package cli.Commands;

import Exceptions.CommandException;
import objectSpace.City;

import java.util.ArrayList;

public class Show implements Command{
    private Storage storage;
    public Show(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, City city) throws CommandException {
        ArrayList<String> response = new ArrayList<>();
        for(Object city1: storage)
        {
            response.add(((City) city1).toString());
        }
        return response;
    }
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
