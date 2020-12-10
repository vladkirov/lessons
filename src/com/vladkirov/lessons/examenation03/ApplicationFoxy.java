package com.vladkirov.lessons.examenation03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApplicationFoxy {
    public static void main(String[] args) {
        System.out.println("\nWelcome to game FoXY!");

        IOWorker loader = new FileWorker();
        Game game = new Game(inputNickName(), loader);

        CommandNewGame commandNewGame = new CommandNewGame(game);
        CommandLoadGame commandLoadGame = new CommandLoadGame(game);
        CommandSaveGame commandSaveGame = new CommandSaveGame(game);
        CommandResumeGame commandResumeGame = new CommandResumeGame(game);

        Menu menu = new Menu();

        menu.setCommand(1, commandNewGame);
        menu.setCommand(2, commandLoadGame);
        menu.setCommand(3, commandSaveGame);
        menu.setCommand(4, commandResumeGame);

        menu.show();
    }

    private static String inputNickName() {
        System.out.println("\nInput nickName: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String nickName = null;
        try {
            nickName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nickName;
    }
}
