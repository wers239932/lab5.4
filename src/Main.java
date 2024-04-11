import cli.Commands.Command;
import cli.Terminal;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ArrayList commandArray = CommandArrayFiller.setBasicCommands();
        CommandExecuter commandExecuter = new CommandExecuter(new Terminal(), commandArray);
        commandExecuter.start();
    }
}