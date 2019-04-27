package com.github.svyaz.minesweeper.gamemodel;

import com.github.svyaz.minesweeper.gamemodel.gamemodes.GameMode;

import java.util.Random;

public class Field {
    /**
     * Массив ячеек поля.
     */
    private Cell[][] cells;

    /**
     * Количество ячеек по горизонтали.
     */
    private int width;

    /**
     * Количество ячеек по вертикали.
     */
    private int height;

    /**
     * Количество мин.
     */
    private int bombsCount;

    /**
     * Создает поле из переданного режима игры
     */
    public Field(GameMode mode) {
        this.width = mode.getWidth();
        this.height = mode.getHeight();
        this.bombsCount = mode.getBombsCount();
        this.cells = new Cell[height][width];
        initField();
    }

    private void initField() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell();
            }
        }

        Random random = new Random();
        int i = 0;

        while (i < bombsCount) {
            int randomRow = random.nextInt(height);
            int randomColumn = random.nextInt(width);

            if (cells[randomRow][randomColumn].getHasBomb()) {
                continue;
            }
            cells[randomRow][randomColumn].setHasBomb(true);
            i++;
        }
    }

    public void printField() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.printf(" %s", cells[i][j].getCellLook());
            }
            System.out.println();
        }
    }
}
