package com.github.svyaz.minesweeper.gamemodel.modes;

public class FreeMode extends GameMode {
    public FreeMode(int rows, int columns, int bombsCount) {
        //TODO сделать валидацию и ограничения размеров и количества бомб.
        this.name = "free";
        this.description = "Свободная игра";
        this.rows = rows;
        this.columns = columns;
        this.bombsCount = bombsCount;
    }
}
