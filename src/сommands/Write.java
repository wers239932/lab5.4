package сommands;

import cli.LineReader;
import cli.commandExceptions.CommandException;
import cli.Terminal;
import cli.Command;

import java.util.ArrayList;

public class Write implements Command {

    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal, LineReader lineReader) throws CommandException {
        if(args.isEmpty()) throw new CommandException("неверный набор данных");
        ArrayList<String> response = new ArrayList<>();
        response.add(args.get(0));
        return response;
    }
    @Override
    public String getName() {
        return "write";
    }

    @Override
    public String getDescription() {
        return "write : пишет первое слово ввода";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
