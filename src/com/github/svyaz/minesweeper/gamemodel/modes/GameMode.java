package com.github.svyaz.minesweeper.gamemodel.modes;

/**
 * Режим игры.
 */
public abstract class GameMode {
    /**
     * Название режима (параметр запуска программы)
     */
    String name;

    /**
     * Описание режима (Новичок, Любитель, Профессионал, Свободная игра.)
     */
    String description;

    /**
     * Ширина поля, столбцы.
     */
    int columns;

    /**
     * Высота поля, строки.
     */
    int rows;

    /**
     * Количество бомб.
     */
    int bombsCount;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public int getBombsCount() {
        return bombsCount;
    }
}
