package com.github.svyaz.minesweeper.gamemodel;

/**
 * Внешность ячейки.
 */
public enum CellLook {
    OPEN_0,         // Открыта пустая. Вокруг нет бомб.
    OPEN_1,         // Открыта. Вокруг 1 бомба.
    OPEN_2,         // Открыта. Вокруг 2 бомбы.
    OPEN_3,         // Открыта. Вокруг 3 бомбы.
    OPEN_4,         // Открыта. Вокруг 4 бомбы.
    OPEN_5,         // Открыта. Вокруг 5 бомб.
    OPEN_6,         // Открыта. Вокруг 6 бомб.
    OPEN_7,         // Открыта. Вокруг 7 бомб.
    OPEN_8,         // Открыта. Вокруг 8 бомб.
    CLOSED_CLEAR,   // Закрыта. Без флага.
    CLOSED_FLAGGED, // Закрыта. Помечена флагом.
    BOMB_CLEAR,     // Бомба. Открывается когда игра проиграна.
    BOMB_BANG,      // Бомба с взрывом, на которой игрок взорвался. Открывается когда игра проиграна.
    BOMB_WRONG,     // Зачеркнутая бомба. На месте неправильно установленного флага, когда игра проиграна.
}
