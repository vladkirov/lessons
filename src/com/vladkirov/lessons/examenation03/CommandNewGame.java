package com.vladkirov.lessons.examenation03;

public class CommandNewGame implements Command {
    private final Game game;

    public CommandNewGame(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.newGame();
    }
}
