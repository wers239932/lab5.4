package cli.Commands;

import cli.Terminal;
import objectSpace.ArgumentCheker;
import objectSpace.City;

import java.io.IOException;
import java.util.function.Function;

public class Parser<T> {
    T t;
    public T getArgumentWithRules(String msg, Terminal terminal, Function parser) {
        String arg = "";
        terminal.writeLine(msg);
        while (true) {
            try {
                t = (T) parser.apply(terminal.readLine());
                break;
            } catch (Exception e) {
                terminal.writeLine(e.getMessage());
                terminal.writeLine("некорректный ввод, повторите заново");
            }
        }
        return t;
    }
}
