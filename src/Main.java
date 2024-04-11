import cli.Commands.Command;
import cli.Terminal;
import filework.DataAccessLayer;
import filework.Storage;
import objectSpace.exceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        DataAccessLayer.setDataAccessLayer(System.getenv("SAVEFILE"));
        Storage storage = null;
        try {
            storage = DataAccessLayer.loadStorage();
        } catch (IOException | CoordinatesException | AreaException | GovernmentException | GovernorException |
                 HeightException | CarCodeException | PopulationException | NameCityException | CapitalException e) {
            storage = new Storage();
            System.out.println("не удалось загрузить коллекцию");
        }
        ArrayList commandArray = CommandArrayFiller.setBasicCommands(storage);
        CommandExecuter commandExecuter = new CommandExecuter(new Terminal(), commandArray, storage);
        commandExecuter.start();
    }
}