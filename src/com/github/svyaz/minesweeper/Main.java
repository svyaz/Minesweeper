package com.github.svyaz.minesweeper;

import com.github.svyaz.minesweeper.gamemodel.Game;
import com.github.svyaz.minesweeper.gamemodel.gamemodes.RookieMode;

public class Main {
    public static void main(String[] args) {

        Game gameController = new Game(new RookieMode());
        gameController.start();

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