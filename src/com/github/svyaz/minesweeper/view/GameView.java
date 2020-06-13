package com.github.svyaz.minesweeper.view;

import com.github.svyaz.minesweeper.gamemodel.Cell;
import com.github.svyaz.minesweeper.gamemodel.Game;

import java.util.List;
import java.util.Map;

public interface GameView {
    // Инициализация view
    void initView(String modeDescription, int rows, int columns, int bombsCount);

    // Обновить поле
    void updateField(List<Cell> cellsList);

    // Обновить время игры
    void updateGameTimeString(String timeString);

    // Обновить число оставшихся бомб
    void updateBombsCount(int bombsCount);

    // Нарисовать поле
    void printField();

    // Запустить view
    void startView(Game gameController);

    // Показать сообщение
    void showMessage(String message);

    // Показать таблицу рекордов
    void showScores(Map<String, String> scoresMap);

    // Получить имя пользователя, который поставил рекорд
    String getUserName();
}
