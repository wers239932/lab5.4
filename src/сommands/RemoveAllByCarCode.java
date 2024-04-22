package сommands;

import cli.LineReader;
import cli.commandExceptions.CommandException;
import cli.Terminal;
import cli.Command;
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
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal, LineReader lineReader) throws CommandException {
        if(args.isEmpty()) throw new CommandException("неверный набор данных");
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
