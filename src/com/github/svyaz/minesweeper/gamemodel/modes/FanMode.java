package com.github.svyaz.minesweeper.gamemodel.modes;

public class FanMode extends GameMode {
    public FanMode() {
        name = "fan";
        description = "MODE_FAN";
        columns = 16;
        rows = 16;
        bombsCount = 40;
    }
}
