import Commands.Command;
import Exceptions.WrongDataException;
import cli.Terminal;

import java.util.ArrayList;
import java.util.List;

public class CommandExecuter {
    private CommandArray commandArray;
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
                Command command = commandArray.get(commandLine.get(0));
                commandLine.removeFirst();
                ArrayList response = command.execute(commandLine);
                this.terminal.writeResponse(response);
            }
            catch (NullPointerException e)
            {
                this.terminal.writeLine("такой команды не существует");
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
}
