package com.github.svyaz.minesweeper.gamemodel;

/**
 * Ячейка игрового поля.
 */
public class Cell {
    /**
     * Открыта или закрыта.
     */
    private boolean isOpen = false;

    /**
     * Есть ли бомба на ячейке.
     */
    private boolean hasBomb = false;

    /**
     * Внешность ячейки.
     */
    private CellLook cellLook = CellLook.CLOSED_CLEAR;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean getHasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public CellLook getCellLook() {
        return cellLook;
    }

    public void setCellLook(CellLook cellLook) {
        this.cellLook = cellLook;
    }
}
