package server;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server {
    private DatagramChannel datagramChannel;
    private InetSocketAddress address;
    private SocketAddress clientSocket;
    public Server(int port)
    {
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
    public void handle(){
        /*
        * читаем байты и объект реквест из udp
        * читаем имя команды
        * по имени понимаем какой класс ожидаем в поле дата
        * вызываем функцию соответствующую команде с нужными параметрами
        * */
    }
    public void sendReply(Serializable object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        byte[] sendData = byteArrayOutputStream.toByteArray();
        datagramChannel.send(ByteBuffer.wrap(sendData), clientSocket);
    }
    /*public Object getMessage()
    {
        this.datagramChannel.receive(new ByteBuffer());
        byte[] data = this.datagramChannel.receive();
        ByteBuffer buf = ByteBuffer.wrap(data);
    }*/
}
