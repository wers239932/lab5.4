import Commands.*;
import Exceptions.CommandDoesntExistException;
import Exceptions.WrongDataException;
import cli.Terminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandExecuter {
    private HashMap<String, Command> commandArray;
    private Terminal terminal;

    public void addCommandArray(CommandArray commandArray)
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

    private Command get(Object name) throws CommandDoesntExistException {

        Command command = this.commandArray.get((String) name);
        if(command==null) throw new CommandDoesntExistException();
        return command;

    }
    public void addBasicCommands()
    {
        this.commandArray.put("add", new Add());
        this.commandArray.put("clear", new Clear());
        this.commandArray.put("count_greater_than_capital", new CountGreaterThanCapital());
        this.commandArray.put("execute_script", new ExecuteScript());
        this.commandArray.put("exit", new Exit());
        this.commandArray.put("help", new Help());
        this.commandArray.put("info", new Info());
        this.commandArray.put("remove_all_by_car_code", new RemoveAllByCarCode());
        this.commandArray.put("remove_by_id", new RemoveById());
        this.commandArray.put("remove_first", new RemoveFirst());
        this.commandArray.put("remove_greater", new RemoveGreater());
        this.commandArray.put("remove_lower", new RemoveLower());
        this.commandArray.put("save", new Save());
        this.commandArray.put("Show", new Show());
        this.commandArray.put("Sum_of_carcode", new SumOfCarCode());
        this.commandArray.put("update", new Update());
        this.commandArray.put("write", new Write());
    }
}
