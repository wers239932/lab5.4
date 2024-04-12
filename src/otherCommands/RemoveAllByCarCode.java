package otherCommands;

import cli.commandExceptions.CommandException;
import cli.Terminal;
import command.Command;
import objectSpace.City;
import objectSpace.objectExceptions.CarCodeException;
import StorageInterface.StorageInterface;

import java.util.ArrayList;

public class RemoveAllByCarCode implements Command {
    private StorageInterface storage;
    private Long carCode;
    public RemoveAllByCarCode(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        try {
            this.carCode = City.parseCarCode(args.get(0));
        } catch (CarCodeException e) {
            throw new CommandException(e.getMessage());
        }

        ArrayList<String> response = new ArrayList<>();
        for(Object city2:storage.getStorage())
        {
            City city1=(City) city2;
            if(city1.getCarCode()==carCode)
            {
                storage.remove((City) city2);
            }
        }
        response.add("объекты удалены");
        return response;
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
