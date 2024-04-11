package Commands;

import Exceptions.WrongDataException;

import java.util.ArrayList;

public class Exit implements Command{
    @Override
    public ArrayList<String> execute(ArrayList<String> args) throws WrongDataException {
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
}
