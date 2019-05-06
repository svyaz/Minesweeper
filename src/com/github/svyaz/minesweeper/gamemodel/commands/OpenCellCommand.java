package com.github.svyaz.minesweeper.gamemodel.commands;

/**
 * Команда открытия ячейки по указанным строке-столбцу
 */
public class OpenCellCommand extends Command {
    private final int row;
    private final int column;

    public OpenCellCommand(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public void execute() {
        game.openCell(row, column);
    }
}
