package client;

import StorageInterface.StorageInterface;
import api.Request;
import api.RequestNames;
import api.Response;
import storage.City;
import storage.StorageInfo;

import java.io.*;
import java.net.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

public class Client implements StorageInterface {
    private final static int messageSize = 508;
    public final static Duration timeout = Duration.ofSeconds(1);
    private InetAddress address;
    private int port;

    public Client(String host, int port) throws UnknownHostException {
        this.address = InetAddress.getByName(host);
        this.port = port;
    }

    private Response sendRequest(Request request) {
        DatagramSocket socket;
        try {
            socket = new DatagramSocket();
            socket.setSoTimeout((int) timeout.toMillis());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream stream = new ObjectOutputStream(outputStream);
            stream.writeObject(request);
            stream.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        if (outputStream.toByteArray().length > messageSize)
            throw new RuntimeException("реквест не влезает в размер сообщения");

        DatagramPacket dp = new DatagramPacket(outputStream.toByteArray(), outputStream.size(), address, port);
        try {
            socket.send(dp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] buffer = new byte[messageSize];
        DatagramPacket udpResp = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(udpResp);
        } catch (SocketTimeoutException e) {
            System.out.println("таймаут");
            return sendRequest(request);
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
        Request<Object> request = new Request<>(RequestNames.GET_CITIES_LIST);
        Response response = this.sendRequest(request);
        if (!response.isDone())
            System.out.println(response.getError());
        ArrayList<City> citiesList = (ArrayList<City>) response.getData();
        return citiesList;
    }

    @Override
    public void add(City city) {
        Request<City> request = new Request<City>(RequestNames.ADD, city);
        Response response = this.sendRequest(request);
        if (!response.isDone())
            System.out.println(response.getError());
    }

    @Override
    public void update(City city) {
        Request<City> request = new Request<City>(RequestNames.UPDATE, city);
        Response response = this.sendRequest(request);
        if (!response.isDone())
            System.out.println(response.getError());
    }

    @Override
    public void clear() {
        Request<Object> request = new Request<>(RequestNames.CLEAR);
        Response response = this.sendRequest(request);
        if (!response.isDone())
            System.out.println(response.getError());
    }

    @Override
    public void save() throws IOException {

    }

    @Override
    public void load() throws IOException {

    }

    @Override
    public int countGreaterThanCapital(Boolean capital) {
        Request<Boolean> request = new Request<>(RequestNames.COUNT_GREATER_THAN_CAPITAL, capital);
        Response response = this.sendRequest(request);
        if (!response.isDone())
            System.out.println(response.getError());
        return (Integer) response.getData();
    }

    @Override
    public void removeAllByCarCode(Long carCode) {
        Request<City> request = new Request<>(RequestNames.REMOVE_ALL_BY_CAR_CODE);
        Response response = this.sendRequest(request);
        if (!response.isDone())
            System.out.println(response.getError());
    }

    @Override
    public StorageInfo getInfo() {
        Request<City> request = new Request<>(RequestNames.GET_INFO);
        Response response = this.sendRequest(request);
        if (!response.isDone())
            System.out.println(response.getError());
        return (StorageInfo) response.getData();
    }

    @Override
    public void removeById(int id) {
        Request<Integer> request = new Request<>(RequestNames.REMOVE_BY_ID, id);
        Response response = this.sendRequest(request);
        if (!response.isDone())
            System.out.println(response.getError());
    }

    @Override
    public void removeFirst() {
        Request<Object> request = new Request<>(RequestNames.REMOVE_FIRST);
        Response response = this.sendRequest(request);
        if (!response.isDone())
            System.out.println(response.getError());
    }

    @Override
    public void removeGreater(City city) {
        Request<City> request = new Request<>(RequestNames.REMOVE_GREATER, city);
        Response response = this.sendRequest(request);
        if (!response.isDone())
            System.out.println(response.getError());
    }

    @Override
    public void removeLower(City city) {
        Request<City> request = new Request<>(RequestNames.REMOVE_LOWER, city);
        Response response = this.sendRequest(request);
        if (!response.isDone())
            System.out.println(response.getError());
    }

    @Override
    public Long sumOfCarCode() {
        Request<Object> request = new Request<>(RequestNames.SUM_OF_CAR_CODE);
        Response response = this.sendRequest(request);
        if (!response.isDone())
            System.out.println(response.getError());
        return (Long) response.getData();
    }
}
