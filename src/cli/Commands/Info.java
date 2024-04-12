package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.City;
import storage.Storage;

import java.util.ArrayList;

public class Info implements Command{
    private Storage storage;
    public Info(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        return null;
    }
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
