package cli;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public interface IOInterface {
    public void writeLine(String line);
    public void writeResponse(ArrayList<String> response);
    public String readLine() throws IOException;
    public Boolean isInteractive();
}
