package cli.Commands;

import java.util.ArrayList;
import java.util.HashMap;

public class Help implements Command{
    private HashMap<String, Command> commandArray;
    public Help()
    {

    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args) {
        ArrayList<String> response = new ArrayList<>();
        response.add("it's too late to help");
        return response;
    }
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}
