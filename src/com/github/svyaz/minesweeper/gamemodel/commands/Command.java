package com.github.svyaz.minesweeper.gamemodel.commands;

import com.github.svyaz.minesweeper.gamemodel.Game;

public abstract class Command {
    Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    public abstract void execute();
}
