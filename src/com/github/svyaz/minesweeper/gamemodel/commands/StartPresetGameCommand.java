package com.github.svyaz.minesweeper.gamemodel.commands;

/**
 * Запустить новую игру в одном из предустановленных режимов.
 */
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
