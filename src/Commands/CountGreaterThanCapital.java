package Commands;

import Exceptions.WrongDataException;

import java.util.ArrayList;

public class CountGreaterThanCapital implements Command {
    @Override
    public ArrayList<String> execute(ArrayList<String> args) throws WrongDataException {
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
}
