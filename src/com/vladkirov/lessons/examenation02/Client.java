package com.vladkirov.lessons.examenation02;

import java.io.*;
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class Client {
    private final String nickName;
    private static String host;
    private static int port;
    private static String stopWord;

    private ConnectionNet connection;

    public Client(String nickName) {
        initParametersFromProperties();
        this.nickName = nickName;

        try {
            connection = new ConnectionNet(new Socket(host, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initParametersFromProperties() {
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream("sources/chat/client.properties")) {
            prop.load(input);
            port = Integer.parseInt(prop.getProperty("PORT"));
            host = prop.getProperty("HOST");
            stopWord = prop.getProperty("STOP_WORD");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startWork() {
        Thread writerThread, readerThread;
        try {
            writerThread = new Thread(new Writer());
            writerThread.start();

            readerThread = new Thread(new Reader());
            readerThread.start();

            System.out.println("Client " + nickName + " started...");
            writerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Reader implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Message message = (Message) connection.getInput().readObject();
                    System.out.println(message.getSender() + " ("
                            + message.getDateTime().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss")) + "): "
                            + message.getText());
                }
            } catch (IOException | ClassNotFoundException ioException) {
                System.out.println("Connection is broken.");
            }
        }
    }

    private class Writer implements Runnable {
        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                while (true) {
                    String inputLine = reader.readLine();
                    connection.getOutput().writeObject(new Message(Client.this.nickName, inputLine));
                    connection.getOutput().flush();

                    if (inputLine.equalsIgnoreCase(stopWord)) {
                        connection.close();
                        reader.close();
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
