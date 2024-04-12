package cli;

import cli.commandExceptions.CommandDoesntExistException;
import cli.commandExceptions.CommandException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommandExecuter {
    private HashMap<String, Command> commandArray;
    private Terminal terminal;
    private LineReader lineReader;
    private Boolean executionFromFile;

    public CommandExecuter(Terminal terminal, ArrayList<Command> commandArray) {
        this.terminal = terminal;
        this.lineReader = terminal;
        this.commandArray = new HashMap<>();
        commandArray.add(new Help(this.commandArray));
        this.addCommandArray(commandArray);
        this.executionFromFile=false;
    }
    public CommandExecuter(LineReader lineReader, ArrayList<Command> commandArray) {
        this.lineReader = lineReader;
        this.commandArray = new HashMap<>();
        commandArray.add(new Help(this.commandArray));
        commandArray.add(new ExecuteScript(commandArray));
        this.addCommandArray(commandArray);
        this.executionFromFile=true;
    }


    private void addCommandArray(ArrayList<Command> commandArrayList) {
        for (Command command : commandArrayList) {
            this.addCommand(command);
        }
    }


    public void startFromTerminal() {
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
    public ArrayList<String> startFromFile() throws Exception {
        ArrayList<String> responses = new ArrayList<>();
        while (true) {

            try{
                ArrayList commandLine = new ArrayList(List.of(this.lineReader.readLine().split(" +")));
                Command command = this.get(commandLine.get(0));
                commandLine.removeFirst();
                ArrayList response = command.execute(commandLine, lineReader);
                responses.addAll(response);
            }
            catch (CommandDoesntExistException e) {
                responses.add("такой команды не существует");
            } catch (NullPointerException e) {
                responses.add("команда возвращает null набор строк");
            } catch (CommandException e) {
                responses.add(e.getMessage());
            } catch (IOException e) {
                return responses;
            } catch (Exception e) {
                responses.add(e.getMessage() + "\n" + e.getClass());
                return responses;
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
