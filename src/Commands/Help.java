package Commands;

import java.util.ArrayList;
import java.util.Objects;

public class Help implements Command{

    @Override
    public ArrayList<String> execute(ArrayList<Object> args) {
        ArrayList<String> response = new ArrayList<>();
        response.add("it's too late to help");
        return response;
    }
}
