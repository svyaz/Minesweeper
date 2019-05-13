package com.github.svyaz.minesweeper.gamemodel;

import com.github.svyaz.minesweeper.gamemodel.modes.GameMode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Менеджер для работы с таблицей рекордов
 */
class ScoresManager {
    /**
     * Путь к файлу с записями
     */
    private static final String scoresFileName = "HighScores.txt";

    /**
     * Записи о рекордах для каждого из игрового режимов
     */
    private HashMap<String, ScoreElement> scores;

    /**
     * Доступные режимы игры
     */
    private HashMap<String, GameMode> gameModes;

    ScoresManager(HashMap<String, GameMode> gameModes) {
        this.gameModes = gameModes;
        this.scores = new HashMap<>();
        loadHighScores();
    }

    /**
     * Загрузка таблицы рекордов из файла.
     */
    private void loadHighScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(scoresFileName))) {
            while (true) {
                String text = reader.readLine();
                if (text == null) {
                    break;
                }
                //ScoreElement element = new ScoreElement(text);
                String[] elements = text.split("\\|");
                String modeString = elements[0];
                long gameTime = Long.parseLong(elements[1]);
                String userName = elements[2];

                if (!modeString.equals("free") && gameModes.containsKey(modeString)) {
                    ScoreElement scoreElement = new ScoreElement(modeString, gameTime, userName);
                    scores.put(modeString, scoreElement);
                }
            }
        } catch (IOException | IllegalArgumentException ignore) {
        }
    }

    /**
     * Рекорды переводит в другую HashMap с ключем "Название режима игры" для передачи в view.
     *
     * @return HashMap для передачи в view.
     */
    HashMap<String, String> getScoresMap() {
        HashMap<String, String> result = new HashMap<>();

        for (ScoreElement element : scores.values()) {
            String newKey = gameModes.get(element.getModeString()).getDescription();
            String newValue = String.format(" %s %s",
                    Game.getGameTimeString(element.getGameTime()),
                    element.getUserName());
            result.put(newKey, newValue);
        }
        return result;
    }
}
