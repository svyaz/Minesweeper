package com.github.svyaz.minesweeper.gamemodel.gamemodes;

public class ProMode extends GameMode {
    public ProMode() {
        name = "pro";
        description = "Профессионал";
        columns = 30;
        rows = 16;
        bombsCount = 99;
    }
}
