package com.github.svyaz.minesweeper;

import com.github.svyaz.minesweeper.gamemodel.Game;
import com.github.svyaz.minesweeper.gamemodel.modes.*;
import com.github.svyaz.minesweeper.view.GameView;
import com.github.svyaz.minesweeper.view.gui.GuiView;
import com.github.svyaz.minesweeper.view.text.TextView;
import org.apache.commons.cli.*;

public class Main {
    private static final String MESSAGE_PARSE_ERROR = "Невозможно разобрать входные параметры";
    private static final String MESSAGE_UNKNOWN_MODE = "Неизвестный режим игры.";
    private static final String MESSAGE_UNKNOWN_PARAMS = "Неизвестные параметры.";
    private static final String MESSAGE_HELP = "Запуск программы:" + System.lineSeparator() +
            "Minesweeper.jar [-mode [text | gui] | -help]" + System.lineSeparator() +
            "  -mode - режим запуска. text - текстовый, gui - графический. По умолчанию - gui" + System.lineSeparator() +
            "  -help - показ этой справки.";

    public static void main(String[] args) {
        Options options = new Options();

        Option modeOption = Option.builder("mode")
                .hasArg(true)
                .required(false)
                .desc("Режим запуска. text - текстовый, gui - графический.")
                .build();

        Option helpOption = Option.builder("help")
                .hasArg(false)
                .required(false)
                .desc("Вызов справки.")
                .build();

        options.addOption(modeOption);
        options.addOption(helpOption);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmdLine = parser.parse(options, args);

            if (cmdLine.hasOption("help")) {
                System.out.println(MESSAGE_HELP);
                System.exit(0);
            }

            String mode = cmdLine.getOptionValue("mode", "gui");

            GameView gameView = null;

            if (mode.equals("text")) {
                gameView = new TextView();
            } else if (mode.equals("gui")) {
                gameView = new GuiView();
            } else {
                System.out.println(MESSAGE_UNKNOWN_MODE);
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

        } catch (UnrecognizedOptionException e) {
            System.out.println(MESSAGE_UNKNOWN_PARAMS);
            System.out.println();
            System.out.println(MESSAGE_HELP);

        } catch (ParseException e) {
            System.out.println(MESSAGE_PARSE_ERROR);
            System.out.println(e.getMessage());
        }
    }
}
