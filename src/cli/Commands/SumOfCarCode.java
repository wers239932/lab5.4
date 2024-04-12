package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.City;
import storage.Storage;

import java.util.ArrayList;

public class SumOfCarCode implements Command{
    private Storage storage;
    public SumOfCarCode(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        return null;
    }
    @Override
    public String getName() {
        return "sum_of_car_code";
    }

    @Override
    public String getDescription() {
        return "sum_of_car_code : вывести сумму значений поля carCode для всех элементов коллекции";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
