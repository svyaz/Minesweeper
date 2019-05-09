package com.github.svyaz.minesweeper.gamemodel.commands;

/**
 * Показать информацию о программе.
 */
public class ShowAboutCommand extends Command {
    @Override
    public void execute() {
        game.showAbout();
    }
}
