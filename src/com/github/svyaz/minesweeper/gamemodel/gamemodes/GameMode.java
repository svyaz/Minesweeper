package com.github.svyaz.minesweeper.gamemodel.gamemodes;

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
     * Ширина поля, в количестве ячеек.
     */
    int width;

    /**
     * Ширина поля, в количестве ячеек.
     */
    int height;

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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBombsCount() {
        return bombsCount;
    }
}
