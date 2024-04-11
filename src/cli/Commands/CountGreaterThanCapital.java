package cli.Commands;

import Exceptions.CommandException;

import java.util.ArrayList;

public class CountGreaterThanCapital implements Command {
    @Override
    public ArrayList<String> execute(ArrayList<String> args) throws CommandException {
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
