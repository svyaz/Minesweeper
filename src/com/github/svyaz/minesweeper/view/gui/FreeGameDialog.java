package com.github.svyaz.minesweeper.view.gui;

import com.github.svyaz.minesweeper.gamemodel.commands.StartFreeGameCommand;

import javax.swing.*;
import java.awt.*;

class FreeGameDialog {
    private JFrame parentFrame;
    private JDialog dialog;
    private int rows;
    private int columns;
    private int bombsCount;
    private StartFreeGameCommand command = null;
    private JTextField rowsTextField;
    private JTextField columnsTextField;
    private JTextField bombsTextField;
    private JLabel rowsLabel;
    private JLabel columnsLabel;
    private JLabel bombsLabel;
    private JButton okButton;

    FreeGameDialog(JFrame parentFrame, int rows, int columns, int bombsCount) {
        this.parentFrame = parentFrame;
        this.rows = rows;
        this.columns = columns;
        this.bombsCount = bombsCount;
        createDialog();
    }

    private void createDialog() {
        // === Modal frame ===
        dialog = new JDialog(parentFrame, true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setSize(260, 160);
        dialog.setResizable(false);

        Container pane = dialog.getContentPane();
        GridBagLayout layout = new GridBagLayout();
        pane.setLayout(layout);
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        GridBagConstraints constraints = new GridBagConstraints();

        // === Rows text ===
        rowsLabel = new JLabel();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.2;
        layout.setConstraints(rowsLabel, constraints);
        pane.add(rowsLabel);

        // === Rows input ===
        rowsTextField = new JTextField(String.valueOf(rows), 0);
        constraints.gridx = 1;
        layout.setConstraints(rowsTextField, constraints);
        pane.add(rowsTextField);

        // === Columns text ===
        columnsLabel = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 1;
        layout.setConstraints(columnsLabel, constraints);
        pane.add(columnsLabel);

        // === Columns input ===
        columnsTextField = new JTextField(String.valueOf(columns), 0);
        constraints.gridx = 1;
        layout.setConstraints(columnsTextField, constraints);
        pane.add(columnsTextField);

        // === Bombs text ===
        bombsLabel = new JLabel();
        constraints.gridx = 0;
        constraints.gridy = 2;
        layout.setConstraints(bombsLabel, constraints);
        pane.add(bombsLabel);

        // === Bombs input ===
        bombsTextField = new JTextField(String.valueOf(bombsCount), 0);
        constraints.gridx = 1;
        layout.setConstraints(bombsTextField, constraints);
        pane.add(bombsTextField);

        // === Button ===
        okButton = new JButton();
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.weighty = 0.4;
        constraints.insets = new Insets(10, 10, 10, 10);
        layout.setConstraints(okButton, constraints);
        pane.add(okButton);

        okButton.addActionListener(l -> {
            setCommand();
            dialog.dispose();
        });

        Rectangle frameBounds = parentFrame.getBounds();
        Rectangle formBounds = dialog.getBounds();
        dialog.setLocation(frameBounds.x + frameBounds.width / 2 - formBounds.width / 2,
                frameBounds.y + frameBounds.height / 2 - formBounds.height / 2);
    }

    void setComponentsText(String dialogTitle,
                           String rowsString,
                           String columnsString,
                           String bombsString,
                           String buttonString) {
        dialog.setTitle(dialogTitle);
        rowsLabel.setText(rowsString);
        columnsLabel.setText(columnsString);
        bombsLabel.setText(bombsString);
        okButton.setText(buttonString);
    }

    private void setCommand() {
        try {
            rows = Integer.parseInt(rowsTextField.getText());
        } catch (NumberFormatException ignore) {
        }

        try {
            columns = Integer.parseInt(columnsTextField.getText());
        } catch (NumberFormatException ignore) {
        }

        try {
            bombsCount = Integer.parseInt(bombsTextField.getText());
        } catch (NumberFormatException ignore) {
        }

        command = new StartFreeGameCommand(rows, columns, bombsCount);
    }

    StartFreeGameCommand getCommand() {
        dialog.setVisible(true);
        return command;
    }
}
