package cli;

import cli.LineReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CommandFileReader implements LineReader {
    private FileReader reader;
    String scriptName;
    public CommandFileReader(String scriptName) {
        this.scriptName=scriptName;
    }

    public void openFile() throws FileNotFoundException {
        this.reader=new FileReader(scriptName);
    }

    @Override
    public String readLine() throws IOException {
        String str = null;
        int ch = this.reader.read();
        if (ch != -1) str = "";

        while (ch != (int)'\r' && ch != -1) {

            if(ch != (int)'\n') {
                str += (char) ch;
            }
            else if(!str.equals("")) {
                break;
            }
            ch = this.reader.read();

        }
        if(str==null) throw new IOException();
        return str;
    }

    public void closeStream() throws IOException {
        reader.close();
    }
}
