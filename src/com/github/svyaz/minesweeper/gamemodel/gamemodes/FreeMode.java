package com.github.svyaz.minesweeper.gamemodel.gamemodes;

public class FreeMode extends GameMode {
    public FreeMode(int width, int height, int bombsCount) {
        this.name = "free";
        this.description = "Свободная игра";
        this.width = width;
        this.height = height;
        this.bombsCount = bombsCount;
    }
}
