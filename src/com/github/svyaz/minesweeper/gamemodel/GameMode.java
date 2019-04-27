package com.github.svyaz.minesweeper.gamemodel;

/**
 * Режим игры: Новичок, Любитель, Профессионал, Настраиваемая игра.
 */
public class GameMode {
    /**
     * Название режима.
     */
    private String name;

    /**
     * Ширина поля, в количестве ячеек.
     */
    private int width;

    /**
     * Ширина поля, в количестве ячеек.
     */
    private int height;

    /**
     * Количество бомб.
     */
    private int bombsCount;

    public GameMode(String name, int width, int height, int bombsCount) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.bombsCount = bombsCount;
    }

    public String getName() {
        return name;
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
