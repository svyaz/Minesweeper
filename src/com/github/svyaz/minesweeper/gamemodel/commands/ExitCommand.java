package com.github.svyaz.minesweeper.gamemodel.commands;

/**
 * Команда на выход из программы.
 */
public class ExitCommand extends Command {
    @Override
    public void execute() {
        game.exit();
    }
}
