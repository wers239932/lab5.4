package cli;

import cli.commandExceptions.CommandDoesntExistException;
import cli.commandExceptions.CommandException;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CommandExecuter {
    private HashMap<String, Command> commandArray;
    private IOInterface terminal;
    private HashSet<String> runningScripts;

    public CommandExecuter(IOInterface terminal, ArrayList<Command> commandArray, HashSet<String> runningScripts) {
        this.terminal = terminal;
        this.commandArray = new HashMap<>();
        this.addCommandArray(commandArray);
        if (runningScripts == null) this.runningScripts = new HashSet<String>();
        else this.runningScripts = runningScripts;
    }

    private ArrayList<Command> getCommandArray() {
        return new ArrayList<Command>(commandArray.values());
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
                String commandName = (String) commandLine.get(0);
                ArrayList<String> response = new ArrayList<>();
                switch (commandName) {
                    case ("help"): {
                        response = this.help();
                        break;
                    }
                    case ("execute_script"): {
                        String filename = commandLine.get(1).toString();
                        if (this.runningScripts.contains(filename))
                            break;
                        FileTerminal fileIO = new FileTerminal(filename);
                        this.runningScripts.add(filename);
                        CommandExecuter commandExecuter = new CommandExecuter(fileIO, this.getCommandArray(), this.runningScripts);
                        commandExecuter.start();
                        this.runningScripts.remove(filename);
                        break;
                    }
                    default: {
                        Command command = this.getCommand(commandName);
                        commandLine.remove(0);
                        response = command.execute(commandLine, terminal);
                        break;
                    }
                }
                this.terminal.writeResponse(response);
            } catch (CommandDoesntExistException e) {
                this.terminal.writeLine("такой команды не существует");
            } catch (NullPointerException e) {
                this.terminal.writeLine("команда возвращает null набор строк");
            } catch (CommandException e) {
                this.terminal.writeLine(e.getMessage());
            } catch (NoSuchElementException e) {
                return;
            } catch (Exception e) {
                terminal.writeLine(e.getMessage() + "\n" + e.getClass());
            }

        }
    }


    public void addCommand(Command command) {
        this.commandArray.put(command.getName(), command);
    }

    private Command getCommand(Object name) throws CommandDoesntExistException {

        Command command = this.commandArray.get((String) name);
        if (command == null) throw new CommandDoesntExistException();
        return command;

    }

    public ArrayList<String> help() {
        ArrayList<String> response = new ArrayList<>();
        for (Command command : this.commandArray.values()) {
            response.add(command.getDescription());
        }
        return response;
    }
}
