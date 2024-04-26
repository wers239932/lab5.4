package server;

import StorageInterface.StorageInterface;
import api.Request;
import api.RequestNames;
import api.RequestStatus;
import api.Response;
import storage.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Server {
    private DatagramSocket datagramSocket;
    private InetAddress hostAddress;

    private StorageInterface storage;
    private final int messageSize = 508;

    public Server(String host, int port, StorageInterface storage) {
        this.storage = storage;
        try {
            this.hostAddress = InetAddress.getByName(host);
            datagramSocket = new DatagramSocket(port, this.hostAddress);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public void handle() {
        while (true) {
            byte arr[] = new byte[messageSize];
            DatagramPacket datagramPacket = new DatagramPacket(arr, messageSize);
            try {
                this.datagramSocket.receive(datagramPacket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            InetAddress clientAddress = datagramPacket.getAddress();
            int clientPort = datagramPacket.getPort();
            ByteArrayInputStream dataStream = new ByteArrayInputStream(arr);
            Request request;
            try {
                ObjectInputStream objectStream = new ObjectInputStream(dataStream);
                request = (Request) objectStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String commandName = request.getCommandName();
            switch (commandName) {
                case (RequestNames.ADD): {
                    this.storage.add((City) request.getData());
                    Response<Object> response = new Response<>(RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (RequestNames.GET_CITIES_LIST): {
                    Response<ArrayList<City>> response = new Response<>(this.storage.getCitiesList(), RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (RequestNames.UPDATE): {
                    this.storage.update((City) request.getData());
                    Response<Object> response = new Response<>(RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (RequestNames.CLEAR): {
                    this.storage.clear();
                    Response<Object> response = new Response<>(RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (RequestNames.COUNT_GREATER_THAN_CAPITAL): {
                    Capital capital = (Capital) request.getData();
                    Response<Integer> response = new Response<>(this.storage.countGreaterThanCapital(capital.getCapital()), RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (RequestNames.REMOVE_ALL_BY_CAR_CODE): {
                    CarCode carCode = (CarCode) request.getData();
                    this.storage.removeAllByCarCode(carCode.getCarCode());
                    Response<Object> response = new Response<>(RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (RequestNames.GET_INFO): {
                    StorageInfo storageInfo = this.storage.getInfo();
                    Response<StorageInfo> response = new Response<>(storageInfo, RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (RequestNames.REMOVE_BY_ID): {
                    ID id = (ID) request.getData();
                    this.storage.removeById(id.getId());
                    Response<Object> response = new Response<>(RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (RequestNames.REMOVE_FIRST): {
                    this.storage.removeFirst();
                    Response<Object> response = new Response<>(RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (RequestNames.REMOVE_GREATER): {
                    City city = (City) request.getData();
                    this.storage.removeGreater(city);
                    Response<Object> response = new Response<>(RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (RequestNames.REMOVE_LOWER): {
                    City city = (City) request.getData();
                    this.storage.removeLower(city);
                    Response<Object> response = new Response<>(RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (RequestNames.SUM_OF_CAR_CODE): {
                    Long sum = this.storage.sumOfCarCode();
                    Response<Long> response = new Response<>(sum, RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (RequestNames.GET_COMMAND_ARRAY): {
                    break;
                }
            }
        }

    }

    public void sendReply(Response object, InetAddress address, int port) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        byte[] sendData = byteArrayOutputStream.toByteArray();
        int len = sendData.length;
        try {
            if (len > messageSize){
                throw new Exception("реквест не влезает в размер сообщения");
            }
            DatagramPacket datagramPacket = new DatagramPacket(sendData, len, address, port);
            this.datagramSocket.send(datagramPacket);
        } catch (Exception e) {
            this.sendReply(new Response<>(RequestStatus.FAILED, e.getMessage()), address, port);
            System.out.println(e.getMessage());
        }
    }
}
