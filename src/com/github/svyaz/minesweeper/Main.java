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
    public static void main(String[] args) {

        /*GameView gameView = null;

        if (args.length >= 2 && args[0].equals("-mode")) {
            switch (args[1]) {
                case "text":
                    gameView = new TextView();
                    break;
                case "gui":
                    gameView = new GuiView();
                    break;
                default:
                    System.out.println("Unknown mode. Run program with parameter -help");
                    System.exit(0);
            }
        } else if (args.length >= 1 && args[0].equals("-help")) {
            System.out.println("Prints help!");
            System.exit(0);
        } else if (args.length == 0) {
            gameView = new GuiView();
        } else {
            System.out.println("Unknown parameters.");
            System.out.println();
            System.out.println("Prints help!");
            System.exit(0);
        }*/

        GameView gameView = new TextView();

        Game gameController = new Game(gameView,
                new RookieMode(),
                new FanMode(),
                new ProMode(),
                new FreeMode(9, 9, 10)
        );
        gameController.runGame();
    }
}

//TODO сделать нормальные сообщения в Main.
//TODO сделать иконку приложения
