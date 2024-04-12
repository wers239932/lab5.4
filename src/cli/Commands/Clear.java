package cli.Commands;

import cli.Commands.Exceptions.CommandException;
import cli.Terminal;
import StorageInterface.StorageInterface;

import java.util.ArrayList;

public class Clear implements Command{
    private StorageInterface storage;
    public Clear(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        ArrayList<String> response = new ArrayList<>();
        this.storage.clear();
        response.add("коллекция очищена");
        return response;
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
