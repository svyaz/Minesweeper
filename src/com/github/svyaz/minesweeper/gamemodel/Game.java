package com.github.svyaz.minesweeper.gamemodel;

import com.github.svyaz.minesweeper.gamemodel.gamemodes.GameMode;
import com.github.svyaz.minesweeper.view.GameView;
import com.github.svyaz.minesweeper.view.text.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * По сути пока получается что это и есть контроллер.
 */
public class Game {
    /**
     * Пользовательский UI
     */
    private GameView view;

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
        this.view = new TextView();
        this.status = GameStatus.NOT_STARTED;
        this.gameMode = gameMode;
        this.field = new Field(gameMode);
        this.timer = new Timer();
    }

    public Field getField() {
        return field;
    }

    /**
     * Запуск таймера при старте игры (когда сделан первый ход)
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

    /**
     * Rock it!
     */
    public void start() {

        view.initView(gameMode.getDescription(),
                gameMode.getRows(),
                gameMode.getColumns(),
                gameMode.getBombsCount());
        view.printField();

        while (true) {
            Command command = view.getCommand();
            switch (command.getCommand()) {
                case OPEN_CELL:
                    //TODO open one cell
                    view.showMessage("open one cell: " + command.getRow() + ", " + command.getColumn());
                    break;
                case OPEN_NEIGHBOR:
                    //TODO open neighbor cells
                    view.showMessage("open neighbor cells: " + command.getRow() + ", " + command.getColumn());
                    break;
                case FLAG_CELL:
                    //TODO flag/unflag cell
                    view.showMessage("flag/unflag cell:" + command.getRow() + ", " + command.getColumn());
                    break;
                case EXIT:
                    view.showMessage("Пока пока!");
                    System.exit(0);
                    break;
                case SHOW_ABOUT:
                    //TODO написать текстовку about
                    view.showMessage("About text!");
                    break;
                case RESTART_GAME:
                    //TODO restart game in current mode
                    view.showMessage("restart game in current mode: " + gameMode.getName());
                    break;
                case START_NEW_GAME:
                    //TODO new game
                    view.showMessage("new game: " + command.getParameter());
                    break;
                case START_NEW_FREE:
                    //TODO new game
                    view.showMessage("new free game: " + command.getRow() + ", " + command.getColumn() + ", " + command.getBombsCount());
                    break;
                case SHOW_SCORES:
                    //TODO show scores
                    view.showMessage("Show scores.");
                    break;

            }
        }
    }

}
