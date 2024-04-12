package сommands;

import cli.commandExceptions.CommandException;
import cli.Terminal;
import cli.Command;
import objectSpace.City;
import objectSpace.objectExceptions.CapitalException;
import StorageInterface.StorageInterface;

import java.util.ArrayList;

public class CountGreaterThanCapital implements Command {
    private StorageInterface storage;
    public CountGreaterThanCapital(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        Boolean capital;
        try {
            capital = City.parseCapital(args.get(0));
        } catch (CapitalException e) {
            throw new CommandException(e.getMessage());
        }
        int amount=0;
        ArrayList<String> response = new ArrayList<>();
        for(Object city2:storage.getStorage())
        {
            City city1=(City) city2;
            if(city1.getCapital().compareTo(capital)>0)
            {
                amount++;
            }
        }
        response.add("количество объектов с полем carCode больше заданного равно " + amount);
        return response;
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
