package com.github.svyaz.minesweeper.view.gui;

import com.github.svyaz.minesweeper.gamemodel.Cell;
import com.github.svyaz.minesweeper.gamemodel.CellLook;
import com.github.svyaz.minesweeper.gamemodel.Game;
import com.github.svyaz.minesweeper.gamemodel.GameStatus;
import com.github.svyaz.minesweeper.gamemodel.commands.*;
import com.github.svyaz.minesweeper.gamemodel.modes.FreeMode;
import com.github.svyaz.minesweeper.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class GuiView implements GameView {
    private static final String IMAGES_PATH = "/com/github/svyaz/minesweeper/resources/";
    private static final int KEY_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    private int rows;
    private int columns;
    private int currentModeBombsCount;  // используется только для значения по умолчанию в форме FreeGame

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

    // Флаги для определения нажатия обоих кнопок мыши
    private boolean isLeftPressed = false;
    private boolean isRightPressed = false;

    private ResourceBundle messages = ResourceBundle.getBundle("com.github.svyaz.minesweeper.view.gui.Messages");

    public GuiView() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignore) {
            }
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            loadMainButtonIcons();
            loadFieldIcons();
            addMenuComponents();
            addGameComponents(frame.getContentPane());

            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        });
    }

    private void addMenuComponents() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu(messages.getString("MENU_GAME"));
        JMenu infoMenu = new JMenu(messages.getString("MENU_INFO"));

        // === Game menu ===
        newGameItem = new JMenuItem(messages.getString("MENU_ITEM_NEW_GAME"));
        scoresItem = new JMenuItem(messages.getString("MENU_ITEM_HIGH_SCORES"));
        exitItem = new JMenuItem(messages.getString("MENU_ITEM_EXIT"));
        // --- modes ---
        modeRookieItem = new JMenuItem(messages.getString("MENU_ITEM_ROOKIE"));
        modeFanItem = new JMenuItem(messages.getString("MENU_ITEM_FAN"));
        modeProItem = new JMenuItem(messages.getString("MENU_ITEM_PRO"));
        modeFreeItem = new JMenuItem(messages.getString("MENU_ITEM_FREE"));
        gameMenu.add(newGameItem);
        gameMenu.addSeparator();
        gameMenu.add(modeRookieItem);
        gameMenu.add(modeFanItem);
        gameMenu.add(modeProItem);
        gameMenu.add(modeFreeItem);
        gameMenu.addSeparator();
        gameMenu.add(scoresItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        // === Info menu ===
        helpItem = new JMenuItem(messages.getString("MENU_ITEM_HELP"));
        aboutItem = new JMenuItem(messages.getString("MENU_ITEM_ABOUT"));
        infoMenu.add(helpItem);
        infoMenu.addSeparator();
        infoMenu.add(aboutItem);

        // === Add to menuBar and frame ===
        menuBar.add(gameMenu);
        menuBar.add(infoMenu);
        frame.setJMenuBar(menuBar);

        // === Set hot keys ===
        newGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KEY_MASK));
        scoresItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KEY_MASK));
        helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KEY_MASK));
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KEY_MASK));
    }

    private void addGameComponents(Container mainPanel) {
        GridBagLayout layout = new GridBagLayout();
        mainPanel.setLayout(layout);
        mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        GridBagConstraints constraints = new GridBagConstraints();

        Font font = new Font("Arial", Font.PLAIN, 16);
        Dimension topDimension = new Dimension(70, 30);

        // === Bombs remain label ===
        bombsLabel = new JLabel();
        bombsLabel.setFont(font);
        JPanel bombsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bombsPanel.setMinimumSize(topDimension);
        bombsPanel.setPreferredSize(topDimension);
        bombsPanel.add(bombsLabel);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 8, 0, 0);
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        layout.setConstraints(bombsPanel, constraints);
        mainPanel.add(bombsPanel);

        // === Main center button ===
        mainButton = new JButton();
        mainButton.setIcon(mainButtonIcons.get(GameStatus.NOT_STARTED));
        mainButton.setBorderPainted(true);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.insets = new Insets(8, 0, 8, 0);
        layout.setConstraints(mainButton, constraints);
        mainPanel.add(mainButton);

        // === Game time label ===
        timeLabel = new JLabel("0:00:00");
        timeLabel.setFont(font);
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        timePanel.setMinimumSize(topDimension);
        timePanel.setPreferredSize(topDimension);
        timePanel.add(timeLabel);
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 2;
        constraints.insets = new Insets(0, 0, 0, 8);
        layout.setConstraints(timePanel, constraints);
        mainPanel.add(timePanel);

        // === Game field panel ===
        fieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(0, 10, 10, 10);
        layout.setConstraints(fieldPanel, constraints);
        mainPanel.add(fieldPanel);
    }

    private void loadFieldIcons() {
        Arrays.stream(CellLook.values())
                .forEach(v -> {
                    ImageIcon icon = new ImageIcon(getClass().getResource(IMAGES_PATH + v.getFileName()));
                    fieldIcons.put(v, icon);
                });
    }

    private void loadMainButtonIcons() {
        Arrays.stream(GameStatus.values())
                .forEach(v -> {
                    ImageIcon icon = new ImageIcon(getClass().getResource(IMAGES_PATH + v.getFileName()));
                    mainButtonIcons.put(v, icon);
                });
    }

    @Override
    public void initView(String modeDescription, int rows, int columns, int bombsCount) {
        SwingUtilities.invokeLater(() -> {
            frame.setTitle(messages.getString(modeDescription));
            this.rows = rows;
            this.columns = columns;
            this.currentModeBombsCount = bombsCount;
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
        SwingUtilities.invokeLater(() -> cellsList.forEach(c ->
                cells[c.getRow()][c.getColumn()].setIcon(fieldIcons.get(c.getCellLook()))
        ));
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
            helpItem.addActionListener(e -> showMessage("HELP_TEXT"));

            // Game modes
            modeRookieItem.addActionListener(e -> gameController.executeCommand(new StartPresetGameCommand("rookie")));
            modeFanItem.addActionListener(e -> gameController.executeCommand(new StartPresetGameCommand("fan")));
            modeProItem.addActionListener(e -> gameController.executeCommand(new StartPresetGameCommand("pro")));

            modeFreeItem.addActionListener(e -> {
                FreeGameDialog freeGameDialog = new FreeGameDialog(frame, rows, columns, currentModeBombsCount);
                freeGameDialog.setComponentsText(messages.getString("FREE_DIALOG_TITLE"),
                        messages.getString("FREE_DIALOG_HEIGHT"),
                        messages.getString("FREE_DIALOG_WIDTH"),
                        messages.getString("FREE_DIALOG_BOMBS"),
                        messages.getString("FREE_DIALOG_OK"),
                        String.format(messages.getString("FREE_DIALOG_HEIGHT_HINT"),
                                FreeMode.MIN_ROWS,
                                FreeMode.MAX_ROWS),
                        String.format(messages.getString("FREE_DIALOG_WIDTH_HINT"),
                                FreeMode.MIN_COLUMNS,
                                FreeMode.MAX_COLUMNS),
                        String.format(messages.getString("FREE_DIALOG_BOMBS_HINT"),
                                (int) (FreeMode.MIN_BOMBS_FACTOR * 100.0),
                                (int) (FreeMode.MAX_BOMBS_FACTOR * 100.0))
                );
                Optional.ofNullable(freeGameDialog.getCommand())
                        .ifPresent(gameController::executeCommand);
            });

            // Game events
            cellsMouseAdapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        isLeftPressed = true;
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        isRightPressed = true;
                    } else if (e.getButton() == MouseEvent.BUTTON2) {
                        isLeftPressed = true;
                        isRightPressed = true;
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    JLabelCell cell = (JLabelCell) e.getSource();
                    int row = cell.getRow();
                    int column = cell.getColumn();
                    Command command = null;

                    if (isLeftPressed && isRightPressed) {
                        command = new OpenNeighborsCommand(row, column);
                    } else if (isLeftPressed) {
                        command = new OpenCellCommand(row, column);
                    } else if (isRightPressed) {
                        command = new FlagCellCommand(row, column);
                    }
                    isLeftPressed = false;
                    isRightPressed = false;

                    Optional.ofNullable(command)
                            .ifPresent(gameController::executeCommand);
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
                JOptionPane.showMessageDialog(frame,
                        messages.getString(message),
                        messages.getString("MESSAGES_TITLE"),
                        JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void showScores(Map<String, String> scoresMap) {
        StringBuilder sb = new StringBuilder();
        scoresMap.forEach((key, value) -> sb
                .append(messages.getString(key))
                .append(value)
                .append(System.lineSeparator()));
        JOptionPane.showMessageDialog(frame,
                sb.toString(),
                messages.getString("SCORES_TITLE"),
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String getUserName() {
        UserNameDialog userNameDialog = new UserNameDialog(frame,
                messages.getString("DEFAULT_USERNAME"));
        userNameDialog.setComponentsText(messages.getString("USERNAME_DIALOG_TITLE"),
                messages.getString("USERNAME_DIALOG_MESSAGE"),
                messages.getString("USERNAME_DIALOG_OK"));
        return userNameDialog.getUserName();
    }
}
