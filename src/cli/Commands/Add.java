package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.*;
import storage.Storage;
import storage.StorageInterface;

import java.util.ArrayList;

public class Add implements Command{
    private StorageInterface storage;
    public Add(StorageInterface storage)
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
                    arg -> Coordinates.parseXCoord((String) arg));
            Parser<Long> parser2 = new Parser();
            long y = parser2.getArgumentWithRules("введите число в формате long, вторую координату", terminal,
                    arg -> Coordinates.parseYCoord((String) arg));
            Parser<Long> parser3 = new Parser();
            Long area = parser3.getArgumentWithRules("введите площадь в формате Long, площадь должна быть больше 0", terminal,
                    arg -> City.parseArea((String) arg));
            Parser<Integer> parser4 = new Parser();
            int population = parser4.getArgumentWithRules("введите население, должно быть больше 0", terminal, arg -> City.parsePopulation((String) arg));
            Parser<Double> parser5 = new Parser();
            double deep = parser5.getArgumentWithRules("введите высоту над уровнем моря в формате double", terminal, arg -> City.parseMetersAboveSeaLevel((String) arg));
            Parser<Boolean> parser6 = new Parser();
            Boolean capital  = parser6.getArgumentWithRules("введите true если у города есть столица, что угодно другое в ином случае", terminal, arg -> City.parseCapital((String) arg));
            Parser<Long> parser7 = new Parser();
            Long carcode = parser7.getArgumentWithRules("введите carCode - целое число от 0 до 1000", terminal, arg -> City.parseCarCode((String) arg));
            Parser<Government> parser8 = new Parser();
            Government government = parser8.getArgumentWithRules("введите тип правительства: KLEPTOCRACY, CORPORATOCRACY или PATRIARCHY", terminal, arg -> City.parseGovernment((String) arg));
            Parser<Human> parser9 = new Parser();
            Human guvernor = parser9.getArgumentWithRules("введите дату в формате yyyy-MM-dd<английская буква T>HH:mm:ss", terminal, arg -> Human.parseGovernor((String) arg));
            City city = new City(name,new Coordinates(x,y),area,population,deep,capital,carcode,government, guvernor);
            this.storage.add(city);
            ArrayList<String> response = new ArrayList<>();
            response.add("добавлен элемент");
            return response;
        }
        catch (Exception e)
        {
            throw new CommandException(e.getMessage());
        }
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
