package com.github.svyaz.minesweeper.gamemodel;

/**
 * Ячейка игрового поля.
 */
public class Cell {
    /**
     * Координата ячейки по строке
     */
    private int row;

    /**
     * Координата ячейки по столбцу
     */
    private int column;

    /**
     * Открыта или закрыта.
     */
    private boolean open;

    /**
     * Есть ли бомба на ячейке.
     */
    private boolean bomb;

    /**
     * Внешность ячейки.
     */
    private CellLook cellLook;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        //this.bomb = bomb;
        this.open = false;
        this.cellLook = CellLook.CLOSED_CLEAR;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen() {
        open = true;
    }

    public boolean hasBomb() {
        return bomb;
    }

    void setBomb() {
        bomb = true;
    }

    public CellLook getCellLook() {
        return cellLook;
    }

    public void setCellLook(CellLook cellLook) {
        this.cellLook = cellLook;
    }
}
