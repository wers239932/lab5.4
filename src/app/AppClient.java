package app;

import cli.CommandExecuter;
import cli.Terminal;
import client.Client;
import сommands.CommandArrayFiller;

import java.net.UnknownHostException;
import java.util.ArrayList;

public class AppClient {
    public static void Run() {
        String host = System.getenv("SERVER_HOST");
        int port = Integer.parseInt(System.getenv("SERVER_PORT"));
        Client client = null;
        try {
            client = new Client(host, port);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        ArrayList commandArray = CommandArrayFiller.setBasicCommands(client);
        CommandExecuter commandExecuter = new CommandExecuter(new Terminal(), commandArray);
        commandExecuter.start();
    }
}
