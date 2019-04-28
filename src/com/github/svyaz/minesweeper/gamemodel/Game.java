package com.github.svyaz.minesweeper.gamemodel;

import com.github.svyaz.minesweeper.gamemodel.gamemodes.GameMode;

import java.util.Timer;
import java.util.TimerTask;


public class Game {
    /**
     * Статус игры.
     * При создании новой игры сначала у нее статус NOT_STARTED.
     * Таймер начинает отсчитывать только после первого нажатия на игровое поле.
     */
    private GameStatus status;

    /**
     * Таймер игры.
     */
    private Timer timer;

    /**
     * Время игры.
     */
    private long time;

    /**
     * Режим игры: Новичок, Любитель, Профессионал, Настраиваемая игра.
     */
    private GameMode gameMode;

    /**
     * Игровое поле.
     */
    private Field field;

    /**
     * Создание игры в выбранном режиме.
     */
    public Game(GameMode gameMode) {
        this.status = GameStatus.NOT_STARTED;
        this.gameMode = gameMode;
        this.timer = new Timer();
    }

    /**
     * Запуск таймера при старте игры
     */
    private void startTimer() {
        TimerTask timerTask = new TimerTask() {
            private final long startTime = System.currentTimeMillis();

            @Override
            public void run() {
                //System.out.println((System.currentTimeMillis() - startTime) / 1000);
                time = System.currentTimeMillis() - startTime;
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }
}
