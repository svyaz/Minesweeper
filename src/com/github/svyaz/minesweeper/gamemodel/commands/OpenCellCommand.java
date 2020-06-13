package com.github.svyaz.minesweeper.gamemodel.commands;

import lombok.AllArgsConstructor;

/**
 * Команда открытия ячейки по указанным строке-столбцу
 */
@AllArgsConstructor
public class OpenCellCommand extends Command {
    private final int row;
    private final int column;

    @Override
    public void execute() {
        game.openCell(row, column);
    }
}
