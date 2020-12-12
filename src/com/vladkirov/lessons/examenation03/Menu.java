package com.vladkirov.lessons.examenation03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Independent object drawing Menu of game
 */
public class Menu {
    private final Map<Integer, Command> commandMap;
    private static final int COUNT_ITEMS = 5;

    public Menu() {
        commandMap = new HashMap<>();
    }

    public void setCommand(int slot, Command command) {
        commandMap.put(slot, command);
    }

    public Command getCommand(int slot) {
        return commandMap.get(slot);
    }

    private void printItems() {
        System.out.println("\n---------- Menu: ----------");
        System.out.println("1) New game");
        System.out.println("2) Load game");
        System.out.println("3) Save current game");
        System.out.println("4) Resume current game");
        System.out.println("5) Exit");
        System.out.println("Input number menu item:");
    }

    /**
     * Lopped show menu for input action
     */
    public void show() {
        printItems();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                int numberAction = Integer.parseInt(reader.readLine());

                if (numberAction == COUNT_ITEMS) break;
                if (numberAction >= 1 && numberAction < COUNT_ITEMS) {
                    getCommand(numberAction).execute();
                    printItems();
                } else System.out.println("Incorrect save number entered. Repeat please!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}