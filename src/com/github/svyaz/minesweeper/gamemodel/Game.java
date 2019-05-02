package com.github.svyaz.minesweeper.gamemodel;

import com.github.svyaz.minesweeper.gamemodel.gamemodes.GameMode;
import com.github.svyaz.minesweeper.view.GameView;
import com.github.svyaz.minesweeper.view.text.TextView;

import java.util.*;

import static com.github.svyaz.minesweeper.gamemodel.GameStatus.*;

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
     * Режим игры: Новичок, Любитель, Профессионал, Свободная игра.
     */
    private GameMode gameMode;

    /**
     * Возможные режимы: Новичок, Любитель, Профессионал, Свободная игра.
     */
    private GameMode[] gameModes;

    /**
     * Игровое поле.
     */
    private Field field;

    /**
     * Создание контроллера в выбранном режиме.
     */
    public Game(GameMode... gameModes) {
        this.view = new TextView();
        this.status = NOT_STARTED;
        this.gameModes = gameModes;
        gameMode = gameModes[0];

    }

    public Field getField() {
        return field;
    }

    /**
     * Запуск таймера при старте игры (когда сделан первый ход)
     */
    private void startTimer() {
        time = 0;
        timer = new Timer(true);

        TimerTask timerTask = new TimerTask() {
            private final long startTime = System.currentTimeMillis();

            @Override
            public void run() {
                //System.out.println((System.currentTimeMillis() - startTime) / 1000);
                time = System.currentTimeMillis() - startTime;
                view.updateGameTime(time);
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    /**
     * Rock it!
     * TODO выбрать подходящее имя для метода!!!
     */
    public void init() {

        //TODO одинаковый код - в отдельный метод
        this.field = new Field(gameMode);
        time = 0;
        view.initView(gameMode.getDescription(),
                gameMode.getRows(),
                gameMode.getColumns(),
                gameMode.getBombsCount());
        view.updateGameTime(time);
        view.printField();

        while (true) {
            Command command = view.getCommand();
            switch (command.getCommand()) {

                case OPEN_CELL:
                    //TODO open one cell
                    view.showMessage("open one cell: " + command.getRow() + ", " + command.getColumn());

                    // Если игра уже закончена
                    if (status == LOST || status == FINISHED) {
                        view.showMessage("Игра уже закончена!");
                        break;
                    }

                    // Стартуем игру если это первый ход
                    if (status == NOT_STARTED) {
                        startTimer();
                        status = STARTED;
                    }

                    //TODO Провалидировать row и column
                    Cell cell = field.getCell(command.getRow(), command.getColumn());

                    // Если флаг или уже открыта, то просто перерисовываем поле.
                    if (cell.hasFlag() || cell.isOpen()) {
                        view.printField();
                        break;
                    }

                    // Если бомба - взорвались и конец игры. Отмечаем все бомбы на карте
                    if (cell.hasBomb()) {
                        status = LOST;
                        List<Cell> cellsToUpdate = new LinkedList<>();
                        for (int i = 0; i < field.getRows(); i++) {
                            for (int j = 0; j < field.getColumns(); j++) {
                                Cell tmpCell = field.getCell(i, j);

                                if (tmpCell.hasBomb()) {
                                    tmpCell.setCellLook(tmpCell == cell ? CellLook.BOMB_BANG : CellLook.BOMB_CLEAR);
                                    cellsToUpdate.add(tmpCell);
                                }
                            }
                        }
                        view.updateField(cellsToUpdate);
                        view.updateGameTime(time);
                        view.printField();
                        view.showMessage("!!!!! Bang !!!!! Вы взорвались !!!!!");
                        break;
                    }

                    // Если дошли сюда - то ячейку значит нужно открыть
                    //TODO и если вокруг нее 0 бомб - то открыть участок до бомб

                    int row = cell.getRow();
                    int column = cell.getColumn();
                    int bombsAround = 0;

                    for (int i = row - 1; i <= row + 1; i++) {
                        for (int j = column - 1; j <= column + 1; j++) {
                            if (i < 0 || i >= field.getRows() || j < 0 || j >= field.getColumns() || (i == row && j == column)) {
                                // Проверка выхода за границы поля
                                continue;
                            }
                            if (field.getCell(i, j).hasBomb()) {
                                ++bombsAround;
                            }
                        }
                    }

                    cell.open();
                    //TODO проверку после каждого открытия сколько осталось не открытых ячеек и не пора ли заканчивать игру???
                    //TODO проверить на настоящей игре что происходит если не посльзоваться флагами, а просто все открывать
                    cell.setCellLook(CellLook.values()[bombsAround]);
                    LinkedList<Cell> cellsToUpdate = new LinkedList<>();
                    cellsToUpdate.add(cell);
                    view.updateField(cellsToUpdate);
                    view.updateGameTime(time);
                    view.printField();
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
                    //TODO это все или нет? Вроде норм работает...
                    view.showMessage("restart game in current mode: " + gameMode.getName());

                    if (status != NOT_STARTED) {
                        status = NOT_STARTED;
                        timer.cancel();
                    }

                    field = new Field(gameMode);
                    time = 0;
                    view.initView(gameMode.getDescription(),
                            gameMode.getRows(),
                            gameMode.getColumns(),
                            gameMode.getBombsCount());
                    view.updateGameTime(time);
                    view.printField();

                    break;
                case START_NEW_GAME:
                    view.showMessage("Новая игра: " + command.getParameter());
                    try {
                        //noinspection OptionalGetWithoutIsPresent
                        gameMode = Arrays.stream(gameModes)
                                .filter(m -> m.getName().equals(command.getParameter()))
                                .findFirst()
                                .get();
                        System.out.println(gameMode.getDescription());

                        if (status != NOT_STARTED) {
                            status = NOT_STARTED;
                            timer.cancel();
                        }

                        field = new Field(gameMode);
                        time = 0;
                        view.initView(gameMode.getDescription(),
                                gameMode.getRows(),
                                gameMode.getColumns(),
                                gameMode.getBombsCount());
                        view.updateGameTime(time);
                        view.printField();

                    } catch (NoSuchElementException e) {
                        view.showMessage("Неизвестный режим!");
                    }

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

//TODO все сообщения вынести в отдельный файл.
