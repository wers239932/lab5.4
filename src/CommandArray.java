import Commands.*;
import Exceptions.CommandDoesntExistException;

import java.util.HashMap;
import java.util.Objects;

public class CommandArray extends HashMap<String, Command>{
    public void addCommand(String[] names, Command command)
    {
        for (String name : names)
        {
            this.put(name, command);
        }
    }
    @Override
    public Command get(Object name) throws CommandDoesntExistException {
        try{
            Command command = super.get((String) name);
            return command;
        }
        catch (NullPointerException e)
        {
            throw new CommandDoesntExistException();
        }
    }
    public void addBasicCommands()
    {
        this.put("add", new Add());
        this.put("clear", new Clear());
        this.put("count_greater_than_capital", new CountGreaterThanCapital());
        this.put("execute_script", new ExecuteScript());
        this.put("exit", new Exit());
        this.put("help", new Help());
        this.put("info", new Info());
        this.put("remove_all_by_car_code", new RemoveAllByCarCode());
        this.put("remove_by_id", new RemoveById());
        this.put("remove_first", new RemoveFirst());
        this.put("remove_greater", new RemoveGreater());
        this.put("remove_lower", new RemoveLower());
        this.put("save", new Save());
        this.put("Show", new Show());
        this.put("Sum_of_carcode", new SumOfCarCode());
        this.put("update", new Update());
        this.put("write", new Write());
    }
}
