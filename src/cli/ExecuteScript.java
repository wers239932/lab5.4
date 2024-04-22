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
    private ArrayList<String> scriptNames;
    public ExecuteScript(ArrayList<Command> commandArrayList, ArrayList<String> scriptNames)
    {
        this.commandArrayList = commandArrayList;
        this.scriptNames = scriptNames;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal, LineReader lineReader) throws CommandException {
        ArrayList<String> response = new ArrayList<>();
        String scriptName = args.get(0);
        Boolean recursion=false;
        for(String scriptNamePrevious: scriptNames)
        {
            if(scriptNamePrevious.equals(scriptName))
            {
                recursion=true;
            }
        }
        if(!recursion) {
            try {
                scriptNames.add(scriptName);
                CommandFileReader commandFileReader = new CommandFileReader(scriptName);
                commandFileReader.openFile();
                CommandExecuter commandExecuter = new CommandExecuter(commandFileReader, terminal, commandArrayList, scriptNames);
                try {
                    commandExecuter.startFromFile();
                } catch (Exception e) {
                    response.add(e.getMessage());
                    response.add("файл исполнен");
                }
                commandFileReader.closeStream();
                scriptNames.remove(scriptName);
                return response;
            } catch (Exception e) {
                throw new CommandException(e.getMessage());
            }
        }
        response.add("нельзя запустить скрипт из себя");
        return response;
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
