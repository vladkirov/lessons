package com.vladkirov.lessons.examenation03;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Main and most famous class in project. This is Game!
 */
public class Game {
    private final String nickName;
    private final IOWorker loader;
    /**
     * Current state of game for loading, saving, calculation. Can be replaced with Object in Future.
     */
    private String state;

    public Game(String nickName, IOWorker loader) {
        this.nickName = nickName;
        this.loader = loader;
        state = null;
    }

    public void newGame() {
        state = Chain.START_STATE;
        resume();
    }

    public void resume() {
        IStep step = Chain.factoryChainSteps(state);
        if (step != null) state = step.handleRequest();
    }

    public void load() {
        System.out.println("\nAvailable saves:");
        List<SaveGame> saves = loader.getListSaves(nickName);
        for (int i = 0; i < saves.size(); i++)
            System.out.println(i + ") Name: " + saves.get(i).getName()
                    + ", time: " + saves.get(i).getDateTimeSave().format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss")));

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("\nInput save number (negative number for exit!): ");

                int inputSaveNumber = Integer.parseInt(reader.readLine());
                if (inputSaveNumber < saves.size() && inputSaveNumber >= 0) {
                    state = saves.get(inputSaveNumber).getState();
                    System.out.println("Save <" + saves.get(inputSaveNumber).getName() + "> loaded.");
                    resume();
                    break;
                } else if (inputSaveNumber < 0)
                    break;
                else
                    System.out.println("Incorrect save number entered. Repeat please!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        loader.saveGame(new SaveGame(nickName, state, LocalDateTime.now()));
    }
}
