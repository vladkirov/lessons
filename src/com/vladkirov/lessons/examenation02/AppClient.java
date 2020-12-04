package com.vladkirov.lessons.examenation02;

public class AppClient {
    public static void main(String[] args) {
        Client client = new Client("VK" + (int) (Math.random() * 10));
        client.startWork();
    }
}
