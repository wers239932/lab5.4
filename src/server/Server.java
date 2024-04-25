package server;

import StorageInterface.StorageInterface;
import api.Request;
import api.RequestStatus;
import api.Response;
import objectSpace.*;
import storage.Storage;

import java.io.*;
import java.net.*;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;

import static api.RequestNames.*;

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

        /*try {
            this.address = new InetSocketAddress(port);
            this.datagramChannel = DatagramChannel.open();
            this.datagramChannel.bind(address);
        } catch(BindException e){
            System.out.println("Порт занят. Выберете другой порт");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
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
            ByteArrayInputStream inputStream1 = new ByteArrayInputStream(arr);
            Request request;
            try {
                ObjectInputStream inputStream = new ObjectInputStream(inputStream1);
                request = (Request) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String commandName = request.getCommandName();
            switch (commandName) {
                case (ADD): {
                    this.storage.add((City) request.getData());
                    Response<Object> response = new Response<>(RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (GET_CITIES_LIST): {
                    Response<ArrayList<City>> response = new Response<>(this.storage.getCitiesList(), RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (UPDATE): {
                    this.storage.update((City) request.getData());
                    Response<Object> response = new Response<>(RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (CLEAR): {
                    this.storage.clear();
                    Response<Object> response = new Response<>(RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (COUNT_GREATER_THAN_CAPITAL): {
                    Capital capital = (Capital) request.getData();
                    Response<Integer> response = new Response<>(this.storage.countGreaterThanCapital(capital.getCapital()), RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (REMOVE_ALL_BY_CAR_CODE): {
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
                case (GET_INFO): {
                    StorageInfo storageInfo = this.storage.getInfo();
                    Response<StorageInfo> response = new Response<>(storageInfo, RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (REMOVE_BY_ID): {
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
                case (REMOVE_FIRST): {
                    this.storage.removeFirst();
                    Response<Object> response = new Response<>(RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (REMOVE_GREATER): {
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
                case (REMOVE_LOWER): {
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
                case (SUM_OF_CAR_CODE): {
                    Long sum = this.storage.sumOfCarCode();
                    Response<Long> response = new Response<>(sum, RequestStatus.DONE, null);
                    try {
                        this.sendReply(response, clientAddress, clientPort);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case (GET_COMMAND_ARRAY): {
                    break;
                }
            }
        }

        /*
         * читаем байты и объект реквест из udp
         * читаем имя команды
         * по имени понимаем какой класс ожидаем в поле дата
         * вызываем функцию соответствующую команде с нужными параметрами
         * */
    }

    public void sendReply(Serializable object, InetAddress address, int port) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        byte[] sendData = byteArrayOutputStream.toByteArray();
        int len = sendData.length;
        DatagramPacket datagramPacket = new DatagramPacket(sendData, len, address, port);
        this.datagramSocket.send(datagramPacket);
    }
    /*public Object getMessage()
    {
        this.datagramChannel.receive(new ByteBuffer());
        byte[] data = this.datagramChannel.receive();
        ByteBuffer buf = ByteBuffer.wrap(data);
    }*/
}
