import cli.Terminal;

public class Main {
    public static void main(String[] args) {
        CommandExecuter commandExecuter = new CommandExecuter();
        CommandArrayFiller.addBasicCommands(commandExecuter);
        commandExecuter.addTerminal(new Terminal());
        commandExecuter.start();
    }
}