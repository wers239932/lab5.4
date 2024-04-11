import cli.Terminal;
import dal.DataAccessLayer;
import objectSpace.exceptions.*;
import storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DataAccessLayer dataAccessLayer = new DataAccessLayer(System.getenv("SAVEFILE"));
        Storage storage = null;
        try {
            storage = new Storage(dataAccessLayer);
        } catch (IOException e) {
            System.out.println("не удалось загрузить коллекцию");
            System.exit(1);
        }
        ArrayList commandArray = CommandArrayFiller.setBasicCommands(storage);
        CommandExecuter commandExecuter = new CommandExecuter(new Terminal(), commandArray);
        commandExecuter.start();
    }
}