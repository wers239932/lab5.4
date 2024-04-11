package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.City;

import java.util.ArrayList;

public interface Command {
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException;
    public String getName();
    public String getDescription();
    public Boolean getNeedObject();
}
