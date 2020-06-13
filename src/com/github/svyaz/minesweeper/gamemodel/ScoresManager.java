package com.github.svyaz.minesweeper.gamemodel;

import com.github.svyaz.minesweeper.gamemodel.modes.GameMode;

import java.io.*;
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
    private Map<String, ScoreElement> scores;

    /**
     * Доступные режимы игры
     */
    private Map<String, GameMode> gameModes;

    ScoresManager(Map<String, GameMode> gameModes) {
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
                if (text == null || text.isEmpty()) {
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

    /**
     * Метод проверяет является ли время новым рекордом.
     *
     * @param gameMode режим игры, для которого провряется.
     * @param time     время законченной игры.
     * @return true если это новый рекорд, false - если время больше чем рекорд.
     */
    boolean isNewRecord(GameMode gameMode, long time) {
        return Optional.ofNullable(scores.get(gameMode.getName()))
                .map(el -> el.getGameTime() > time)
                .orElse(true);
    }

    /**
     * Устанавливает новый рекорд в таблице.
     *
     * @param gameMode режим игры.
     * @param userName имя игрока, поставившего рекорд.
     * @param time     время рекордной игры.
     */
    void setNewRecord(GameMode gameMode, String userName, long time) {
        ScoreElement element = new ScoreElement(gameMode.getName(), time, userName);
        scores.put(gameMode.getName(), element);
    }

    /**
     * Сохраняет текущие рекорды в файл.
     */
    void save() {
        try (PrintWriter writer = new PrintWriter(scoresFileName)) {
            scores.forEach((k, v) -> writer.print(String.format("%s|%s|%s%n", k, v.getGameTime(), v.getUserName())));
            writer.flush();
        } catch (FileNotFoundException ignore) {
        }
    }
}
