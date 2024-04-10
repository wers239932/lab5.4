import Commands.Command;

import java.util.HashMap;

public class CommandArray extends HashMap<String, Command>{
    public void addCommand(String[] names, Command command)
    {
        for (String name : names)
        {
            this.put(name, command);
        }
    }

}
