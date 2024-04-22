package сommands;

import cli.LineReader;
import cli.commandExceptions.CommandException;
import cli.Terminal;
import StorageInterface.StorageInterface;
import cli.Command;

import java.io.IOException;
import java.util.ArrayList;

public class Save implements Command {
    private StorageInterface storage;
    public Save(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal, LineReader lineReader) throws CommandException {
        try {
            storage.save();
        } catch (IOException e) {
            throw new CommandException("ошибка ввода-вывода");
        }
        ArrayList<String> response = new ArrayList<>();
        response.add("коллекция сохранена");
        return response;
    }
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
