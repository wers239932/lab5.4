package cli.Commands;

import Exceptions.CommandException;
import filework.Storage;

import java.util.ArrayList;

public class Update implements Command{
    private Storage storage;
    public Update(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args) throws CommandException {
        return null;
    }
    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}
