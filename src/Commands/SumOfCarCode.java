package Commands;

import Exceptions.WrongDataException;

import java.util.ArrayList;

public class SumOfCarCode implements Command{
    @Override
    public ArrayList<String> execute(ArrayList<String> args) throws WrongDataException {
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
}
