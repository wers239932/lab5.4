package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.City;
import objectSpace.exceptions.IdException;
import StorageInterface.StorageInterface;

import java.util.ArrayList;

public class RemoveById implements Command{
    private StorageInterface storage;
    private int id;
    public RemoveById(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        try {
            this.id = City.parseId(args.get(0));
        } catch (IdException e) {
            throw new CommandException(e.getMessage());
        }
        ArrayList<String> response = new ArrayList<>();
        for(Object city2:storage.getStorage())
        {
            City city1=(City) city2;
            if(city1.getId()==this.id)
            {
                storage.remove((City) city2);
            }
        }
        response.add("объект удален");
        return response;
    }
    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
