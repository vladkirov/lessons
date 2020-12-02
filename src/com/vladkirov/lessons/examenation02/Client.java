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

    public Client(String nickName) {
        initParametersFromProperties();
        this.nickName = nickName;
    }

    private void initParametersFromProperties() {
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream("sources/chat/server.properties")) {
            prop.load(input);
            port = Integer.parseInt(prop.getProperty("PORT"));
            host = prop.getProperty("HOST");
            stopWord = prop.getProperty("STOP_WORD");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startWork() {
        try {
            socket = new Socket(host, port);

            new Thread(new Reader()).start();
            new Thread(new Writer()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Reader implements Runnable {
        @Override
        public void run() {
            try {
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                while (true) {
                    Message message = (Message) input.readObject();
                    System.out.println(message.getSender() + " (" + message.getDateTime() + "): " + message.getText());
                }
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private class Writer implements Runnable {
        @Override
        public void run() {
            try {
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                while (true) {
                    String inputLine = reader.readLine();
                    if (inputLine.equalsIgnoreCase(stopWord)) {
                        output.close();
                        reader.close();
                        break;
                    }
                    output.writeObject(new Message(nickName, inputLine));
                    output.flush();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
