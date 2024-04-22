package objectSpace;

import cli.ArgumentCheker;
import cli.LineReader;
import cli.Terminal;

import java.io.IOException;

public class Parser<T> {
    T t;
    public T getArgumentWithRules(String msg, Terminal terminal, LineReader lineReader, ArgumentCheker parser) throws IOException {
        String arg = "";
        terminal.writeLine(msg);
        while (true) {
            try {
                t = (T) parser.parse(lineReader.readLine());
                break;
            } catch (IOException e)
            {
                throw new IOException(e);
            }
            catch (Exception e) {
                terminal.writeLine(e.getMessage());
                terminal.writeLine("некорректный ввод, повторите заново");
                terminal.writeLine(msg);
            }
        }
        return t;
    }
}
