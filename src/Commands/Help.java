package Commands;

import java.util.ArrayList;

public class Help implements Command{

    @Override
    public ArrayList<String> execute(ArrayList<String> args) {
        ArrayList<String> response = new ArrayList<>();
        response.add("it's too late to help");
        return response;
    }
}
