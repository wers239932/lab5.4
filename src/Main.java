import Commands.Command;
import Commands.Help;
import Commands.Write;
import cli.Terminal;

public class Main {
    public static void main(String[] args) {
        CommandArray commandArray = new CommandArray();
        commandArray.addBasicCommands();
        CommandExecuter commandExecuter = new CommandExecuter();
        commandExecuter.addCommandArray(commandArray);
        commandExecuter.addTerminal(new Terminal());
        commandExecuter.start();
    }
}