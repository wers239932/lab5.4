package storage;

import StorageInterface.StorageInterface;
import dal.DataAccessLayer;
import objectSpace.City;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Storage implements StorageInterface {
    private ArrayList<City> objects;
    private Date creationDate;
    private DataAccessLayer dataAccessLayer;
    public Storage(DataAccessLayer dataAccessLayer) throws IOException {
        this.objects = new ArrayList<>();
        this.dataAccessLayer = dataAccessLayer;
        this.load();

        this.creationDate=new Date();
    }
    @Override
    public Date getCreationDate() {
        return creationDate;
    }
    @Override
    public void load() throws IOException {
        ArrayList<String> records = this.dataAccessLayer.readAllRecords();
        City city;
        for(String record: records)
        {
            try {
                city = City.parseCity(record.split(","));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.objects.add(city);
        }
    }
    @Override
    public void save() throws IOException {
        ArrayList<String> contents = new ArrayList<>();
        contents.add("id,name,coordinate_x,coordinate_y,creationDate,area,population,metersAboveSeaLevel,capital,carCode,government,governor_birthday");
        for(City city : this.objects)
        {
            contents.add(city.toString());
        }
        dataAccessLayer.writeAllRecords(contents);
    }
    public ArrayList<City> getAllCitiesList()
    {
        return objects;
    }

    @Override
    public ArrayList<City> getStorage() {
        return this.objects;
    }

    @Override
    public void setStorage(ArrayList storage) {
        this.objects = storage;
    }

    @Override
    public void add(City city) {
        this.objects.add(city);
    }

    @Override
    public void remove(City city) {
        this.objects.remove(city);
    }
    @Override
    public void clear()
    {
        this.objects.clear();
    }
}
