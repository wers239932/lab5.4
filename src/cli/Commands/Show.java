package cli.Commands;

import Exceptions.CommandException;
import filework.Storage;
import objectSpace.City;

import java.util.ArrayList;

public class Show implements Command{
    private Storage storage;
    public Show(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args) throws CommandException {
        ArrayList<String> response = new ArrayList<>();
        for(Object city: storage)
        {
            response.add(((City) city).toString());
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
}
