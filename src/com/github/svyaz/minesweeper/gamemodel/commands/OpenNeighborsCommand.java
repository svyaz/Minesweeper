package com.github.svyaz.minesweeper.gamemodel.commands;

import lombok.AllArgsConstructor;

/**
 * Открывает ячейки вокруг указанной по строке-столбцу,
 * если она открыта и вокруг нее стоит соответствующее количество флагов.
 * Можно взорваться!
 */
@AllArgsConstructor
public class OpenNeighborsCommand extends Command {
    private final int row;
    private final int column;

    @Override
    public void execute() {
        game.openNeighbors(row, column);
    }
}
