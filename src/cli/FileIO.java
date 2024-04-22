package cli;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO implements IOInterface{
    private Scanner file;
    public FileIO(String filename) throws FileNotFoundException {

        this.file = new Scanner(new FileReader(filename));
    }
    @Override
    public void writeLine(String line) {

    }

    @Override
    public void writeResponse(ArrayList<String> response) {

    }

    @Override
    public String readLine() {
        String line = file.nextLine();
        return line;
    }

    @Override
    public Boolean isInteractive() {
        return false;
    }
}
