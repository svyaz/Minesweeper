package com.github.svyaz.minesweeper.gamemodel.modes;

public class ProMode extends GameMode {
    public ProMode() {
        name = "pro";
        description = "MODE_PRO";
        columns = 30;
        rows = 16;
        bombsCount = 99;
    }
}
