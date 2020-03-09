package com.github.svyaz.minesweeper.gamemodel.modes;

import lombok.Getter;

/**
 * Режим игры.
 */
@Getter
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
}
