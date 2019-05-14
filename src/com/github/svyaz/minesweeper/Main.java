package com.github.svyaz.minesweeper;

import com.github.svyaz.minesweeper.gamemodel.Game;
import com.github.svyaz.minesweeper.gamemodel.modes.FanMode;
import com.github.svyaz.minesweeper.gamemodel.modes.FreeMode;
import com.github.svyaz.minesweeper.gamemodel.modes.ProMode;
import com.github.svyaz.minesweeper.gamemodel.modes.RookieMode;

public class Main {
    public static void main(String[] args) {
        Game gameController = new Game(
                new RookieMode(),
                new FanMode(),
                new ProMode(),
                new FreeMode(9, 9, 10)
        );
        gameController.runGame();
    }
}

/*TODO сделать параметры запуска:
-mode:gui|text
-help
 */
