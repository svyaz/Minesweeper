package com.github.svyaz.minesweeper.gamemodel;

import lombok.Getter;
import lombok.Setter;

/**
 * Ячейка игрового поля.
 */
@Getter
@Setter
public class Cell {
    /**
     * Координата ячейки по строке
     */
    private final int row;

    /**
     * Координата ячейки по столбцу
     */
    private final int column;

    /**
     * Открыта или закрыта.
     */
    private boolean open;

    /**
     * Есть ли бомба на ячейке.
     */
    private boolean bomb;

    /**
     * Поставлен ли флаг на ячейку
     */
    private boolean flag;

    /**
     * Внешность ячейки.
     */
    private CellLook cellLook;

    Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.bomb = false;
        this.open = false;
        this.flag = false;
        this.cellLook = CellLook.CLOSED_CLEAR;
    }

    /*public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    boolean isOpen() {
        return open;
    }

    void open() {
        open = true;
    }

    boolean hasBomb() {
        return bomb;
    }

    void setBomb() {
        bomb = true;
    }

    boolean hasFlag() {
        return flag;
    }

    void setFlag(boolean flag) {
        this.flag = flag;
    }

    public CellLook getCellLook() {
        return cellLook;
    }

    void setCellLook(CellLook cellLook) {
        this.cellLook = cellLook;
    }*/
}
