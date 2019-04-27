package com.github.svyaz.minesweeper.gamemodel;

public enum GameStatus {
    NOT_STARTED,    // Не начата. Такой статус возникает после сразу запуска.
    STARTED,        // Игра начата и находится в процессе.
    PAUSED,         // Игра преостановлена, на паузе.
    LOST,           // Игра проиграна, взорвался на мине.
    FINISHED,       // Игра успешно пройдена.
}
