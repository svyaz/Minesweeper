package com.github.svyaz.minesweeper.gamemodel.commands;

public class ShowAboutCommand extends Command {
    @Override
    public void execute() {
        game.showAbout();
    }
}
