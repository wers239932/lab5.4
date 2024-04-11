import cli.Commands.*;
import Exceptions.CommandDoesntExistException;
import Exceptions.WrongDataException;
import cli.Terminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandExecuter {
    private HashMap<String, Command> commandArray;
    private Terminal terminal;

    public void addCommandArray(HashMap<String, Command> commandArray)
    {
        this.commandArray = commandArray;
    }
    public void addTerminal(Terminal terminal){
        this.terminal = terminal;
    }

    public void start()
    {
        while (true)
        {
            try {
                ArrayList commandLine = new ArrayList(List.of(this.terminal.readLine().split(" +")));
                Command command = this.get(commandLine.get(0));
                commandLine.removeFirst();
                ArrayList response = command.execute(commandLine);
                this.terminal.writeResponse(response);
            }
            catch (CommandDoesntExistException e)
            {
                this.terminal.writeLine("такой команды не существует");
            }
            catch (NullPointerException e)
            {
                this.terminal.writeLine("команда возвращает null набор строк");
            }
            catch (WrongDataException e)
            {
                this.terminal.writeLine("введен неверный набор данных");
            }
            catch (Exception e)
            {
                terminal.writeLine(e.getMessage() + "\n" +  e.getClass());
            }

        }
    }
    public void addCommand(String[] names, Command command)
    {
        for (String name : names)
        {
            this.commandArray.put(name, command);
        }
    }

    public void addCommand(Command command)
    {
        this.commandArray.put(command.getName(), command);
    }
    private Command get(Object name) throws CommandDoesntExistException {

        Command command = this.commandArray.get((String) name);
        if(command==null) throw new CommandDoesntExistException();
        return command;

    }
    public void addBasicCommands()
    {
        this.commandArray = new HashMap<>();
        this.addCommand(new Add());
        this.addCommand(new Clear());
        this.addCommand(new CountGreaterThanCapital());
        this.addCommand(new ExecuteScript());
        this.addCommand(new Exit());
        this.addCommand(new Help());
        this.addCommand(new Info());
        this.addCommand(new RemoveAllByCarCode());
        this.addCommand(new RemoveById());
        this.addCommand(new RemoveFirst());
        this.addCommand(new RemoveGreater());
        this.addCommand(new RemoveLower());
        this.addCommand(new Save());
        this.addCommand(new Show());
        this.addCommand(new SumOfCarCode());
        this.addCommand(new Update());
        this.addCommand(new Write());
    }
}
