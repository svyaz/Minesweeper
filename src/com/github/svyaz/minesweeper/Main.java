package com.github.svyaz.minesweeper;

import com.github.svyaz.minesweeper.gamemodel.Game;
import com.github.svyaz.minesweeper.gamemodel.gamemodes.FanMode;
import com.github.svyaz.minesweeper.gamemodel.gamemodes.FreeMode;
import com.github.svyaz.minesweeper.gamemodel.gamemodes.ProMode;
import com.github.svyaz.minesweeper.gamemodel.gamemodes.RookieMode;

public class Main {
    public static void main(String[] args) {

        Game gameController = new Game(
                new RookieMode(),
                new FanMode(),
                new ProMode(),
                new FreeMode(9, 9, 10)
        );
        gameController.init();

    }
}

/*TODO
Команды запуска:
-mode:gui|text
-level:rookie|fan|pro|free
-free:n,m,b
-scores
-about
-help
 */