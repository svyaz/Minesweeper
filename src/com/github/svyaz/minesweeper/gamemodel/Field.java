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
     * Создает поле из переданного режима игры.
     *
     * @param mode режим игры.
     */
    Field(GameMode mode) {
        this.columns = mode.getColumns();
        this.rows = mode.getRows();
        this.bombsCount = mode.getBombsCount();
        this.flagsCount = 0;
        this.openCellsCount = 0;
        this.cells = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * Заполнение поля бомбами в случайном порядке. За исключением ячейки куда сделан первых клик.
     *
     * @param excludeRow    строка ячейки куда сделан первый клик.
     * @param excludeColumn столбец ячейки куда сделан первый клик.
     */
    void placeBombsOnField(int excludeRow, int excludeColumn) {
        Random random = new Random();
        int i = 0;

        while (i < bombsCount) {
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);

            if (cells[row][column].isBomb() || (row == excludeRow && column == excludeColumn)) {
                continue;
            }
            cells[row][column].setBomb(true);
            i++;
        }

        // Для тестирования показа цифры 8 (Новичок). Тыкать в клетку {2, 2}.
        /*cells[0][0].setBomb();
        cells[0][1].setBomb();
        cells[0][2].setBomb();
        cells[1][0].setBomb();
        cells[1][2].setBomb();
        cells[2][0].setBomb();
        cells[2][1].setBomb();
        cells[2][2].setBomb();
        cells[2][3].setBomb();
        cells[2][4].setBomb();*/

        // Для тестирования показа цифры 7 (Новичок). Тыкать в клетку {2, 2}.
        /*cells[0][0].setBomb();
        cells[0][1].setBomb();
        cells[0][2].setBomb();
        cells[1][0].setBomb();
        cells[2][0].setBomb();
        cells[2][1].setBomb();
        cells[2][2].setBomb();
        cells[2][3].setBomb();
        cells[2][4].setBomb();
        cells[2][5].setBomb();*/

        // Для тестирования показа цифры 6 (Новичок). Тыкать в клетку {2, 2}.
        /*cells[0][0].setBomb();
        cells[0][1].setBomb();
        cells[1][0].setBomb();
        cells[2][0].setBomb();
        cells[2][1].setBomb();
        cells[2][2].setBomb();
        cells[2][3].setBomb();
        cells[2][4].setBomb();
        cells[2][5].setBomb();
        cells[2][6].setBomb();*/
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

    void addOpenCellsCount(int openCellsCount) {
        this.openCellsCount += openCellsCount;
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
