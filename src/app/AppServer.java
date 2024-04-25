package app;

import cli.CommandExecuter;
import cli.Terminal;
import dal.DataAccessLayer;
import server.Server;
import storage.Storage;
import сommands.CommandArrayFiller;

import java.io.IOException;
import java.util.ArrayList;

public class AppServer {
    public static void Run() {
        DataAccessLayer dataAccessLayer = new DataAccessLayer(System.getenv("SAVEFILE"));
        Storage storage = null;
        try {
            storage = new Storage(dataAccessLayer);
        } catch (IOException e) {
            System.out.println("не удалось загрузить коллекцию");
            System.exit(1);
        }
        String host = System.getenv("SERVER_HOST");
        int port = Integer.parseInt(System.getenv("SERVER_PORT"));
        Server server = new Server(host, port, storage);
        server.handle();
    }
}
