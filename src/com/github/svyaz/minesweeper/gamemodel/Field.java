package com.github.svyaz.minesweeper.gamemodel;

import com.github.svyaz.minesweeper.gamemodel.modes.GameMode;

import java.util.Random;

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
     * Количество мин.
     */
    private int bombsCount;

    /**
     * Количество открытых ячеек
     */
    private int openCellsCount;

    /**
     * Создает поле из переданного режима игры
     */
    public Field(GameMode mode) {
        this.columns = mode.getColumns();
        this.rows = mode.getRows();
        this.bombsCount = mode.getBombsCount();
        this.openCellsCount = 0;
        this.cells = new Cell[rows][columns];
        initField();
    }

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
            //TODO DEBUG
            System.out.println("bomb(" + row + "," + column + ")");
        }
    }

    public Cell getCell(int row, int column) {
        //TODO исключение при выходе за границы с сообщением
        return cells[row][column];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getBombsCount() {
        return bombsCount;
    }

    public int getOpenCellsCount() {
        return openCellsCount;
    }

    public void incrementOpenCellsCount() {
        openCellsCount++;
    }

    public boolean isAllOpen() {
        System.out.printf("All: %d, open: %d, bombs: %d%n", rows * columns, openCellsCount, bombsCount);
        return rows * columns - openCellsCount == bombsCount;
    }
}
