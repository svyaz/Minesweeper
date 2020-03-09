package com.github.svyaz.minesweeper.gamemodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Внешность ячейки.
 */
@Getter
@AllArgsConstructor
public enum CellLook {
    OPEN_0("empty.png"),            // Открыта пустая. Вокруг нет бомб.
    OPEN_1("num-1.png"),            // Открыта. Вокруг 1 бомба.
    OPEN_2("num-2.png"),            // Открыта. Вокруг 2 бомбы.
    OPEN_3("num-3.png"),            // Открыта. Вокруг 3 бомбы.
    OPEN_4("num-4.png"),            // Открыта. Вокруг 4 бомбы.
    OPEN_5("num-5.png"),            // Открыта. Вокруг 5 бомб.
    OPEN_6("num-6.png"),            // Открыта. Вокруг 6 бомб.
    OPEN_7("num-7.png"),            // Открыта. Вокруг 7 бомб.
    OPEN_8("num-8.png"),            // Открыта. Вокруг 8 бомб.
    CLOSED_CLEAR("closed.png"),     // Закрыта. Без флага.
    CLOSED_FLAGGED("flag.png"),     // Закрыта. Помечена флагом.
    BOMB_CLEAR("bomb.png"),         // Бомба. Открывается когда игра проиграна.
    BOMB_BANG("bang.png"),          // Бомба с взрывом, на которой игрок взорвался. Открывается когда игра проиграна.
    BOMB_WRONG("bomb-wrong.png");   // Зачеркнутая бомба. На месте неправильно установленного флага, когда игра проиграна. ;

    private final String fileName;
}
