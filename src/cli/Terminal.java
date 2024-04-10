package cli;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Terminal {
    private final Scanner sc;
    public void writeLine(String line)
    {
        System.out.println(line);
    }
    public void writeResponse(ArrayList<String> response)
    {
        for(String line : response)
        {
            this.writeLine(line);
        }
    }
    public Terminal()
    {
        this.sc=new Scanner(System.in);
    }

    public String readLine()
    {
        return this.sc.nextLine();
    }

    public void closeStream() throws IOException {
        sc.close();
    }
}
