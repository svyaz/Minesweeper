package com.github.svyaz.minesweeper.gamemodel;

public enum GameCommand {
    START_NEW_GAME, // Запустить новую игру
    START_NEW_FREE, // Запустить новую игру для Свободного режима
    RESTART_GAME,   // Перезапустить игру в текущем режиме
    OPEN_CELL,      // Открыть ячейку
    OPEN_NEIGHBOR,  // Открыть окружающие ячейки
    FLAG_CELL,      // Поставить/убрать флаг на ячейку
    //SHOW_HELP,      // "Помощь", вывод подсказки по командам
    SHOW_ABOUT,     // Показать "О программе"
    SHOW_SCORES,    // Показать рекорды
    //TODO может не нужно через команды а отдельно???
    //ENTERED_NAME,   // Введено имя для таблицы рекордов
    EXIT            // Выход
}