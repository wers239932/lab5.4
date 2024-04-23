package app;

import StorageInterface.StorageInterface;
import cli.CommandExecuter;
import cli.Terminal;
import client.Client;
import сommands.CommandArrayFiller;

import java.util.ArrayList;

public class AppClient {
    public static void Run()
    {
        Client client = new Client();
        ArrayList commandArray = CommandArrayFiller.setBasicCommands(client);
        CommandExecuter commandExecuter = new CommandExecuter(new Terminal(), commandArray, null);
        commandExecuter.start();
    }
}
