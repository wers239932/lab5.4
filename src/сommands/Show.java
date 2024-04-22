package сommands;

import cli.LineReader;
import cli.commandExceptions.CommandException;
import cli.Terminal;
import cli.Command;
import objectSpace.City;
import StorageInterface.StorageInterface;

import java.util.ArrayList;

public class Show implements Command {
    private StorageInterface storage;
    public Show(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal, LineReader lineReader) throws CommandException {
        ArrayList<String> response = new ArrayList<>();
        for(Object city1: storage.getStorage())
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
