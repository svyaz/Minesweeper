package com.github.svyaz.minesweeper.gamemodel.commands;

/**
 * Команда запустить игру в Свободном режиме.
 */
public class StartFreeGameCommand extends Command {
    private final int rows;
    private final int columns;
    private final int bombsCount;

    public StartFreeGameCommand(int rows, int columns, int bombsCount) {
        this.rows = rows;
        this.columns = columns;
        this.bombsCount = bombsCount;
    }

    @Override
    public void execute() {
        game.startNewGame(rows, columns, bombsCount);
    }
}
