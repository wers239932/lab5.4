package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import StorageInterface.StorageInterface;

import java.util.ArrayList;

public class ExecuteScript implements Command{
    private StorageInterface storage;
    public ExecuteScript(StorageInterface storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        return null;
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
