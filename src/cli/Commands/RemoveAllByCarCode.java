package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.City;
import storage.Storage;

import java.util.ArrayList;

public class RemoveAllByCarCode implements Command{
    private Storage storage;
    public RemoveAllByCarCode(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        return null;
    }
    @Override
    public String getName() {
        return "remove_all_by_car_code";
    }

    @Override
    public String getDescription() {
        return "remove_all_by_car_code carCode : удалить из коллекции все элементы, значение поля carCode которого эквивалентно заданному";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
