package com.github.svyaz.minesweeper.gamemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Запись в таблице рекордов
 */
@Getter
@AllArgsConstructor
class ScoreElement {
    /**
     * режим игры
     */
    private String modeString;

    /**
     * имя игрока
     */
    private long gameTime;

    /**
     * время игры
     */
    private String userName;
}
