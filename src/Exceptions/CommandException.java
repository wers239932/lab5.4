package Exceptions;

public class CommandException extends Exception{
    public CommandException(String description) {
        super(description);
    }
}
