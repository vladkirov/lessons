package com.vladkirov.lessons.examenation03;

public class CommandLoadGame implements Command {
    private final Game game;

    public CommandLoadGame(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.load();
    }
}
