package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.ArgumentCheker;
import objectSpace.City;
import storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Add implements Command{
    private Storage storage;
    public Add(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {

        try {
            Parser<String> parser = new Parser();
            String name = parser.getArgumentWithRules("введите название города", terminal,
                    arg -> City.parseName((String) arg));
            Parser<Float> parser1 = new Parser();
            float x = parser1.getArgumentWithRules("введите число в формате float, первую координату", terminal,
                    arg -> City.parseFloat((String) arg));
            Parser<Long> parser2 = new Parser();
            long y = parser2.getArgumentWithRules("введите число в формате long, вторую координату", terminal,
                    arg -> City.parseLong((String) arg));
            Parser<Long> parser3 = new Parser();
            Long area1 = parser3.getArgumentWithRules("введите площадь в формате Long, площадь должна быть больше 0", terminal,
                    arg -> City.parseArea((String) arg));
            System.out.println("это должно выводиться");
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
