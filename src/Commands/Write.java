package Commands;

import Exceptions.WrongDataException;

import java.util.ArrayList;
import java.util.Objects;

public class Write implements Command {
    @Override
    public ArrayList<String> execute(ArrayList<Object> args) throws WrongDataException {
        if(args.isEmpty()) throw new WrongDataException();
        ArrayList<String> response = new ArrayList<>();
        response.add(args.get(0));
        return response;
    }
}
