package client;

import StorageInterface.StorageInterface;
import api.Request;
import objectSpace.City;

import java.io.*;
import java.nio.ByteBuffer;
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
    public void setStorage(ArrayList storage) {

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


        // начало примера куска серверного кода
        String line = new String(obj);
        System.out.println(line);
        ByteArrayInputStream inputStream1 = new ByteArrayInputStream(obj);
        try {
            ObjectInputStream inputStream = new ObjectInputStream(inputStream1);
            Request request1 = (Request) inputStream.readObject();
            System.out.println(request1.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // конец куска серверного кода
    }

    @Override
    public void update(City city) {

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
