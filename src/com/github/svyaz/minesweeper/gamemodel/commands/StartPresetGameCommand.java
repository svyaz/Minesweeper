package com.github.svyaz.minesweeper.gamemodel.commands;

public class StartPresetGameCommand extends Command {
    private String mode;

    public StartPresetGameCommand(String mode) {
        this.mode = mode;
    }

    @Override
    public void execute() {
        game.startNewGame(mode);
    }
}
