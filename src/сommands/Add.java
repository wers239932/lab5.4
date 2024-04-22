package сommands;

import cli.CityReader;
import cli.LineReader;
import objectSpace.Parser;
import cli.commandExceptions.CommandException;
import cli.Terminal;
import cli.Command;
import objectSpace.*;
import StorageInterface.StorageInterface;

import java.io.IOException;
import java.util.ArrayList;

public class Add implements Command {
    private StorageInterface storage;
    public Add(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal, LineReader lineReader) throws CommandException, IOException {

        try {
            City city = CityReader.readCity(lineReader, terminal);
            this.storage.add(city);
            ArrayList<String> response = new ArrayList<>();
            response.add("добавлен элемент");
            return response;
        }
        catch (IOException e)
        {
        throw new IOException();
        }
        catch (Exception e)
        {
            throw new CommandException(e.getMessage());
        }
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
