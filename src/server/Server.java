package server;

import StorageInterface.StorageInterface;
import api.*;
import storage.*;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.System.in;

public class Server {
    private DatagramSocket datagramSocket;
    private InetAddress hostAddress;
    private StorageInterface storage;
    public final static Duration timeout = Duration.ofMillis(50);
    private Scanner scanner;
    private Logger logger = Logger.getLogger("MyLog");

    public Server(String host, int port, StorageInterface storage) {
        this.scanner = new Scanner(in);
        this.storage = storage;
        try {
            this.hostAddress = InetAddress.getByName(host);
            datagramSocket = new DatagramSocket(port, this.hostAddress);
            this.datagramSocket.setSoTimeout((int) timeout.toMillis());
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        Logger logger = Logger.getLogger("MyLog");
        try {
            FileHandler fh = new FileHandler("ItsLogTime.log", true);
            logger.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());
            logger.info("сервер создан");
        } catch (SecurityException | IOException e) {
            logger.log(Level.SEVERE, "Произошла ошибка при работе с FileHandler.", e);
        }
    }

    public void handle() {
        while (true) {
            int available;
            try {
                available = System.in.available();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (available > 0) {
                String command = scanner.nextLine();
                switch (command) {
                    case ("exit"): {
                        try {
                            storage.save();
                            System.out.println("коллекция сохранена");
                            logger.fine("коллекция сохранена");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("завершение работы");
                        logger.info("выключение сервера");
                        System.exit(0);
                    }
                    case ("save"): {
                        try {
                            storage.save();
                            System.out.println("коллекция сохранена");
                            logger.fine("коллекция сохранена");
                        } catch (IOException e) {
                            logger.severe("ошибка сохранения");
                            throw new RuntimeException(e);
                        }
                    }
                }
            } else {
                try {
                    byte arr[] = new byte[ProtocolInfo.messageSize];
                    DatagramPacket datagramPacket = new DatagramPacket(arr, ProtocolInfo.messageSize);
                    try {
                        this.datagramSocket.receive(datagramPacket);
                    } catch (SocketTimeoutException e) {
                        throw new NoMessageException();
                    } catch (IOException e) {
                        logger.severe("ошибка получения сообщения по сети");
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
                        logger.severe("сообщение клиента не может быть прочитано");
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
                            logger.info("выполнена команда add");
                            break;
                        }
                        case (RequestNames.GET_CITIES_LIST): {
                            Response<ArrayList<City>> response = new Response<>(this.storage.getCitiesList(), RequestStatus.DONE, null);
                            try {
                                this.sendReply(response, clientAddress, clientPort);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            logger.info("выполнена команда add");
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
                            logger.info("выполнена команда add");
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
                            logger.info("выполнена команда add");
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
                            logger.info("выполнена команда add");
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
                            logger.info("выполнена команда add");
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
                            logger.info("выполнена команда add");
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
                            logger.info("выполнена команда add");
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
                            logger.info("выполнена команда add");
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
                            logger.info("выполнена команда add");
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
                            logger.info("выполнена команда add");
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
                            logger.info("выполнена команда add");
                            break;
                        }
                        case (RequestNames.GET_COMMAND_ARRAY): {
                            break;
                        }
                    }
                } catch (NoMessageException e) {

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
        Random random = new Random();
        int id = random.nextInt();
        int dataSize = ProtocolInfo.messageSize - ProtocolInfo.headerSize;
        byte total = (byte) ((len + dataSize - 1) / dataSize);
        byte index = 0;
        while (index < total) {
            ByteArrayOutputStream part = new ByteArrayOutputStream();
            byte[] bytes = ByteBuffer.allocate(4).putInt(id).array();
            part.write(bytes);
            part.write(total);
            part.write(index);
            int end = (index + 1) * dataSize;
            if (end > len) end = len;
            part.write(Arrays.copyOfRange(sendData, index * dataSize, end));
            index++;
            DatagramPacket datagramPacket = new DatagramPacket(part.toByteArray(), part.size(), address, port);
            this.datagramSocket.send(datagramPacket);
        }
    }
}
