package com.github.svyaz.minesweeper.gamemodel.commands;

public class ShowScoresCommand extends Command {
    @Override
    public void execute() {
        game.showScores();
    }
}
