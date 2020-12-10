package com.vladkirov.lessons.examenation03;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Game {
    private final String nickName;
    private final IOWorker loader;
    private String state;

    public Game(String nickName, IOWorker loader) {
        this.nickName = nickName;
        this.loader = loader;
        state = null;
    }

    public void newGame() {
        state = Step.START_STATE;
        resume();
    }

    public void resume() {
        if (state == null) state = Step.START_STATE;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("\n" + Step.STEPS.get(state).getText());

                if (Step.STEPS.get(state).getLinkFirst().equals(Step.FINISH_STATE) ||
                        Step.STEPS.get(state).getLinkFirst().equals(Step.GAME_OVER_STATE)) {
                    state = null;
                    break;
                }

                System.out.println("===== Actions: =====");
                System.out.println("1) - " + Step.STEPS.get(state).getLinkFirst());
                System.out.println("2) - " + Step.STEPS.get(state).getLinkSecond());
                System.out.println("3) - Menu");
                System.out.println("Input action number: ");

                int actionNumber = Integer.parseInt(reader.readLine());
                if (actionNumber == 3) break;
                switch (actionNumber) {
                    case 1 ->
                        state = Step.STEPS.get(state).getLinkFirst();
                    case 2 ->
                        state = Step.STEPS.get(state).getLinkSecond();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
