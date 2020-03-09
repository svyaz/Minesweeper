package com.github.svyaz.minesweeper.gamemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameStatus {
    NOT_STARTED("face-smile.png"),  // Не начата. Такой статус возникает после сразу запуска.
    STARTED("face-smile.png"),      // Игра начата и находится в процессе.
    LOST("face-gameover.png"),      // Игра проиграна, взорвался на бомбе.
    FINISHED("face-glasses.png");   // Игра успешно пройдена.

    private final String fileName;
}
