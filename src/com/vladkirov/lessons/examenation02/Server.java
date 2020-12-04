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
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started...");

            Thread writerThread = new Thread(new Writer());
            writerThread.start();

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    ConnectionNet connection = new ConnectionNet(clientSocket);
                    connections.add(connection);

                    Reader reader = new Reader(connection);
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
                for (ConnectionNet connection : connections) {
                    try {
                        connection.getSocket().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (serverSocket != null) serverSocket.close();
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

    public void removeClientConnectionNet(ConnectionNet connection) {
        if (!connections.isEmpty()) {
            connections.remove(connection);
            System.out.println("Client " + connection.getSender() + " was terminated.");
        }
    }

    private class Reader implements Runnable {
        private ConnectionNet connection;
        private ObjectInputStream input;

        public Reader(ConnectionNet connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Message message;
                    message = (Message) connection.getInput().readObject();
                    connection.setSender(message.getSender());

                    System.out.println(message);

                    if (message.getText().equalsIgnoreCase(stopWord)) {
                        removeClientConnectionNet(connection);
                        break;
                    }

                    messages.put(message);
                }
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                if (connection.getSocket() != null)
                    System.out.println("Connect with " + connection.getSocket() + " is already closed.");
            } finally {
                this.connection.close();
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
                        if (connection.getSender().equalsIgnoreCase(message.getSender()))
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
