package cli.Commands;

import Exceptions.CommandException;
import objectSpace.City;

import java.util.ArrayList;

public class Exit implements Command{
    @Override
    public ArrayList<String> execute(ArrayList<String> args) throws CommandException {
        return null;
    }
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
