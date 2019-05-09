package com.github.svyaz.minesweeper.gamemodel.modes;

public class RookieMode extends GameMode {
    public RookieMode() {
        name = "rookie";
        description = "MODES_ROOKIE";
        columns = 9;
        rows = 9;
        bombsCount = 10;
    }
}
