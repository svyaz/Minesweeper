package com.github.svyaz.minesweeper.view;

import com.github.svyaz.minesweeper.gamemodel.Cell;
import com.github.svyaz.minesweeper.gamemodel.Command;

import java.util.List;

public interface GameView {
    // Инициализация view
    void initView(String modeDescription, int rows, int columns, int bombsCount);

    // Обновить поле
    void updateField(List<Cell> cellsList);

    // Обновить время игры
    void updateGameTime(long gameTime);

    //TODO - реализовать - Обновить число оставшихся бомб
    //void updateBombsCounter(int bombsCounter);

    // Нарисовать поле
    void printField();

    // Ожидать команду
    Command waitCommand();

    // Показать сообщение об ошибке
    void showMessage(String message);
}
