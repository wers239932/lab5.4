package server;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server {
    private DatagramChannel datagramChannel;
    private InetSocketAddress address;
    private SocketAddress client;
    private int port;
    public Server(int port)
    {
        this.port = port;
        try {
            this.address = new InetSocketAddress(port);
            this.datagramChannel = DatagramChannel.open();
            this.datagramChannel.bind(address);
        } catch(BindException e){
            System.out.println("Порт занят. Выберете другой порт");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void sendMessage(Serializable object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        byte[] sendData = byteArrayOutputStream.toByteArray();
        ByteBuffer buf = ByteBuffer.wrap(sendData);
        datagramChannel.send(buf, client);
    }
}
