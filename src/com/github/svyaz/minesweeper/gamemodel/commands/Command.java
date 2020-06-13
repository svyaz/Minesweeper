package com.github.svyaz.minesweeper.gamemodel.commands;

import com.github.svyaz.minesweeper.gamemodel.Game;
import lombok.Setter;

public abstract class Command {
    @Setter
    Game game;

    public abstract void execute();
}
