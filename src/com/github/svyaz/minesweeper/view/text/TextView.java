package com.github.svyaz.minesweeper.view.text;

import com.github.svyaz.minesweeper.gamemodel.Cell;
import com.github.svyaz.minesweeper.gamemodel.commands.*;
import com.github.svyaz.minesweeper.view.GameView;

import java.util.List;
import java.util.Scanner;

public class TextView implements GameView {
    private int rows;
    private int columns;
    private int bombsCount;
    private char[][] cells;
    private String modeDescription;
    private String timeString;
    private Scanner scanner;

    public TextView() {
        scanner = new Scanner(System.in);
        scanner.useDelimiter(System.lineSeparator());
    }

    @Override
    public void initView(String modeDescription, int rows, int columns, int bombsCount) {
        this.modeDescription = modeDescription;
        //this.timeString = 0;
        this.rows = rows;
        this.columns = columns;
        this.bombsCount = bombsCount;
        this.cells = new char[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.cells[i][j] = '-';
            }
        }
    }

    @Override
    public void updateField(List<Cell> updateCells) {
        for (Cell cell : updateCells) {
            char cellSymbol;
            //TODO сделать красивые символы
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
                    cellSymbol = 'F';
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
    public void updateGameTime(long gameTime) {
        int hours = (int) gameTime / 3_600_000;
        int minutes = ((int) gameTime % 3_600_000) / 60_000;
        int seconds = ((int) gameTime % 60_000) / 1_000;
        timeString = String.format("%d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public void printField() {
        System.out.println("Mode: " + modeDescription);
        System.out.println("Bombs remaining: " + bombsCount);
        System.out.println("Time: " + timeString);

        //TODO вывести координаты по горизонтали и по вертикали
        //Stream.iterate(1, c -> c + 1).limit(columns).forEach(System.out::println);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(" " + cells[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public Command waitCommand() {
        while (scanner.hasNext()) {
            String inputString = scanner.next();
            //TODO отрезать пробелы с начала и конца, убрать двойные пробелы.

            if (inputString.matches("o \\d+ \\d+")) {
                // Команда "Открыть одну ячейку по координатам"
                String[] strings = inputString.split(" ");
                int row = Integer.parseInt(strings[1]);
                int column = Integer.parseInt(strings[2]);
                return new OpenCellCommand(row, column);

            } else if (inputString.matches("b \\d+ \\d+")) {
                // Команда "Открыть окружающие ячейки"
                String[] strings = inputString.split(" ");
                int row = Integer.parseInt(strings[1]);
                int column = Integer.parseInt(strings[2]);
                //return new Command(GameCommand.OPEN_NEIGHBOR, row, column, 0, null);

            } else if (inputString.matches("f \\d+ \\d+")) {
                // Команда "Поставить/снять флаг"
                String[] strings = inputString.split(" ");
                int row = Integer.parseInt(strings[1]);
                int column = Integer.parseInt(strings[2]);
                //return new Command(GameCommand.FLAG_CELL, row, column, 0, null);

            } else if (inputString.matches("h")) {
                //TODO написать текстовку help-а по командам
                showMessage("help message!");

            } else if (inputString.matches("a")) {
                // Показать информацию о программе
                return new ShowAboutCommand();

            } else if (inputString.matches("s")) {
                // Команда "Показать таблицу рекордов"
                return new ShowScoresCommand();

            } else if (inputString.matches("e")) {
                // Выход из программы.
                return new ExitCommand();

            } else if (inputString.matches("n \\d+ \\d+ \\d+")) {
                // Запуск новой игры в Свободном режиме
                String[] strings = inputString.split(" ");
                int rows = Integer.parseInt(strings[1]);
                int columns = Integer.parseInt(strings[2]);
                int bombsCount = Integer.parseInt(strings[3]);
                //return new Command(GameCommand.START_NEW_FREE, rows, columns, bombsCount, null);

            } else if (inputString.matches("n \\w+")) {
                // Запуск новой игры в одном из предустановленных режимов (Новичок, Любитель, Профессионал).
                //return new Command(GameCommand.START_NEW_GAME, 0, 0, 0, inputString.substring(2));

            } else if (inputString.matches("r")) {
                // Рестартовать игру в текущем режиме
                return new RestartCommand();
            } else {
                showMessage("Неизвестная команда!");
            }
        }
        return null;    // Чтобы метод компилировался.
    }
}
