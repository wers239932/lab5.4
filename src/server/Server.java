package server;

import api.Request;
import objectSpace.City;
import storage.Storage;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.zip.CheckedInputStream;

public class Server {
    private DatagramChannel datagramChannel;
    private InetSocketAddress address;
    private SocketAddress clientSocket;
    private DatagramSocket datagramSocket;
    InetAddress hostAddress;

    private Storage storage;
    private HashMap<String, Class> classCommandMap;
    public Server(int port)
    {
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        this.classCommandMap = new HashMap<>();
        this.classCommandMap.put("add", City.class);
        this.classCommandMap.put("getStorage", null);

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
    public void handle(){
        byte arr[] = new byte[60000];
        DatagramPacket datagramPacket = new DatagramPacket(arr, 60000);
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
        switch (commandName){
            case ("add"): {
                this.storage.add((City) request.getData());;
            }
            case ("getStorage"): {
                try {
                    this.sendReply(storage,clientAddress, clientPort);
                } catch (IOException e) {
                    throw new RuntimeException(e);
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
        /*ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        byte[] sendData = byteArrayOutputStream.toByteArray();
        datagramChannel.send(ByteBuffer.wrap(sendData), clientSocket);*/
    }
    /*public Object getMessage()
    {
        this.datagramChannel.receive(new ByteBuffer());
        byte[] data = this.datagramChannel.receive();
        ByteBuffer buf = ByteBuffer.wrap(data);
    }*/
}
