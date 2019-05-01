package com.github.svyaz.minesweeper.gamemodel;

public enum GameStatus {
    NOT_STARTED,    // Не начата. Такой статус возникает после сразу запуска.
    STARTED,        // Игра начата и находится в процессе.
    LOST,           // Игра проиграна, взорвался на бомбе.
    FINISHED,       // Игра успешно пройдена.
}
