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
import objectSpace.objectExceptions.IdException;
import StorageInterface.StorageInterface;

import java.util.ArrayList;

public class Update implements Command {
    private StorageInterface storage;
    public Update(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal, LineReader lineReader) throws CommandException {
        int id;
        if(args.isEmpty()) throw new CommandException("неверный набор данных");
        try {
            id = City.parseId(args.get(0));
        } catch (IdException e) {
            throw new CommandException(e.getMessage());
        }
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
            if(city1.getId()==id)
            {
                storage.remove((City) city2);
                storage.add(city);
            }
        }
        response.add("объект обновлен");
        return response;
    }
    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
    @Override
    public Boolean getNeedObject() {
        return true;
    }
}
