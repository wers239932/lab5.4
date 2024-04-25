package storage;

import StorageInterface.StorageInterface;
import dal.DataAccessLayer;
import objectSpace.City;
import objectSpace.StorageInfo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Storage implements StorageInterface, Serializable {
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
    public ArrayList<City> getCitiesList() {
        return this.objects;
    }


    @Override
    public void add(City city) {
        this.objects.add(city);
    }

    @Override
    public void update(City city) {
        for(Object city2: this.objects)
        {
            City city1=(City) city2;
            if(city1.getId()==city.getId())
            {
                this.objects.remove((City) city2);
                this.objects.add(city);
            }
        }
    }


    @Override
    public void clear()
    {
        this.objects.clear();
    }
    @Override
    public int countGreaterThanCapital(Boolean capital)
    {
        int amount=0;
        for(Object city2: this.objects)
        {
            City city1=(City) city2;
            if(city1.getCapital().compareTo(capital)>0)
            {
                amount++;
            }
        }
        return amount;
    }
    @Override
    public StorageInfo getInfo() {
        return new StorageInfo(this.objects.size(), this.creationDate);
    }
    public void removeAllByCarCode(Long carCode) {
        for(Object city2: this.objects)
        {
            City city1=(City) city2;
            if(city1.getCarCode()==carCode)
            {
                this.objects.remove((City) city2);
            }
        }
    }
    @Override
    public void removeById(int id) {
        for(Object city2:this.objects)
        {
            City city1=(City) city2;
            if(city1.getId()==id)
            {
                this.objects.remove((City) city2);
            }
        }
    }
    @Override
    public void removeFirst() {
        if(!this.objects.isEmpty())
            this.objects.remove(0);
    }
    @Override
    public void removeGreater(City city){
        for(Object city2: this.objects)
        {
            City city1=(City) city2;
            if(city1.compareTo(city)>0)
            {
                this.objects.remove((City) city2);
            }
        }
    }
    @Override
    public void removeLower(City city){
        for(Object city2: this.objects)
        {
            City city1=(City) city2;
            if(city1.compareTo(city)<0)
            {
                this.objects.remove((City) city2);
            }
        }
    }
    @Override
    public Long sumOfCarCode(){
        Long sum= 0L;
        for(Object city2:this.objects)
        {
            City city1=(City) city2;
            if(city1.getCarCode()!=null)
                sum+=city1.getCarCode();
        }
        return sum;
    }
}
