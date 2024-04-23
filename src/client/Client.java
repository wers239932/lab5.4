package client;

import StorageInterface.StorageInterface;
import objectSpace.City;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Client implements StorageInterface {
    @Override
    public ArrayList getStorage() {
        return null;
    }

    @Override
    public Date getCreationDate() {
        return null;
    }

    @Override
    public void setStorsge(ArrayList storage) {

    }

    @Override
    public void add(City city) {

    }

    @Override
    public void remove(City city) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void save() throws IOException {

    }

    @Override
    public void load() throws IOException {

    }
}
