package com.github.svyaz.minesweeper.gamemodel.commands;

import com.github.svyaz.minesweeper.gamemodel.Game;

public abstract class Command {
    protected Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    /*private final GameCommand command;
    private final int row;
    private final int column;
    private final int bombsCount;
    private String parameter;*/

    /**
     * Для команды с всеми возможными параметрами
     * TODO поменять порядок параметров с сделать перегрузки конструкторов.
     */
    /*public Command(GameCommand command, int row, int column, int bombsCount, String parameter) {
        this.command = command;
        this.row = row;
        this.column = column;
        this.bombsCount = bombsCount;
        this.parameter = parameter;
    }

    */

    /**
     * Для команд без параметров
     *//*
    public Command(GameCommand command) {
        this(command, 0, 0, 0, null);
    }

    public GameCommand getCommand() {
        return command;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getBombsCount() {
        return bombsCount;
    }

    public String getParameter() {
        return parameter;
    }*/

    public abstract void execute();
}
