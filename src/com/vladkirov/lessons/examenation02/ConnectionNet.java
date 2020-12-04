package com.vladkirov.lessons.examenation02;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionNet {
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String sender;

    public ConnectionNet(Socket socket) {
        this.socket = socket;
        try {
            output = new ObjectOutputStream(this.socket.getOutputStream());
            input = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public void close() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (sender != null) System.out.println("Connect with " + sender + " was closed.");
        }
    }
}
