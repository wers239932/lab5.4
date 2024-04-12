package cli.Commands;

import cli.Commands.Exceptions.CommandException;
import cli.Terminal;

import java.util.ArrayList;

public interface Command {
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException;
    public String getName();
    public String getDescription();
    public Boolean getNeedObject();
}
