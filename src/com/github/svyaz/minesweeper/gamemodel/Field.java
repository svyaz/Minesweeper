package com.github.svyaz.minesweeper.gamemodel;

import com.github.svyaz.minesweeper.gamemodel.gamemodes.GameMode;

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
     * Создает поле из переданного режима игры
     */
    public Field(GameMode mode) {
        this.columns = mode.getColumns();
        this.rows = mode.getRows();
        this.bombsCount = mode.getBombsCount();
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
            int randomRow = random.nextInt(rows);
            int randomColumn = random.nextInt(columns);

            if (cells[randomRow][randomColumn].hasBomb()) {
                continue;
            }
            cells[randomRow][randomColumn].setBomb();
            i++;
        }
    }


}
