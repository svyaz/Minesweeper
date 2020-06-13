package com.github.svyaz.minesweeper.gamemodel.commands;

import lombok.AllArgsConstructor;

/**
 * Запустить новую игру в одном из предустановленных режимов.
 */
@AllArgsConstructor
public class StartPresetGameCommand extends Command {
    private String mode;

    @Override
    public void execute() {
        game.startNewGame(mode);
    }
}
