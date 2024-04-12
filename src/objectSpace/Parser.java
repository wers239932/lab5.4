package objectSpace;

import cli.ArgumentCheker;
import cli.Terminal;

public class Parser<T> {
    T t;
    public T getArgumentWithRules(String msg, Terminal terminal, ArgumentCheker parser) {
        String arg = "";
        terminal.writeLine(msg);
        while (true) {
            try {
                t = (T) parser.parse(terminal.readLine());
                break;
            } catch (Exception e) {
                terminal.writeLine(e.getMessage());
                terminal.writeLine("некорректный ввод, повторите заново");
                terminal.writeLine(msg);
            }
        }
        return t;
    }
}
