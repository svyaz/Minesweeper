package com.github.svyaz.minesweeper.gamemodel;

/**
 * Запись в таблице рекордов
 */
class ScoreElement {
    private String modeString;
    private String userName;
    private long gameTime;

    /**
     * Создает элемент для сохранения после установки нового рекорда.
     *
     * @param modeString режим игры.
     * @param gameTime   время игры.
     * @param userName   имя игрока.
     */
    ScoreElement(String modeString, long gameTime, String userName) {
        this.modeString = modeString;
        this.gameTime = gameTime;
        this.userName = userName;
    }

    String getModeString() {
        return modeString;
    }

    String getUserName() {
        return userName;
    }

    long getGameTime() {
        return gameTime;
    }
}
