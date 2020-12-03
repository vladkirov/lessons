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
    private CopyOnWriteArraySet<Socket> connections;
    private ArrayBlockingQueue<Message> messages;

    public Server() {
        initParametersFromProperties();
        connections = new CopyOnWriteArraySet<>();
        messages = new ArrayBlockingQueue<>(20, true);
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
                    connections.add(clientSocket);
                    Reader reader = new Reader(clientSocket);

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
                for (Socket connection : connections) {
                    connection.close();
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

    public void removeClientConnection(Socket socket) {
        if (!connections.isEmpty()) connections.remove(socket);
    }

    private class Reader implements Runnable {
        private Socket socket;
        private ObjectInputStream input;

        public Reader(Socket socket) throws IOException {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                input = new ObjectInputStream(this.socket.getInputStream());

            while (true) {
                Message message = null;
                try {
                    message = (Message) input.readObject();
                    if (message.getText().equalsIgnoreCase(stopWord)) {
                        removeClientConnection(socket);
                        break;
                    }

                    messages.put(message);
                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class Writer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Message message = messages.take();
                    System.out.println(message);
                    for (Socket connection : connections)
                        sendMessage(message, connection);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendMessage(Message message, Socket socket) {
            try {
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(message);
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
