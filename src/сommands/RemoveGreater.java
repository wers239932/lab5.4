package сommands;

import cli.CityReader;
import cli.LineReader;
import objectSpace.Parser;
import cli.commandExceptions.CommandException;
import cli.Terminal;
import cli.Command;
import objectSpace.City;
import objectSpace.Coordinates;
import objectSpace.Government;
import objectSpace.Human;
import StorageInterface.StorageInterface;

import java.util.ArrayList;

public class RemoveGreater implements Command {
    private StorageInterface storage;
    public RemoveGreater(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal, LineReader lineReader) throws CommandException {
        City city;
        try {
            city = CityReader.readCity(lineReader, terminal);
        }
        catch (Exception e)
        {
            throw new CommandException(e.getMessage());
        }
        ArrayList<String> response = new ArrayList<>();
        for(Object city2:storage.getStorage())
        {
            City city1=(City) city2;
            if(city1.compareTo(city)>0)
            {
                storage.remove((City) city2);
            }
        }
        response.add("элементы удалены");
        return  response;
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
