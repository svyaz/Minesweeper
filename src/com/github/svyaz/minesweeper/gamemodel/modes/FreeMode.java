package com.github.svyaz.minesweeper.gamemodel.modes;

public class FreeMode extends GameMode {
    public FreeMode(int rows, int columns, int bombsCount) {
        this.name = "free";
        this.description = "MODES_FREE";

        /*
         * Размер поля в стандартном сапёре в режиме "Свободная игра" ограничен:
         * 9 <= rows <= 24
         * 9 <= columns <= 30
         */
        this.rows = rows < 9 ? 9 : (rows > 24 ? 24 : rows);
        this.columns = columns < 9 ? 9 : (columns > 30 ? 30 : columns);

        /*
         * Количество бомб в стандартном сапере можно задавать максимум до 667 штук.
         * Я решил ограничивать задаваемое число - не больше 30% от количества клеток,
         * иначе, мне кажется, игру не пройти. И не меньше 10% - иначе слишком легко :)
         */
        int cells = this.rows * this.columns;
        this.bombsCount = bombsCount < cells * 0.1
                ? (int) (cells * 0.1)
                : (bombsCount > cells * 0.3 ? (int) (cells * 0.3) : bombsCount);
    }
}
