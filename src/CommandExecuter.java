import cli.Commands.*;
import cli.Commands.Exceptions.CommandDoesntExistException;
import cli.Commands.Exceptions.CommandException;
import cli.Terminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandExecuter {
    private HashMap<String, Command> commandArray;
    private Terminal terminal;

    public CommandExecuter(Terminal terminal, ArrayList<Command> commandArray) {
        this.terminal = terminal;
        this.commandArray = new HashMap<>();
        commandArray.add(new Help(this.commandArray));
        this.addCommandArray(commandArray);
    }


    private void addCommandArray(ArrayList<Command> commandArrayList) {
        for (Command command : commandArrayList) {
            this.addCommand(command);
        }
    }


    public void start() {
        while (true) {
            try {
                ArrayList commandLine = new ArrayList(List.of(this.terminal.readLine().split(" +")));
                Command command = this.get(commandLine.get(0));
                commandLine.removeFirst();
                ArrayList response = command.execute(commandLine, terminal);
                this.terminal.writeResponse(response);
            } catch (CommandDoesntExistException e) {
                this.terminal.writeLine("такой команды не существует");
            } catch (NullPointerException e) {
                this.terminal.writeLine("команда возвращает null набор строк");
            } catch (CommandException e) {
                this.terminal.writeLine(e.getMessage());
            } catch (Exception e) {
                terminal.writeLine(e.getMessage() + "\n" + e.getClass());
            }

        }
    }


    public void addCommand(Command command) {
        this.commandArray.put(command.getName(), command);
    }

    private Command get(Object name) throws CommandDoesntExistException {

        Command command = this.commandArray.get((String) name);
        if (command == null) throw new CommandDoesntExistException();
        return command;

    }

    private HashMap<String, Command> getCommandArray() {
        return this.commandArray;
    }
}
