package com.github.svyaz.minesweeper.gamemodel;

import com.github.svyaz.minesweeper.gamemodel.gamemodes.GameMode;

import java.time.Duration;

public class Game {
    /**
     * Статус игры.
     * При создании новой игры сначала у нее статус NOT_STARTED.
     * Таймер начинает отсчитывать только после первого нажатия на игровое поле.
     */
    GameStatus status = GameStatus.NOT_STARTED;

    /**
     * Время игры.
     */
    Duration duration;

    /**
     * Режим игры: Новичок, Любитель, Профессионал, Настраиваемая игра.
     */
    GameMode mode;

    /**
     * Игровое поле.
     */
    Field field;
}
