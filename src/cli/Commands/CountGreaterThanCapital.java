package cli.Commands;

import Exceptions.CommandException;
import objectSpace.City;

import java.util.ArrayList;

public class CountGreaterThanCapital implements Command {
    private Storage storage;
    public CountGreaterThanCapital(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, City city) throws CommandException {
        return null;
    }
    @Override
    public String getName() {
        return "count_greater_than_capital";
    }

    @Override
    public String getDescription() {
        return "count_greater_than_capital capital : вывести количество элементов, значение поля capital которых больше заданного";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
