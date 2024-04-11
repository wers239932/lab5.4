import cli.Terminal;

public class Main {
    public static void main(String[] args) {
        CommandExecuter commandExecuter = new CommandExecuter();
        commandExecuter.addBasicCommands();
        commandExecuter.addTerminal(new Terminal());
        commandExecuter.start();
    }
}