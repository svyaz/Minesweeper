package com.github.svyaz.minesweeper.gamemodel;

import com.github.svyaz.minesweeper.gamemodel.commands.Command;
import com.github.svyaz.minesweeper.gamemodel.modes.FreeMode;
import com.github.svyaz.minesweeper.gamemodel.modes.GameMode;
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
                time = System.currentTimeMillis() - startTime;
                view.updateGameTime(time);
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    /**
     * Let's rock !!!
     */
    public void runGame() {

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
            Command command = view.waitCommand();
            command.setGame(this);
            command.execute();
        }
    }

    public void startNewGame(String mode) {
        try {
            //noinspection OptionalGetWithoutIsPresent
            gameMode = Arrays.stream(gameModes)
                    .filter(m -> m.getName().equals(mode))
                    .findFirst()
                    .get();

            this.restart();
        } catch (NoSuchElementException e) {
            view.showMessage("Неизвестный режим!");
        }
    }

    public void startNewGame(int rows, int columns, int bombsCount) {
        try {
            //noinspection OptionalGetWithoutIsPresent
            gameMode = Arrays.stream(gameModes)
                    .filter(m -> m.getName().equals("free"))
                    .findFirst()
                    .get();

            //TODO ???????

            this.restart();
        } catch (NoSuchElementException e) {
            view.showMessage("Неизвестный режим!");
        }
    }

    public void restart() {
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
    }

    public void openCell(int row, int column) {
        //TODO есть дублирующиеся куски кода с установкой флага и открытием соседей

        // Если игра уже закончена
        if (status == LOST || status == FINISHED) {
            view.showMessage("Игра уже закончена!");
            return;
        }

        // Стартуем игру если это первый ход
        if (status == NOT_STARTED) {
            startTimer();
            status = STARTED;
        }

        // Если за пределами поля
        if (row >= field.getRows() || column >= field.getColumns()) {
            view.printField();
            return;
        }

        Cell cell = field.getCell(row, column);

        // Если флаг или уже открыта, то просто перерисовываем поле.
        if (cell.hasFlag() || cell.isOpen()) {
            view.printField();
            return;
        }

        // Для обновления внешнего вида ячеек
        List<Cell> cellsToUpdate = new LinkedList<>();

        // Если бомба - взорвались и конец игры. Отмечаем все бомбы на поле
        if (cell.hasBomb()) {
            status = LOST;
            timer.cancel();
            for (int i = 0; i < field.getRows(); i++) {
                for (int j = 0; j < field.getColumns(); j++) {
                    Cell tmpCell = field.getCell(i, j);

                    if (tmpCell.hasBomb()) {
                        if (tmpCell.hasFlag()) {
                            continue;
                        }
                        tmpCell.setCellLook(tmpCell == cell ? CellLook.BOMB_BANG : CellLook.BOMB_CLEAR);
                    } else {
                        if (tmpCell.hasFlag()) {
                            tmpCell.setCellLook(CellLook.BOMB_WRONG);
                        } else {
                            continue;
                        }
                    }
                    cellsToUpdate.add(tmpCell);
                }
            }
            view.updateField(cellsToUpdate);
            view.updateGameTime(time);
            view.printField();
            view.showMessage("!!!!! Bang !!!!! Вы взорвались !!!!!");
            return;
        }

        // Если дошли сюда - то ячейку значит нужно открыть
        Queue<Cell> queue = new LinkedList<>();
        queue.add(cell);

        while (!queue.isEmpty()) {
            Cell current = queue.remove();

            if (current.isOpen()) {
                // Если ячейка уже открыта
                continue;
            }

            int currentRow = current.getRow();
            int currentColumn = current.getColumn();
            int bombsAround = 0;
            Queue<Cell> subQueue = new LinkedList<>();

            for (int i = currentRow - 1; i <= currentRow + 1; i++) {
                for (int j = currentColumn - 1; j <= currentColumn + 1; j++) {
                    if (i < 0 || i >= field.getRows() || j < 0 || j >= field.getColumns() || (i == currentRow && j == currentColumn)) {
                        // Проверка выхода за границы поля и если та же самая ячейка
                        continue;
                    }

                    Cell neighbor = field.getCell(i, j);
                    if (neighbor.hasBomb()) {
                        ++bombsAround;
                    } else {
                        subQueue.add(neighbor);
                    }
                }
            }

            current.open();
            current.setCellLook(CellLook.values()[bombsAround]);
            cellsToUpdate.add(current);
            field.incrementOpenCellsCount();

            if (bombsAround == 0) {
                queue.addAll(subQueue);
            }
        }

        view.updateField(cellsToUpdate);
        view.updateGameTime(time);
        view.printField();

        if (field.isAllOpen()) {
            // Если всё открыто кроме бомб - игра пройдена!
            //TODO поставить флажки на все неоткрытые клетки (с бомбами)
            status = FINISHED;
            timer.cancel();
            view.showMessage("!!!!! Вы выиграли !!!!! УРА !!!!!");
        }
    }

    public void openNeighbors(int row, int column) {
        // Если игра уже закончена
        if (status == LOST || status == FINISHED) {
            view.showMessage("Игра уже закончена!");
            return;
        }

        // Стартуем игру если это первый ход
        if (status == NOT_STARTED) {
            startTimer();
            status = STARTED;
        }

        // Если за пределами поля
        if (row >= field.getRows() || column >= field.getColumns()) {
            view.printField();
            return;
        }

        Cell cell = field.getCell(row, column);

        // Если флаг или уже открыта, то просто перерисовываем поле.
        if (cell.hasFlag() || !cell.isOpen()) {
            view.printField();
            return;
        }

        int currentRow = cell.getRow();
        int currentColumn = cell.getColumn();
        int bombsAround = 0;
        int flagsAround = 0;
        List<Cell> cellsToOpenList = new LinkedList<>();

        for (int i = currentRow - 1; i <= currentRow + 1; i++) {
            for (int j = currentColumn - 1; j <= currentColumn + 1; j++) {
                // Проверка выхода за границы поля и если та же самая ячейка
                if (i < 0 || i >= field.getRows() || j < 0 || j >= field.getColumns() || (i == currentRow && j == currentColumn)) {
                    continue;
                }

                Cell neighbor = field.getCell(i, j);

                // Если уже открытая то пропускаем
                if (neighbor.isOpen()) {
                    continue;
                }

                // Считаем бомбы вокруг
                if (neighbor.hasBomb()) {
                    ++bombsAround;
                }

                // Считаем флаги вокруг
                if (neighbor.hasFlag()) {
                    ++flagsAround;
                } else {
                    if (neighbor.hasBomb()) {
                        // Добавление в начало чтобы на бомбах сразу взрываться
                        cellsToOpenList.add(0, neighbor);
                    } else {
                        cellsToOpenList.add(neighbor);
                    }
                }
            }
        }

        if (bombsAround == flagsAround) {
            //TODO Отдельный private метод на открытие. А то еще и перерисовывает после каждой открытой клетки!
            for (Cell cellToOpen : cellsToOpenList) {
                openCell(cellToOpen.getRow(), cellToOpen.getColumn());
            }
        }
    }

    public void flagCell(int row, int column) {
        // Если игра уже закончена
        if (status == LOST || status == FINISHED) {
            view.showMessage("Игра уже закончена!");
            return;
        }

        // Стартуем игру если это первый ход
        if (status == NOT_STARTED) {
            startTimer();
            status = STARTED;
        }

        // Если за пределами поля
        if (row >= field.getRows() || row >= field.getColumns()) {
            view.printField();
            return;
        }

        Cell fCell = field.getCell(row, column);

        // Если уже открыта - просто перерисовываем поле
        if (fCell.isOpen()) {
            view.printField();
            return;
        }

        // Ставим/убираем флаг
        fCell.setFlag(!fCell.hasFlag());
        fCell.setCellLook(fCell.hasFlag() ? CellLook.CLOSED_FLAGGED : CellLook.CLOSED_CLEAR);
        List<Cell> updateCells = new LinkedList<>();
        updateCells.add(fCell);
        view.updateField(updateCells);
        view.updateGameTime(time);
        view.printField();
    }

    public void exit() {
        System.exit(0);
    }

    public void showAbout() {
        //TODO написать текстовку about
        view.showMessage("About text!");
    }

    public void showScores() {
        //TODO show scores
        view.showMessage("Show scores.");
    }

}

//TODO все сообщения вынести в отдельный файл.
