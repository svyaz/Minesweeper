package com.github.svyaz.minesweeper.gamemodel.commands;

/**
 * Открывает ячейки вокруг указанной по строке-столбцу,
 * если она открыта и вокруг нее стоит соответствующее количество флагов.
 * Можно взорваться!
 */
public class OpenNeighborsCommand extends Command {
    private final int row;
    private final int column;

    public OpenNeighborsCommand(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public void execute() {
        game.openNeighbors(row, column);
    }
}
