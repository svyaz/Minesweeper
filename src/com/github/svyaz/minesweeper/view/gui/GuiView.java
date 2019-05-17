package com.github.svyaz.minesweeper.view.gui;

import com.github.svyaz.minesweeper.gamemodel.Cell;
import com.github.svyaz.minesweeper.gamemodel.commands.Command;
import com.github.svyaz.minesweeper.gamemodel.modes.GameMode;
import com.github.svyaz.minesweeper.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class GuiView implements GameView {
    private JFrame frame;

    public GuiView() {
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Component pane = frame.getContentPane();

        addMenuComponents();
        addGameComponents(pane);


        frame.pack();
        frame.setVisible(true);
    }

    private void addMenuComponents() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu infoMenu = new JMenu("Info");

        // === Game menu ===
        JMenuItem newGameItem = new JMenuItem("New game");
        JMenuItem scoresItem = new JMenuItem("High scores ...");
        JMenuItem exitItem = new JMenuItem("Exit");
        // --- modes ---
        JMenuItem modeRookieItem = new JMenuItem("Rookie");
        JMenuItem modeFanItem = new JMenuItem("Fan");
        JMenuItem modeProItem = new JMenuItem("Professional");
        JMenuItem modeFreeItem = new JMenuItem("Free ...");
        gameMenu.add(newGameItem);
        gameMenu.addSeparator(); // -----
        gameMenu.add(modeRookieItem);
        gameMenu.add(modeFanItem);
        gameMenu.add(modeProItem);
        gameMenu.add(modeFreeItem);
        gameMenu.addSeparator(); // -----
        gameMenu.add(scoresItem);
        gameMenu.addSeparator(); // -----
        gameMenu.add(exitItem);

        // === Info menu ===
        JMenuItem helpItem = new JMenuItem("Help ...");
        JMenuItem aboutItem = new JMenuItem("About program ...");
        infoMenu.add(helpItem);
        infoMenu.addSeparator(); // -----
        infoMenu.add(aboutItem);

        // === Add to menuBar and frame ===
        menuBar.add(gameMenu);
        menuBar.add(infoMenu);
        frame.setJMenuBar(menuBar);

        // === Actions ===
        exitItem.addActionListener(e -> System.exit(0));
    }

    private void addGameComponents(Component pane) {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();


    }

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
        return new Command() {
            @Override
            public void execute() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("From GUI");
            }
        };
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
