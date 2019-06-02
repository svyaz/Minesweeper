package com.github.svyaz.minesweeper.gamemodel.modes;

public class FreeMode extends GameMode {
    public static final int MIN_ROWS = 9;
    public static final int MAX_ROWS = 24;
    public static final int MIN_COLUMNS = 9;
    public static final int MAX_COLUMNS = 30;
    public static final double MIN_BOMBS_FACTOR = 0.1;
    public static final double MAX_BOMBS_FACTOR = 0.3;

    public FreeMode(int rows, int columns, int bombsCount) {
        this.name = "free";
        this.description = "MODE_FREE";

        /*
         * Размер поля в стандартном сапёре в режиме "Свободная игра" ограничен:
         * 9 <= rows <= 24
         * 9 <= columns <= 30
         */
        this.rows = rows < MIN_ROWS ? MIN_ROWS : (rows > MAX_ROWS ? MAX_ROWS : rows);
        this.columns = columns < MIN_COLUMNS ? MIN_COLUMNS : (columns > MAX_COLUMNS ? MAX_COLUMNS : columns);

        /*
         * Количество бомб в стандартном сапёре можно задавать максимум до 667 штук.
         * Я решил ограничивать задаваемое число - не больше 30% от количества клеток,
         * иначе, мне кажется, игру не пройти. И не меньше 10% - иначе слишком легко :)
         */
        int cells = this.rows * this.columns;
        this.bombsCount = bombsCount < cells * MIN_BOMBS_FACTOR
                ? (int) (cells * MIN_BOMBS_FACTOR)
                : (bombsCount > cells * MAX_BOMBS_FACTOR ? (int) (cells * MAX_BOMBS_FACTOR) : bombsCount);
    }
}
