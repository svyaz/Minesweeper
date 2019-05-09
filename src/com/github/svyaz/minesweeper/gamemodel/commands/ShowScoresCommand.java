package com.github.svyaz.minesweeper.gamemodel.commands;

/**
 * Показать таблицу рекордов.
 */
public class ShowScoresCommand extends Command {
    @Override
    public void execute() {
        game.showScores();
    }
}
