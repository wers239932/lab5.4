package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.City;
import StorageInterface.StorageInterface;

import java.util.ArrayList;

public class SumOfCarCode implements Command{
    private StorageInterface storage;
    public SumOfCarCode(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        Long sum= 0L;
        ArrayList<String> response = new ArrayList<>();
        for(Object city2:storage.getStorage())
        {
            City city1=(City) city2;
            if(city1.getCarCode()!=null)
            sum+=city1.getCarCode();
        }
        response.add("сумма carcode по всем объектам равна " + sum.toString());
        return response;
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
