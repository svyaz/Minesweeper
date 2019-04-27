package com.github.svyaz.minesweeper;

import com.github.svyaz.minesweeper.gamemodel.gamemodes.GameMode;
import com.github.svyaz.minesweeper.gamemodel.gamemodes.RookieMode;

public class Main {
    public static void main(String[] args) {
        //Field field = new Field(new GameMode("Beginner", 9,9,10));
        //field.printField();
        GameMode mode = new RookieMode();
        System.out.println(mode.getBombsCount());
    }
}

/*
Команды запуска:
-mode:gui|text
-level:rookie|fan|pro|free
-free:n,m,b
-scores
-about
-help
 */