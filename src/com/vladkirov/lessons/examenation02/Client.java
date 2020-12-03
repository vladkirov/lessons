package com.vladkirov.lessons.examenation02;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

public class Client {
    private final String nickName;
    private static String host;
    private static int port;
    private static String stopWord;
    private Socket socket;
    private ObjectOutputStream output;
    private BufferedReader reader;
    private ObjectInputStream input;

    public Client(String nickName) {
        initParametersFromProperties();
        this.nickName = nickName;

        try {
            socket = new Socket(host, port);
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
            readerThread = new Thread(new Reader());
            readerThread.start();

            writerThread = new Thread(new Writer());
            writerThread.start();

            writerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Reader implements Runnable {
        @Override
        public void run() {
            try {
                Client.this.input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    Message message = (Message) Client.this.input.readObject();
                    System.out.println(message.getSender() + " (" + message.getDateTime() + "): " + message.getText());
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    private class Writer implements Runnable {
        @Override
        public void run() {
            try {
                Client.this.output = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Client.this.reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                try {
                    String inputLine = Client.this.reader.readLine();
                    if (inputLine.equalsIgnoreCase(stopWord)) {
                        Client.this.output.close();
                        Client.this.reader.close();
                        break;
                    }

                    Client.this.output.writeObject(new Message(Client.this.nickName, inputLine));
                    Client.this.output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
