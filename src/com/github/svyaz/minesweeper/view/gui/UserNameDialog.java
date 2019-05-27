package com.github.svyaz.minesweeper.view.gui;

import javax.swing.*;
import java.awt.*;

class UserNameDialog {
    private JFrame parentFrame;
    private JDialog dialog;
    private JLabel userLabel;
    private JTextField userNameTextField;
    private JButton okButton;
    private String userName;

    UserNameDialog(JFrame parentFrame, String defaultUserName) {
        this.parentFrame = parentFrame;
        this.userName = defaultUserName;
        createDialog();
    }

    private void createDialog() {
        // === Modal frame ===
        dialog = new JDialog(parentFrame, true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setSize(260, 140);
        dialog.setResizable(false);

        Container pane = dialog.getContentPane();
        GridBagLayout layout = new GridBagLayout();
        pane.setLayout(layout);
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        GridBagConstraints constraints = new GridBagConstraints();

        // === Label ===
        userLabel = new JLabel();
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
        layout.setConstraints(userLabel, constraints);
        pane.add(userLabel);

        // === Text ===
        userNameTextField = new JTextField(0);
        constraints.gridy = 1;
        constraints.weighty = 0.3;
        layout.setConstraints(userNameTextField, constraints);
        pane.add(userNameTextField);

        // === Button ===
        okButton = new JButton();
        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.fill = GridBagConstraints.NONE;
        constraints.gridy = 2;
        constraints.weighty = 0.4;
        constraints.insets = new Insets(10, 10, 10, 10);
        layout.setConstraints(okButton, constraints);
        pane.add(okButton);

        okButton.addActionListener(l -> {
            setUserName();
            dialog.dispose();
        });

        Rectangle frameBounds = parentFrame.getBounds();
        Rectangle formBounds = dialog.getBounds();
        dialog.setLocation(frameBounds.x + frameBounds.width / 2 - formBounds.width / 2,
                frameBounds.y + frameBounds.height / 2 - formBounds.height / 2);
    }

    void setComponentsText(String dialogTitle,
                           String message,
                           String buttonString) {
        dialog.setTitle(dialogTitle);
        userLabel.setText(message);
        userNameTextField.setText(userName);
        okButton.setText(buttonString);
    }

    private void setUserName() {
        String text = userNameTextField.getText();
        if (text != null && !text.isEmpty()) {
            userName = text;
        }
    }

    String getUserName() {
        dialog.setVisible(true);
        return userName;
    }
}
