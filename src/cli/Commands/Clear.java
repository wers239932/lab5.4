package cli.Commands;

import Exceptions.CommandException;

import java.util.ArrayList;

public class Clear implements Command{
    @Override
    public ArrayList<String> execute(ArrayList<String> args) throws CommandException {
        return null;
    }
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
