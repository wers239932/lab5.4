import java.io.*;

public class DataAccessLayer {
    private FileReader reader;
    private File file;
    private String filename;
    public DataAccessLayer(String filename){
        this.filename = filename;
        this.file = new File(filename);
        try {
            this.reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    public String readRecord() throws IOException {
        String str = null;
        int ch = this.reader.read();
        if (ch != -1) str = "";
        else str = null;

        while (ch != (int)'\r' && ch != -1) {

            if(ch != (int)'\n') {
                str += (char) ch;
            }
            else if(!str.equals("")) {
                break;
            }
            ch = this.reader.read();

        }
        return str;
    }
    public void writeRecord(String line) throws IOException {
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filename));
        outputStream.write(line.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
