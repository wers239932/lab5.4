package cli;

import cli.commandExceptions.CommandException;

import java.util.ArrayList;

public interface Command {
    ArrayList<String> execute(ArrayList<String> args, IOInterface terminal) throws CommandException;

    String getName();

    String getDescription();

    Boolean getNeedObject();
}
