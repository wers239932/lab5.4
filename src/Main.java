import cli.Commands.Command;
import cli.Terminal;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Command> commandArray = CommandArrayFiller.setBasicCommands();
        CommandExecuter commandExecuter = new CommandExecuter(new Terminal(), commandArray);
        commandExecuter.start();
    }
}