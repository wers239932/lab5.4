package cli;

import cli.commandExceptions.CommandException;
import cli.Terminal;

import java.io.IOException;
import java.util.ArrayList;

public interface Command {
    public ArrayList<String> execute(ArrayList<String> args, LineReader terminal) throws CommandException, IOException;
    public String getName();
    public String getDescription();
    public Boolean getNeedObject();
}
