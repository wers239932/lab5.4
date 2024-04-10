package Commands;

import Exceptions.WrongDataException;

import java.util.ArrayList;

public interface Command {
    public ArrayList<String> execute(ArrayList<String> args) throws WrongDataException;
}
