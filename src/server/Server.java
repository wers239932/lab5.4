package server;

import java.io.*;
import java.net.*;
import java.nio.channels.DatagramChannel;

public class Server {
    private DatagramChannel datagramChannel;
    private InetSocketAddress address;
    private Socket client;
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
    public void catchClient() throws IOException {
        this.client = datagramChannel.connect();
    }
    public void sendMessage(Serializable object) throws IOException {
        BufferedOutputStream writer = new BufferedOutputStream(client.getOutputStream());
        ObjectOutputStream outputStream = new ObjectOutputStream(writer);
        outputStream.writeObject(object);
        outputStream.flush();
    }
}
