import Commands.Command;
import Commands.Help;
import Commands.Write;
import cli.Terminal;

public class Main {
    public static void main(String[] args) {
        CommandArray commandArray = new CommandArray();
        commandArray.addCommand(new String[]{"help"},new Help());
        commandArray.addCommand(new String[]{"write"},new Write());
        CommandExecuter commandExecuter = new CommandExecuter();
        commandExecuter.addCommandArray(commandArray);
        commandExecuter.addTerminal(new Terminal());
        commandExecuter.start();
    }
}