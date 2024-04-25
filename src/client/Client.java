package client;

import StorageInterface.StorageInterface;
import api.Request;
import api.RequestNames;
import api.Response;
import objectSpace.City;
import objectSpace.StorageInfo;
import сommands.Add;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;

public class Client implements StorageInterface {
    private final static int bufferSize = 60000;
    private InetAddress address;
    private int port;
    public Client(String host, int port) throws UnknownHostException {
        this.address = InetAddress.getByName(host);
        this.port = port;
    }
    private Response sendRequest(Request request)
    {
        DatagramSocket socket;
        try {
            socket = new DatagramSocket();

        }  catch (SocketException e) {
            throw new RuntimeException(e);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream stream = new ObjectOutputStream(outputStream);
            stream.writeObject(request);
            stream.flush();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }

        DatagramPacket dp = new DatagramPacket(outputStream.toByteArray(), outputStream.size(), address, port);
        try {
            socket.send(dp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] buffer = new byte[bufferSize];
        DatagramPacket udpResp = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(udpResp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buffer);
        try {
            ObjectInputStream inputStream1 = new ObjectInputStream(inputStream);
            Response response = (Response) inputStream1.readObject();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ArrayList<City> getCitiesList() {
        Request<City> request = new Request<City>(RequestNames.GET_CITIES_LIST, null);
        Response response = this.sendRequest(request);
        ArrayList<City> citiesList = (ArrayList<City>) response.getData();
        return citiesList;
    }

    @Override
    public void add(City city) {
        Request<City> request = new Request<City>(RequestNames.ADD, city);
        Response response = this.sendRequest(request);
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
