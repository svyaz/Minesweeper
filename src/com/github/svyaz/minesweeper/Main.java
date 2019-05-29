package com.github.svyaz.minesweeper;

import com.github.svyaz.minesweeper.gamemodel.Game;
import com.github.svyaz.minesweeper.gamemodel.modes.FanMode;
import com.github.svyaz.minesweeper.gamemodel.modes.FreeMode;
import com.github.svyaz.minesweeper.gamemodel.modes.ProMode;
import com.github.svyaz.minesweeper.gamemodel.modes.RookieMode;
import com.github.svyaz.minesweeper.view.GameView;
import com.github.svyaz.minesweeper.view.gui.GuiView;
import com.github.svyaz.minesweeper.view.text.TextView;

public class Main {
    private static final String MESSAGE_UNKNOWN_MODE = "Неизвестный режим игры.";
    private static final String MESSAGE_UNKNOWN_PARAMS = "Неизвестные параметры.";
    private static final String MESSAGE_HELP = "Запуск программы:\n" +
            "Minesweeper.jar [-mode [text | gui] | -help]\n" +
            "  -mode - режим запуска. text - текстовый, gui - графический.\n" +
            "  -help - показ этой справки.\n";

    public static void main(String[] args) {
        GameView gameView = null;

        if (args.length >= 2 && args[0].matches("-[mM][oO][dD][eE]")) {
            if (args[1].matches("[tT][eE][xX][tT]")) {
                gameView = new TextView();
            } else if (args[1].matches("[gG][uU][iI]")) {
                gameView = new GuiView();
            } else {
                System.out.println(MESSAGE_UNKNOWN_MODE);
                System.out.println();
                System.out.println(MESSAGE_HELP);
                System.exit(0);
            }
        } else if (args.length >= 1 && args[0].matches("-[hH][eE][lL][pP]")) {
            System.out.println(MESSAGE_HELP);
            System.exit(0);
        } else if (args.length == 0) {
            gameView = new GuiView();
        } else {
            System.out.println(MESSAGE_UNKNOWN_PARAMS);
            System.out.println();
            System.out.println(MESSAGE_HELP);
            System.exit(0);
        }

        Game gameController = new Game(gameView,
                new RookieMode(),
                new FanMode(),
                new ProMode(),
                new FreeMode(9, 9, 10)
        );
        gameController.runGame();
    }
}
