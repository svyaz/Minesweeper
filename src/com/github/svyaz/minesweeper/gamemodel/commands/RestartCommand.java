package com.github.svyaz.minesweeper.gamemodel.commands;

/**
 * Перезапустить игру в текущем режиме.
 */
public class RestartCommand extends Command {
    @Override
    public void execute() {
        game.restart();
    }
}
