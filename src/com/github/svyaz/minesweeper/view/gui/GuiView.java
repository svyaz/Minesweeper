package com.github.svyaz.minesweeper.view.gui;

import com.github.svyaz.minesweeper.gamemodel.Cell;
import com.github.svyaz.minesweeper.gamemodel.CellLook;
import com.github.svyaz.minesweeper.gamemodel.Game;
import com.github.svyaz.minesweeper.view.GameView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GuiView implements GameView {
    private int rows;
    private int columns;
    private int bombsCount;

    private JFrame frame;
    private JLabel timeLabel;
    private JLabel bombsLabel;
    private JButton mainButton;
    private JPanel fieldPanel = new JPanel();
    private JLabel[][] cells;

    private HashMap<CellLook, ImageIcon> fieldIcons = new HashMap<>();

    public GuiView() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Minesweeper");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);

            loadFieldIcons();
            addMenuComponents();
            addGameComponents(frame.getContentPane());
        });
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

        //exitItem.addActionListener(e -> System.exit(0));
    }

    private void addGameComponents(Container mainPanel) {
        GridBagLayout layout = new GridBagLayout();
        mainPanel.setLayout(layout);
        mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        GridBagConstraints constraints = new GridBagConstraints();

        // === Bombs remain label ===
        bombsLabel = new JLabel();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.ipadx = 30;
        constraints.ipady = 0;
        constraints.weightx = 0.33;
        constraints.weighty = 0.2;
        layout.setConstraints(bombsLabel, constraints);
        mainPanel.add(bombsLabel);

        // === Main center button ===
        mainButton = new JButton();
        try {
            Image buttonImage = ImageIO.read(getClass().getResource("../../resources/images/face-smile.png"));
            mainButton.setIcon(new ImageIcon(buttonImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 0.34;
        constraints.weighty = 0.2;
        layout.setConstraints(mainButton, constraints);
        mainPanel.add(mainButton);

        // === Game time label ===
        timeLabel = new JLabel("0:00:00");
        constraints.anchor = GridBagConstraints.NORTHEAST;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 0.33;
        constraints.weighty = 0.2;
        layout.setConstraints(timeLabel, constraints);
        mainPanel.add(timeLabel);

        // === Game field panel ===
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridheight = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 10, 15, 10);
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.8;
        layout.setConstraints(fieldPanel, constraints);
        mainPanel.add(fieldPanel);
    }

    private void loadFieldIcons() {
        try {
            for (int i = 0; i < CellLook.values().length; i++) {
                String iconPath = null;

                switch (CellLook.values()[i]) {
                    case OPEN_0:
                        iconPath = "../../resources/images/empty.png";
                        break;
                    case OPEN_1:
                        iconPath = "../../resources/images/num-1.png";
                        break;
                    case OPEN_2:
                        iconPath = "../../resources/images/num-2.png";
                        break;
                    case OPEN_3:
                        iconPath = "../../resources/images/num-3.png";
                        break;
                    case OPEN_4:
                        iconPath = "../../resources/images/num-4.png";
                        break;
                    case OPEN_5:
                        iconPath = "../../resources/images/num-5.png";
                        break;
                    case OPEN_6:
                        iconPath = "../../resources/images/num-6.png";
                        break;
                    case OPEN_7:
                        iconPath = "../../resources/images/num-7.png";
                        break;
                    case OPEN_8:
                        iconPath = "../../resources/images/num-8.png";
                        break;
                    case CLOSED_CLEAR:
                        iconPath = "../../resources/images/closed.png";
                        break;
                    case CLOSED_FLAGGED:
                        iconPath = "../../resources/images/flag.png";
                        break;
                    case BOMB_CLEAR:
                        iconPath = "../../resources/images/bomb.png";
                        break;
                    case BOMB_BANG:
                        iconPath = "../../resources/images/bang.png";
                        break;
                    case BOMB_WRONG:
                        iconPath = "../../resources/images/bomb-wrong.png";
                        break;
                }

                ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResource(iconPath)));
                fieldIcons.put(CellLook.values()[i], icon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initView(String modeDescription, int rows, int columns, int bombsCount) {
        SwingUtilities.invokeLater(() -> {
            frame.setTitle(modeDescription);
            this.rows = rows;
            this.columns = columns;
            this.bombsCount = bombsCount;
            cells = new JLabel[rows][columns];
            timeLabel.setText(Game.getGameTimeString(0));
            bombsLabel.setText(String.valueOf(bombsCount));

            GridLayout fieldLayout = new GridLayout(rows, columns);
            fieldPanel.setLayout(fieldLayout);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    cells[i][j] = new JLabel(fieldIcons.get(CellLook.CLOSED_CLEAR));
                    fieldPanel.add(cells[i][j]);
                }
            }
            frame.pack();
        });
    }

    @Override
    public void updateField(List<Cell> cellsList) {

    }

    @Override
    public void updateGameTimeString(String timeString) {
        //timeLabel.setText(timeString);
    }

    @Override
    public void updateBombsCount(int bombsCount) {

    }

    @Override
    public void printField() {
        // Should it do anything?
    }

    @Override
    public void startView(Game gameController) {


        /*return new Command() {
            @Override
            public void execute() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("From GUI");
            }
        };*/
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
