package client;

import StorageInterface.StorageInterface;
import api.Request;
import objectSpace.City;
import objectSpace.StorageInfo;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;

public class Client implements StorageInterface {
    @Override
    public ArrayList<City> getCitiesList() {
        return null;
    }


    @Override
    public void add(City city) {
        Request<City> request = new Request<City>("add", city);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream stream = new ObjectOutputStream(outputStream);
            stream.writeObject(request);
            stream.flush();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return;
        }
        byte[] obj = outputStream.toByteArray();
    }

    @Override
    public void update(City city) {

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

    @Override
    public int countGreaterThanCapital(Boolean capital) {
        return 0;
    }

    @Override
    public void removeAllByCarCode(Long carCode) {

    }

    @Override
    public StorageInfo getInfo() {
        return null;
    }

    @Override
    public void removeById(int id) {

    }

    @Override
    public void removeFirst() {

    }

    @Override
    public void removeGreater(City city) {

    }

    @Override
    public void removeLower(City city) {

    }

    @Override
    public Long sumOfCarCode() {
        return null;
    }
}
