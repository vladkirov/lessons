package com.vladkirov.lessons.examenation03;

public class CommandSaveGame implements Command {
    private final Game game;

    public CommandSaveGame(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.save();
    }
}
