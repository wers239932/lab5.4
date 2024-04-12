package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.ArgumentCheker;
import objectSpace.City;
import storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Add implements Command{
    private Storage storage;
    public Add(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        Parser<Long> parser = new Parser<>();
        try {
            String name = String.valueOf(parser.getArgumentWithRules("введите название города", terminal,
                    arg -> City.parseName((String) arg)));
            Long area1 = parser.getArgumentWithRules("введите площадь в формате Long, площадь должна быть больше 0", terminal,
                    arg -> City.parseArea((String) arg));
            float x = parser.getArgumentWithRules("введите число в формате float, первую координату", terminal,
                    arg -> City.parseFloat((String) arg));
        }
        catch (Exception e)
        {
            terminal.writeLine(e.getMessage());
        }

        return null;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }

    @Override
    public Boolean getNeedObject() {
        return true;
    }

}
