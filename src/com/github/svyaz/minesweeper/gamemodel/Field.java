package com.github.svyaz.minesweeper.gamemodel;

import com.github.svyaz.minesweeper.gamemodel.modes.GameMode;

import java.util.Random;

/**
 * Игровое поле.
 */
public class Field {
    /**
     * Массив ячеек поля.
     */
    private Cell[][] cells;

    /**
     * Количество ячеек по вертикали.
     */
    private int rows;

    /**
     * Количество ячеек по горизонтали.
     */
    private int columns;

    /**
     * Количество бомб.
     */
    private int bombsCount;

    /**
     * Количество установленных флагов.
     */
    private int flagsCount;

    /**
     * Количество открытых ячеек
     */
    private int openCellsCount;

    /**
     * Создает поле из переданного режима игры
     */
    Field(GameMode mode) {
        this.columns = mode.getColumns();
        this.rows = mode.getRows();
        this.bombsCount = mode.getBombsCount();
        this.flagsCount = 0;
        this.openCellsCount = 0;
        this.cells = new Cell[rows][columns];
        initField();
    }

    /**
     * Заполнение поля бомбами в случайном порядке.
     */
    private void initField() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }

        Random random = new Random();
        int i = 0;

        while (i < bombsCount) {
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);

            if (cells[row][column].hasBomb()) {
                continue;
            }
            cells[row][column].setBomb();
            i++;
        }
    }

    Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    int getBombsCount() {
        return bombsCount;
    }

    void incrementOpenCellsCount() {
        openCellsCount++;
    }

    int getFlagsCount() {
        return flagsCount;
    }

    void incrementFlagsCount() {
        flagsCount++;
    }

    void decrementFlagsCount() {
        flagsCount--;
    }

    boolean isAllOpen() {
        return rows * columns - openCellsCount == bombsCount;
    }
}
