package storage;

import StorageInterface.StorageInterface;
import dal.DataAccessLayer;
import objectSpace.City;
import objectSpace.CityNameComparator;
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

        this.creationDate = new Date();
    }

    @Override
    public void load() throws IOException {
        ArrayList<String> records = this.dataAccessLayer.readAllRecords();
        City city;
        for (String record : records) {
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
        for (City city : this.objects) {
            contents.add(city.toString());
        }
        dataAccessLayer.writeAllRecords(contents);
    }

    @Override
    public ArrayList<City> getCitiesList() {
        CityNameComparator comparator = new CityNameComparator();
        this.objects.sort(comparator);
        return this.objects;
    }


    @Override
    public void add(City city) {
        this.objects.add(city);
    }

    @Override
    public void update(City city) {
        for (City cityStored : this.objects) {
            if (cityStored.getId() == city.getId()) {
                this.objects.remove(cityStored);
                this.objects.add(city);
            }
        }
    }


    @Override
    public void clear() {
        this.objects.clear();
    }

    @Override
    public int countGreaterThanCapital(Boolean capital) {
        int amount = 0;
        for (City cityStored : this.objects) {
            if (cityStored.getCapital().compareTo(capital) > 0) {
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
        for (City cityStored : this.objects) {
            if (cityStored.getCarCode() == carCode) {
                this.objects.remove(cityStored);
            }
        }
    }

    @Override
    public void removeById(int id) {
        for (City cityStored : this.objects) {
            if (cityStored.getId() == id) {
                this.objects.remove(cityStored);
            }
        }
    }

    @Override
    public void removeFirst() {
        if (!this.objects.isEmpty())
            this.objects.remove(0);
    }

    @Override
    public void removeGreater(City city) {
        for (City cityStored : this.objects) {
            if (cityStored.compareTo(city) > 0) {
                this.objects.remove((City) cityStored);
            }
        }
    }

    @Override
    public void removeLower(City city) {
        for (City cityStored : this.objects) {
            if (cityStored.compareTo(city) < 0) {
                this.objects.remove((City) cityStored);
            }
        }
    }

    @Override
    public Long sumOfCarCode() {
        Long sum = 0L;
        for (City cityStored : this.objects) {
            if (cityStored.getCarCode() != null)
                sum += cityStored.getCarCode();
        }
        return sum;
    }
}
