package app;

import cli.CommandExecuter;
import cli.Terminal;
import dal.DataAccessLayer;
import storage.Storage;
import сommands.CommandArrayFiller;

import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void Run() {
        DataAccessLayer dataAccessLayer = new DataAccessLayer(System.getenv("SAVEFILE"));
        Storage storage = null;
        try {
            storage = new Storage(dataAccessLayer);
        } catch (IOException e) {
            System.out.println("не удалось загрузить коллекцию");
            System.exit(1);
        }
        ArrayList commandArray = CommandArrayFiller.setBasicCommands(storage);
        CommandExecuter commandExecuter = new CommandExecuter(new Terminal(), commandArray, null);
        commandExecuter.start();
    }
}
