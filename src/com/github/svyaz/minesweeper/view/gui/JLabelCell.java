package com.github.svyaz.minesweeper.view.gui;

import javax.swing.*;

/**
 * Класс для отображения ячейки игрового поля.
 */
class JLabelCell extends JLabel {
    private final int row;
    private final int column;

    JLabelCell(int row, int column, Icon image) {
        super(image);
        this.row = row;
        this.column = column;
    }

    int getRow() {
        return row;
    }

    int getColumn() {
        return column;
    }
}
