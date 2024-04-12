package cli.Commands;

import cli.Terminal;
import objectSpace.City;

import java.util.ArrayList;
import java.util.HashMap;

public class Help implements Command{
    private HashMap<String, Command> commandArray;
    public Help()
    {

    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) {
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
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
