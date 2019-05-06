package com.github.svyaz.minesweeper.gamemodel.commands;

/**
 * Комманда установки/снятия флага на закрытую ячейку по указанному строке-столбцу.
 */
public class FlagCellCommand extends Command {
    private final int row;
    private final int column;

    public FlagCellCommand(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public void execute() {
        game.flagCell(row, column);
    }
}
