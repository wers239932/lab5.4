package сommands;

import cli.LineReader;
import cli.commandExceptions.CommandException;
import cli.Terminal;
import StorageInterface.StorageInterface;
import cli.Command;

import java.util.ArrayList;

public class Info implements Command {
    private StorageInterface storage;
    public Info(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal, LineReader lineReader) throws CommandException {
        ArrayList<String> response = new ArrayList<>();
        response.add("Дата созданиия: "+storage.getCreationDate().toString());
        response.add("количество элементов в памяти: "+storage.getStorage().size());
        return response;
    }
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
