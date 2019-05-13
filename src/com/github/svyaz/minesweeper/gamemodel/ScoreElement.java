package com.github.svyaz.minesweeper.gamemodel;

/**
 * Запись в таблице рекордов
 */
public class ScoreElement {
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
    public ScoreElement(String modeString, long gameTime, String userName) {
        this.modeString = modeString;
        this.gameTime = gameTime;
        this.userName = userName;
    }

    public String getModeString() {
        return modeString;
    }

    public String getUserName() {
        return userName;
    }

    public long getGameTime() {
        return gameTime;
    }
}
