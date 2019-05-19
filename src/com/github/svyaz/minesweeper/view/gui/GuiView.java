package com.github.svyaz.minesweeper.view.gui;

import com.github.svyaz.minesweeper.gamemodel.Cell;
import com.github.svyaz.minesweeper.gamemodel.commands.Command;
import com.github.svyaz.minesweeper.view.GameView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GuiView implements GameView {
    private JFrame frame;
    private JLabel timeLabel;
    private JLabel bombsLabel;
    private JButton mainButton;
    private JPanel fieldPanel;

    public GuiView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame("Minesweeper");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //frame.setResizable(false);
                Container pane = frame.getContentPane();

                addMenuComponents();
                addGameComponents(pane);

                frame.setSize(400, 200);
                //frame.pack();
                frame.setVisible(true);
            }
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
        exitItem.addActionListener(e -> System.exit(0));
    }

    private void addGameComponents(Container mainPanel) {
        GridBagLayout layout = new GridBagLayout();
        mainPanel.setLayout(layout);
        mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        GridBagConstraints constraints = new GridBagConstraints();

        // === Bombs remain label ===
        bombsLabel = new JLabel("99");
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
        GridLayout fieldLayout = new GridLayout(10,10);
        fieldPanel = new JPanel(fieldLayout);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                fieldPanel.add(new JLabel(i + "-" + j));
            }
        }

        //fieldPanel.setSize(200, 200);
        //fieldPanel.setBackground(Color.GREEN);
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridheight = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        layout.setConstraints(fieldPanel, constraints);
        mainPanel.add(fieldPanel);
    }

    @Override
    public void initView(String modeDescription, int rows, int columns, int bombsCount) {

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
