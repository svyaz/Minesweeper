package com.github.svyaz.minesweeper.view.gui;

import com.github.svyaz.minesweeper.gamemodel.Cell;
import com.github.svyaz.minesweeper.gamemodel.CellLook;
import com.github.svyaz.minesweeper.gamemodel.Game;
import com.github.svyaz.minesweeper.gamemodel.GameStatus;
import com.github.svyaz.minesweeper.gamemodel.commands.*;
import com.github.svyaz.minesweeper.view.GameView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GuiView implements GameView {
    private int rows;
    private int columns;

    // Main controls
    private JFrame frame;
    private JLabel timeLabel;
    private JLabel bombsLabel;
    private JButton mainButton;
    private JPanel fieldPanel;
    private JLabelCell[][] cells;
    private MouseAdapter cellsMouseAdapter;

    // Menu controls
    private JMenuItem newGameItem;
    private JMenuItem scoresItem;
    private JMenuItem modeRookieItem;
    private JMenuItem modeFanItem;
    private JMenuItem modeProItem;
    private JMenuItem modeFreeItem;
    private JMenuItem exitItem;
    private JMenuItem helpItem;
    private JMenuItem aboutItem;

    private HashMap<CellLook, ImageIcon> fieldIcons = new HashMap<>();
    private HashMap<GameStatus, ImageIcon> mainButtonIcons = new HashMap<>();

    public GuiView() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            loadMainButtonIcons();
            loadFieldIcons();
            addMenuComponents();
            addGameComponents(frame.getContentPane());

            /*Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation(screenSize.width / 3, screenSize.height / 3);*/
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        });
    }

    private void addMenuComponents() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        JMenu infoMenu = new JMenu("Info");

        // === Game menu ===
        newGameItem = new JMenuItem("New game");
        scoresItem = new JMenuItem("High scores ...");
        exitItem = new JMenuItem("Exit");
        // --- modes ---
        modeRookieItem = new JMenuItem("Rookie");
        modeFanItem = new JMenuItem("Fan");
        modeProItem = new JMenuItem("Professional");
        modeFreeItem = new JMenuItem("Free ...");
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
        helpItem = new JMenuItem("Help ...");
        aboutItem = new JMenuItem("About program ...");
        infoMenu.add(helpItem);
        infoMenu.addSeparator(); // -----
        infoMenu.add(aboutItem);

        // === Add to menuBar and frame ===
        menuBar.add(gameMenu);
        menuBar.add(infoMenu);
        frame.setJMenuBar(menuBar);
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
        mainButton.setIcon(mainButtonIcons.get(GameStatus.NOT_STARTED));
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
        fieldPanel = new JPanel();
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

    private void loadMainButtonIcons() {
        try {
            for (int i = 0; i < GameStatus.values().length; i++) {
                String iconPath = null;

                switch (GameStatus.values()[i]) {
                    case NOT_STARTED:
                    case STARTED:
                        iconPath = "../../resources/images/face-smile.png";
                        break;
                    case LOST:
                        iconPath = "../../resources/images/face-gameover.png";
                        break;
                    case FINISHED:
                        iconPath = "../../resources/images/face-glasses.png";
                        break;
                }

                ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResource(iconPath)));
                mainButtonIcons.put(GameStatus.values()[i], icon);
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
            cells = new JLabelCell[rows][columns];
            timeLabel.setText(Game.getGameTimeString(0));
            bombsLabel.setText(String.valueOf(bombsCount));
            mainButton.setIcon(mainButtonIcons.get(GameStatus.STARTED));

            GridLayout fieldLayout = new GridLayout(rows, columns);
            fieldPanel.removeAll();
            fieldPanel.setLayout(fieldLayout);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    cells[i][j] = new JLabelCell(i, j, fieldIcons.get(CellLook.CLOSED_CLEAR));
                    cells[i][j].addMouseListener(cellsMouseAdapter);
                    fieldPanel.add(cells[i][j]);
                }
            }
            frame.pack();
        });
    }

    @Override
    public void updateField(List<Cell> cellsList) {
        SwingUtilities.invokeLater(() -> {
            for (Cell cell : cellsList) {
                int row = cell.getRow();
                int column = cell.getColumn();
                this.cells[row][column].setIcon(fieldIcons.get(cell.getCellLook()));
            }
        });
    }

    @Override
    public void updateGameTimeString(String timeString) {
        SwingUtilities.invokeLater(() -> timeLabel.setText(timeString));
    }

    @Override
    public void updateBombsCount(int bombsCount) {
        SwingUtilities.invokeLater(() -> bombsLabel.setText(String.valueOf(bombsCount)));
    }

    @Override
    public void printField() {
        SwingUtilities.invokeLater(() -> fieldPanel.updateUI());
    }

    @Override
    public void startView(Game gameController) {
        // Register listeners
        SwingUtilities.invokeLater(() -> {
            ActionListener restartListener = e -> gameController.executeCommand(new RestartCommand());

            mainButton.addActionListener(restartListener);
            newGameItem.addActionListener(restartListener);
            scoresItem.addActionListener(e -> gameController.executeCommand(new ShowScoresCommand()));
            exitItem.addActionListener(e -> gameController.executeCommand(new ExitCommand()));
            aboutItem.addActionListener(e -> gameController.executeCommand(new ShowAboutCommand()));

            helpItem.addActionListener(e -> System.out.println("HELP!!!"));

            modeRookieItem.addActionListener(e -> gameController.executeCommand(new StartPresetGameCommand("rookie")));
            modeFanItem.addActionListener(e -> gameController.executeCommand(new StartPresetGameCommand("fan")));
            modeProItem.addActionListener(e -> gameController.executeCommand(new StartPresetGameCommand("pro")));
            //modeFreeItem.addActionListener(e -> gameController.executeCommand(new StartFreeGameCommand()));
            //TODO

            // Game events
            cellsMouseAdapter = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabelCell cell = (JLabelCell) e.getSource();

                    int buttonIndex = e.getButton();
                    Command command = null;
                    int row = cell.getRow();
                    int column = cell.getColumn();

                    if (buttonIndex == MouseEvent.BUTTON1) {
                        // левая кнопка
                        command = new OpenCellCommand(row, column);
                    } else if (buttonIndex == MouseEvent.BUTTON3) {
                        // правая кнопка
                        command = new FlagCellCommand(row, column);

                    } else {
                        //TODO надо научиться обрабатывать одновременное нажатие двух кнопок
                        //command = new OpenNeighborsCommand(row, column);
                    }
                    gameController.executeCommand(command);

                }

            };

            // В первый раз после запуска надо назначить листенера тут. Иначе на момент заполнения cell[][]
            // cellsMouseAdapter = null и в первой игре после запуска ячейки не нажимаются.
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    cells[i][j].addMouseListener(cellsMouseAdapter);
                }
            }
        });
    }

    @Override
    public void showMessage(String message) {
        switch (message) {
            case "MSG_GAME_FINISHED":
                mainButton.setIcon(mainButtonIcons.get(GameStatus.FINISHED));
                break;
            case "MSG_GAME_LOST":
                mainButton.setIcon(mainButtonIcons.get(GameStatus.LOST));
                break;
            case "MSG_GAME_ALREADY_FINISHED":
                break;
            default:
                JOptionPane.showMessageDialog(frame, message, "MESSAGE_TITLE", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }

    @Override
    public void showScores(HashMap<String, String> scoresMap) {
        StringBuilder sb = new StringBuilder();
        scoresMap.forEach((key, value) -> sb
                .append(key)
                .append(value)
                .append(System.lineSeparator()));
        JOptionPane.showMessageDialog(frame, sb.toString(), "SCORES_TITLE", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String getUserName() {
        // === Modal frame ===
        JDialog userForm = new JDialog(frame, "Enter user name", true);
        userForm.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        userForm.setSize(250, 140);
        userForm.setResizable(false);

        Container pane = userForm.getContentPane();
        GridBagLayout layout = new GridBagLayout();
        pane.setLayout(layout);
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        GridBagConstraints constraints = new GridBagConstraints();

        // === Label ===
        JLabel label = new JLabel("New record! Enter your name:");
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.3;
        layout.setConstraints(label, constraints);
        pane.add(label);

        // === Text ===
        JTextField textField = new JTextField(null, "DEFAULT_USER_NAME", 0);
        constraints.gridy = 1;
        constraints.weighty = 0.3;
        layout.setConstraints(textField, constraints);
        pane.add(textField);

        // === Button ===
        JButton button = new JButton("SAVE_ACTION");
        button.addActionListener(l -> userForm.dispose());
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = 2;
        constraints.weighty = 0.4;
        constraints.insets = new Insets(10, 10, 10, 10);
        layout.setConstraints(button, constraints);
        pane.add(button);

        Rectangle frameBounds = frame.getBounds();
        Rectangle formBounds = userForm.getBounds();
        userForm.setLocation(frameBounds.x + frameBounds.width / 2 - formBounds.width / 2,
                frameBounds.y + frameBounds.height / 2 - formBounds.height / 2);

        userForm.setVisible(true);

        String text = textField.getText();
        if (text == null || text.isEmpty()) {
            return "DEFAULT_USER_NAME";
        }
        return text;
    }
}
