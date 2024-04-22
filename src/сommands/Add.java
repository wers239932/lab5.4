package сommands;

import cli.IOInterface;
import objectSpace.Parser;
import cli.commandExceptions.CommandException;
import cli.Terminal;
import cli.Command;
import objectSpace.*;
import StorageInterface.StorageInterface;

import java.util.ArrayList;

public class Add implements Command {
    private StorageInterface storage;
    public Add(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, IOInterface terminal) throws CommandException {

        try {
            Parser<String> parserName = new Parser();
            String name = parserName.getArgumentWithRules("введите название города", terminal,
                    arg -> City.parseName((String) arg));
            Parser<Float> parserX = new Parser();
            float x = parserX.getArgumentWithRules("введите число в формате float, первую координату", terminal,
                    arg -> Coordinates.parseXCoord((String) arg));
            Parser<Long> parserY = new Parser();
            long y = parserY.getArgumentWithRules("введите число в формате long, вторую координату", terminal,
                    arg -> Coordinates.parseYCoord((String) arg));
            Parser<Long> parserArea = new Parser();
            Long area = parserArea.getArgumentWithRules("введите площадь в формате Long, площадь должна быть больше 0", terminal,
                    arg -> City.parseArea((String) arg));
            Parser<Integer> parserPopulation = new Parser();
            int population = parserPopulation.getArgumentWithRules("введите население, должно быть больше 0", terminal, arg -> City.parsePopulation((String) arg));
            Parser<Double> parserMetersAbove = new Parser();
            double deep = parserMetersAbove.getArgumentWithRules("введите высоту над уровнем моря в формате double", terminal, arg -> City.parseMetersAboveSeaLevel((String) arg));
            Parser<Boolean> parserCapital = new Parser();
            Boolean capital  = parserCapital.getArgumentWithRules("введите true если у города есть столица, что угодно другое в ином случае", terminal, arg -> City.parseCapital((String) arg));
            Parser<Long> parserCarCode = new Parser();
            Long carcode = parserCarCode.getArgumentWithRules("введите carCode - целое число от 0 до 1000", terminal, arg -> City.parseCarCode((String) arg));
            Parser<Government> parserGovernment = new Parser();
            Government government = parserGovernment.getArgumentWithRules("введите тип правительства: KLEPTOCRACY, CORPORATOCRACY или PATRIARCHY", terminal, arg -> City.parseGovernment((String) arg));
            Parser<Human> parserGovernor = new Parser();
            Human governor = parserGovernor.getArgumentWithRules("введите дату в формате yyyy-MM-dd<английская буква T>HH:mm:ss", terminal, arg -> Human.parseGovernor((String) arg));
            City city = new City(name,new Coordinates(x,y),area,population,deep,capital,carcode,government, governor);
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
