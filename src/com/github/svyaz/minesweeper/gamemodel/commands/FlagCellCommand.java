package com.github.svyaz.minesweeper.gamemodel.commands;

import lombok.AllArgsConstructor;

/**
 * Комманда установки/снятия флага на закрытую ячейку по указанному строке-столбцу.
 */
@AllArgsConstructor
public class FlagCellCommand extends Command {
    private final int row;
    private final int column;

    @Override
    public void execute() {
        game.flagCell(row, column);
    }
}
