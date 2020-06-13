package com.github.svyaz.minesweeper.gamemodel.commands;

import lombok.AllArgsConstructor;

/**
 * Команда запустить игру в Свободном режиме.
 */
@AllArgsConstructor
public class StartFreeGameCommand extends Command {
    private final int rows;
    private final int columns;
    private final int bombsCount;

    @Override
    public void execute() {
        game.startNewGame(rows, columns, bombsCount);
    }
}
