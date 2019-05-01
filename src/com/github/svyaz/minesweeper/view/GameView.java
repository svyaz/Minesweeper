package com.github.svyaz.minesweeper.view;

import com.github.svyaz.minesweeper.gamemodel.Cell;
import com.github.svyaz.minesweeper.gamemodel.Command;

public interface GameView {
    // Инициализация view
    void initView(String modeDescription, int rows, int columns, int bombsCount);

    // Обновить поле
    void updateField(Cell[] cells);

    // Нарисовать поле
    void printField();

    // Получить команду
    Command getCommand();

    // Показать сообщение об ошибке
    void showMessage(String message);
}
