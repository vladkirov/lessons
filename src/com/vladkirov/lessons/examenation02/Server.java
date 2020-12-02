package com.vladkirov.lessons.examenation02;

import com.vladkirov.lessons.lesson26.Signal;

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
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started...");

            new Thread(new Writer()).start();

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    connections.add(clientSocket);
                    Reader reader = new Reader(clientSocket);

                    new Thread(reader).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server stopped...");
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

    private class Reader implements Runnable, AutoCloseable {
        private Socket socket;
        private ObjectInputStream input;

        public Reader(Socket socket) throws IOException {
            this.socket = socket;
            input = new ObjectInputStream(this.socket.getInputStream());
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Message message = readMessage();
                    if (message.getText().equalsIgnoreCase(stopWord)) {
                        close();
                        break;
                    }
                    messages.put(message);
                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public Message readMessage() throws IOException, ClassNotFoundException {
            return (Message) input.readObject();
        }

        @Override
        public void close() throws IOException {
            input.close();
            socket.close();
            removeClientConnection(this.socket);
        }
    }

    private class Writer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Message message = messages.take();
                    for (Socket connection : connections)
                        sendMessage(message, connection);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void sendMessage(Message message, Socket socket) {
            ObjectOutputStream output = null;
            try {
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(message);
                output.flush();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
