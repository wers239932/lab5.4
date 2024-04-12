package cli;

import cli.Command;
import cli.commandExceptions.CommandException;
import cli.Terminal;
import StorageInterface.StorageInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class ExecuteScript implements Command {
    private StorageInterface storage;
    private ArrayList<Command> commandArrayList;
    public ExecuteScript(ArrayList<Command> commandArrayList)
    {
        this.commandArrayList = commandArrayList;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, LineReader terminal) throws CommandException {
        try {
            ArrayList<String> response = new ArrayList<>();
            String scriptName = args.get(0);
            CommandFileReader commandFileReader = new CommandFileReader(scriptName);
            commandFileReader.openFile();
            CommandExecuter commandExecuter = new CommandExecuter(commandFileReader, commandArrayList);
            response.addAll(commandExecuter.startFromFile());
            commandFileReader.closeStream();
            return response;
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }

    }
    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
    @Override
    public Boolean getNeedObject() {
        return false;
    }
}
