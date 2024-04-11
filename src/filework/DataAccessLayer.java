package filework;

import objectSpace.City;
import objectSpace.exceptions.*;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class DataAccessLayer {
    private static FileReader reader;
    private static File file;
    public static void setDataAccessLayer(String filename)
    {
        file = new File(filename);
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static String readLine() throws IOException {
        String str = null;
        int ch = reader.read();
        if (ch != -1) str = "";
        else str = null;

        while (ch != (int)'\r' && ch != -1) {

            if(ch != (int)'\n') {
                str += (char) ch;
            }
            else if(!str.equals("")) {
                break;
            }
            ch = reader.read();

        }
        return str;
    }
    public static Storage<City> loadStorage() throws IOException, CoordinatesException, AreaException, GovernmentException, GovernorException, HeightException, CarCodeException, PopulationException, NameCityException, CapitalException {
        Storage<City> storage = new Storage<>();
        String line= readLine();
        line = readLine();
        while(line != null && !line.isEmpty() && !line.isBlank())
        {

            storage.add(City.parseCity(line.trim().split(",")));
            line = readLine();
        }
        reader.close();
        return storage;
    }
    private static Queue<String> createContsentsOfFile(Collection<City> storage)
    {
        Queue<String> contents = new LinkedList<>();
        contents.add("id,name,coordinate_x,coordinate_y,creationDate,area,population,metersAboveSeaLevel,capital,carCode,government,governor_birthday");
        for(City city : storage)
        {
            contents.add(city.toString());
        }
        return contents;
    }
    public void save(String filename, Storage<City> cities) throws IOException {
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filename));
        Queue<String> contents = createContsentsOfFile(cities);
        for(String line : contents)
        {
            outputStream.write(line.getBytes());
            outputStream.write("\n".getBytes());

            outputStream.flush();
        }
        outputStream.close();
    }
}
