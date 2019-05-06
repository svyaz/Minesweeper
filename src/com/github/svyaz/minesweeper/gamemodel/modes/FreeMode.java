package com.github.svyaz.minesweeper.gamemodel.modes;

public class FreeMode extends GameMode {
    public FreeMode(int width, int height, int bombsCount) {
        this.name = "free";
        this.description = "Свободная игра";
        this.columns = width;
        this.rows = height;
        this.bombsCount = bombsCount;
    }
}
