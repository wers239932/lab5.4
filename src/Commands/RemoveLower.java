package Commands;

import Exceptions.WrongDataException;

import java.util.ArrayList;

public class RemoveLower implements Command{
    @Override
    public ArrayList<String> execute(ArrayList<String> args) throws WrongDataException {
        return null;
    }
    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public String getDescription() {
        return "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
