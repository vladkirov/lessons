package com.vladkirov.lessons.examenation02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArraySet;

public class Server {
    private static int port;
    private static String stopWord;
    private CopyOnWriteArraySet<ConnectionNet> connections;
    private ArrayBlockingQueue<Message> messages;

    public Server() {
        initParametersFromProperties();
        connections = new CopyOnWriteArraySet<>();
        messages = new ArrayBlockingQueue<>(Runtime.getRuntime().availableProcessors(), true);
    }

    public void startWork() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started...");

            Thread writerThread = new Thread(new Writer());
            writerThread.start();

            while (true) {
                try {
                    clientSocket = serverSocket.accept();
                    ConnectionNet ConnectionNet = new ConnectionNet(clientSocket);
                    connections.add(ConnectionNet);

                    Reader reader = new Reader(ConnectionNet);
                    Thread readerThread = new Thread(reader);
                    readerThread.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server stopped...");
            try {
                for (ConnectionNet ConnectionNet : connections) {
                    ConnectionNet.getSocket().close();
                }
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initParametersFromProperties() {
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream("sources/chat/server.properties")) {
            prop.load(input);
            port = Integer.parseInt(prop.getProperty("PORT"));
            stopWord = prop.getProperty("STOP_WORD");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeClientConnectionNet(ConnectionNet ConnectionNet) {
        if (!connections.isEmpty()) connections.remove(ConnectionNet);
    }

    private class Reader implements Runnable {
        private ConnectionNet connection;
        private ObjectInputStream input;

        public Reader(ConnectionNet ConnectionNet) throws IOException {
            this.connection = ConnectionNet;
        }

        @Override
        public void run() {
            while (true) {
                Message message = null;
                try {
                    message = (Message) connection.getInput().readObject();
                    if (message.getText().equalsIgnoreCase(stopWord)) {
                        removeClientConnectionNet(connection);
                        break;
                    }

                    connection.setSender(message.getSender());
                    System.out.println(message);
                    messages.put(message);
                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Writer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Message message = messages.take();
                    for (ConnectionNet connection : connections) {
                        if (connection.getSender() != message.getSender())
                            try {
                                connection.getOutput().writeObject(message);
                                connection.getOutput().flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
