package com.github.svyaz.minesweeper.gamemodel.commands;

public class ExitCommand extends Command {
    @Override
    public void execute() {
        game.exit();
    }
}
