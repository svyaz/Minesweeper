package com.github.svyaz.minesweeper.gamemodel.modes;

public class FreeMode extends GameMode {
    public FreeMode(int rows, int columns, int bombsCount) {
        this.name = "free";
        this.description = "Свободная игра";
        this.rows = rows;
        this.columns = columns;
        this.bombsCount = bombsCount;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setBombsCount(int bombsCount) {
        this.bombsCount = bombsCount;
    }
}
