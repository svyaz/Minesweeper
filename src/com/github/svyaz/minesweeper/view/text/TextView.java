package com.github.svyaz.minesweeper.view.text;

import com.github.svyaz.minesweeper.gamemodel.Cell;
import com.github.svyaz.minesweeper.gamemodel.Game;
import com.github.svyaz.minesweeper.gamemodel.commands.*;
import com.github.svyaz.minesweeper.view.GameView;

import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.stream.Stream;

public class TextView implements GameView {
    private static Scanner scanner = new Scanner(System.in);
    private int rows;
    private int columns;
    private int bombsCount;
    private char[][] cells;
    private String modeDescription;
    private String timeString;
    private ResourceBundle messages = ResourceBundle.getBundle("com.github.svyaz.minesweeper.view.text.Messages");

    @Override
    public void initView(String modeDescription, int rows, int columns, int bombsCount) {
        this.modeDescription = modeDescription;
        this.rows = rows;
        this.columns = columns;
        this.bombsCount = bombsCount;
        this.cells = new char[rows][columns];
        timeString = Game.getGameTimeString(0);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.cells[i][j] = '-';
            }
        }

        showMessage("HELP_TEXT");
    }

    @Override
    public void updateField(List<Cell> updateCells) {
        for (Cell cell : updateCells) {
            char cellSymbol;
            switch (cell.getCellLook()) {
                case OPEN_0:
                    cellSymbol = '0';
                    break;
                case OPEN_1:
                    cellSymbol = '1';
                    break;
                case OPEN_2:
                    cellSymbol = '2';
                    break;
                case OPEN_3:
                    cellSymbol = '3';
                    break;
                case OPEN_4:
                    cellSymbol = '4';
                    break;
                case OPEN_5:
                    cellSymbol = '5';
                    break;
                case OPEN_6:
                    cellSymbol = '6';
                    break;
                case OPEN_7:
                    cellSymbol = '7';
                    break;
                case OPEN_8:
                    cellSymbol = '8';
                    break;
                case CLOSED_FLAGGED:
                    cellSymbol = 'f';
                    break;
                case BOMB_CLEAR:
                    cellSymbol = '*';
                    break;
                case BOMB_BANG:
                    cellSymbol = 'X';
                    break;
                case BOMB_WRONG:
                    cellSymbol = 'W';
                    break;
                default:
                    cellSymbol = '-';
            }
            cells[cell.getRow()][cell.getColumn()] = cellSymbol;
        }
    }

    @Override
    public void updateGameTimeString(String timeString) {
        this.timeString = timeString;
    }

    @Override
    public void updateBombsCount(int bombsCount) {
        this.bombsCount = bombsCount;
    }

    @Override
    public void printField() {
        String ls = System.lineSeparator();
        StringBuilder sb = new StringBuilder();

        sb.append("================================================================").append(ls);
        sb.append(messages.getString("VIEW_MODE"));
        sb.append(String.format(" %.15s | ", messages.getString(modeDescription)));
        sb.append(messages.getString("VIEW_BOMBS_REMAIN"));
        sb.append(String.format(" %.3s | ", bombsCount));
        sb.append(messages.getString("VIEW_GAME_TIME"));
        sb.append(String.format(" %.9s ", timeString)).append(ls);
        sb.append("================================================================").append(ls);

        // Координаты
        sb.append("   |");
        for (int i = 0; i < columns; i++) {
            if (i < 10) {
                sb.append(String.format("%2s", i));
            } else if (i % 2 != 0) {
                sb.append(String.format("  %2s", i));
            }
        }
        sb.append(ls);
        Stream.generate(() -> "-")
                .limit(columns * 2 + 4)
                .forEach(sb::append);
        sb.append(ls);

        for (int i = 0; i < rows; i++) {
            sb.append(String.format("%2s |", i));

            for (int j = 0; j < columns; j++) {
                sb.append(' ').append(cells[i][j]);
            }
            sb.append(ls);
        }

        System.out.print(sb.toString());
    }

    @Override
    public void showMessage(String message) {
        System.out.println(messages.getString(message));
    }

    @Override
    public void showScores(HashMap<String, String> scoresMap) {
        scoresMap.forEach((key, value) -> System.out.println(messages.getString(key) + value));
    }

    @Override
    public void startView(Game gameController) {
        while (scanner.hasNextLine()) {
            String inputString = scanner.nextLine().trim().replaceAll(" +", " ");

            if (inputString.matches("o \\d+ \\d+")) {
                // Команда "Открыть одну ячейку по координатам"
                String[] strings = inputString.split(" ");
                int row = Integer.parseInt(strings[1]);
                int column = Integer.parseInt(strings[2]);
                gameController.executeCommand(new OpenCellCommand(row, column));

            } else if (inputString.matches("b \\d+ \\d+")) {
                // Команда "Открыть окружающие ячейки"
                String[] strings = inputString.split(" ");
                int row = Integer.parseInt(strings[1]);
                int column = Integer.parseInt(strings[2]);
                gameController.executeCommand(new OpenNeighborsCommand(row, column));

            } else if (inputString.matches("f \\d+ \\d+")) {
                // Команда "Поставить/снять флаг"
                String[] strings = inputString.split(" ");
                int row = Integer.parseInt(strings[1]);
                int column = Integer.parseInt(strings[2]);
                gameController.executeCommand(new FlagCellCommand(row, column));

            } else if (inputString.matches("h")) {
                // Показать help по командам
                showMessage("HELP_TEXT");

            } else if (inputString.matches("a")) {
                // Показать информацию о программе
                gameController.executeCommand(new ShowAboutCommand());

            } else if (inputString.matches("s")) {
                // Команда "Показать таблицу рекордов"
                gameController.executeCommand(new ShowScoresCommand());

            } else if (inputString.matches("[qe]")) {
                // Выход из программы.
                gameController.executeCommand(new ExitCommand());

            } else if (inputString.matches("n \\d+ \\d+ \\d+")) {
                // Запуск новой игры в Свободном режиме
                String[] strings = inputString.split(" ");
                int rows = Integer.parseInt(strings[1]);
                int columns = Integer.parseInt(strings[2]);
                int bombsCount = Integer.parseInt(strings[3]);
                gameController.executeCommand(new StartFreeGameCommand(rows, columns, bombsCount));

            } else if (inputString.matches("n \\w+")) {
                // Запуск новой игры в одном из предустановленных режимов (Новичок, Любитель, Профессионал).
                gameController.executeCommand(new StartPresetGameCommand(inputString.substring(2)));

            } else if (inputString.matches("r")) {
                // Рестартовать игру в текущем режиме
                gameController.executeCommand(new RestartCommand());
            } else {
                //  Когда ввели что-то неожидаемое.
                showMessage("MSG_UNKNOWN_COMMAND");
            }
        }
    }

    @Override
    public String getUserName() {
        System.out.println(messages.getString("USER_NAME_ENTER_INVITATION"));

        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        } else {
            return messages.getString("USER_NAME_DEFAULT");
        }
    }
}
