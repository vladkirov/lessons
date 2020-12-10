package com.vladkirov.lessons.examenation03;

public class CommandResumeGame implements Command {
    private final Game game;

    public CommandResumeGame(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.resume();
    }
}