package com.github.svyaz.minesweeper.gamemodel.modes;

public class FanMode extends GameMode {
    public FanMode() {
        name = "fan";
        description = "Любитель";
        columns = 16;
        rows = 16;
        bombsCount = 40;
    }
}
