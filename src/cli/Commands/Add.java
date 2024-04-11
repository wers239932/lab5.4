package cli.Commands;

import Exceptions.CommandException;
import cli.Terminal;
import objectSpace.City;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class Add implements Command{
    private Storage storage;
    public Add(Storage storage)
    {
        this.storage = storage;
    }
    @Override
    public ArrayList<String> execute(ArrayList<String> args, Terminal terminal) throws CommandException {
        terminal.writeLine("введите название города");
        String name = terminal.readLine();
        terminal.writeLine("введите число в формате float, первую координату");
        float x;
        while (true) {
            try {
                x = Float.parseFloat(terminal.readLine());
                break;
            } catch (Exception e) {
                terminal.writeLine("некорректный ввод, повторите заново");
            }
        }
        terminal.writeLine("введите число в формате long, вторую координату");
        long y;
        while (true) {
            try {
                y = Long.parseLong(terminal.readLine());
                break;
            } catch (Exception e) {
                terminal.writeLine("некорректный ввод, повторите заново");
            }
        }
        terminal.writeLine("введите площадь в формате Long, площадь должна быть больше 0");
        Long area;
        while (true) {
            try {
                area = City.parseArea(terminal.readLine());
                break;
            } catch (Exception e) {
                terminal.writeLine("некорректный ввод, повторите заново");
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add {element} : добавить новый элемент в коллекцию";
    }

    @Override
    public Boolean getNeedObject() {
        return true;
    }
}
