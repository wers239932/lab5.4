package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.*;
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
            Long area = parser3.getArgumentWithRules("введите площадь в формате Long, площадь должна быть больше 0", terminal,
                    arg -> City.parseArea((String) arg));
            Parser<Integer> parser4 = new Parser();
            int population = parser4.getArgumentWithRules("введите население, должно быть больше 0", terminal, arg -> City.parsePopulation((String) arg));
            Parser<Double> parser5 = new Parser();
            double deep = parser5.getArgumentWithRules("введите высоту над уровнем моря в формате double", terminal, arg -> City.parseDouble((String) arg));
            Parser<Boolean> parser6 = new Parser();
            Boolean capital  = parser6.getArgumentWithRules("введите true если у города есть столица, что угодно другое в ином случае", terminal, arg -> City.parseCapital((String) arg));
            Parser<Long> parser7 = new Parser();
            Long carcode = parser7.getArgumentWithRules("введите carCode - целое число от 0 до 1000", terminal, arg -> City.parseCarCode((String) arg));
            Parser<Government> parser8 = new Parser();
            Government government = parser8.getArgumentWithRules("введите тип правительства: KLEPTOCRACY, CORPORATOCRACY или PATRIARCHY", terminal, arg -> City.parseGovernment((String) arg));
            Parser<Human> parser9 = new Parser();
            Human guvernor = parser9.getArgumentWithRules("введите дату в формате yyyy-MM-dd HH:mm:ss", terminal, arg -> City.parseGovernor((String) arg));
            City city = new City(name,new Coordinates(x,y),area,population,deep,capital,carcode,government, guvernor);
            this.storage.getAllCitiesList().add(city);
            ArrayList<String> response = new ArrayList<>();
            response.add("added city");
            return response;
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
