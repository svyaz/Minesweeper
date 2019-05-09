package com.github.svyaz.minesweeper.gamemodel.modes;

public class ProMode extends GameMode {
    public ProMode() {
        name = "pro";
        description = "MODES_PRO";
        columns = 30;
        rows = 16;
        bombsCount = 99;
    }
}
