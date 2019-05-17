package com.github.svyaz.minesweeper.view.gui;

import com.github.svyaz.minesweeper.gamemodel.Cell;
import com.github.svyaz.minesweeper.gamemodel.commands.Command;
import com.github.svyaz.minesweeper.view.GameView;

import java.util.HashMap;
import java.util.List;

public class GuiView implements GameView {
    @Override
    public void initView(String modeDescription, int rows, int columns, int bombsCount) {

    }

    @Override
    public void updateField(List<Cell> cellsList) {

    }

    @Override
    public void updateGameTimeString(String timeString) {

    }

    @Override
    public void updateBombsCount(int bombsCount) {

    }

    @Override
    public void printField() {

    }

    @Override
    public Command waitCommand() {
        return null;
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showScores(HashMap<String, String> scoresMap) {

    }

    @Override
    public String getUserName() {
        return null;
    }
}
