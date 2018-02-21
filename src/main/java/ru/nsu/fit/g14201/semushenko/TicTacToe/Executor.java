package ru.nsu.fit.g14201.semushenko.TicTacToe;

import javafx.util.Pair;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.CellType;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.Difficulty;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.GameState;
import ru.nsu.fit.g14201.semushenko.TicTacToe.view.View;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class Executor {
    private Model model;
    private Computer computer;
    private View view;
    private Difficulty difficulty;

    public void execute() {
        computer = new Computer();
        model = new Model();
        try {
            view = new View(model);
        } catch (IOException e) {
        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTask(), 0, 1);
    }

    private class MyTask extends TimerTask {
        public void run() {
            switch (view.getGameState()) {
                case START:
                    startGame();
                    break;
                case RUNNING:
                    try {
                        checkClick();
                    } catch (InterruptedException e) {
                    }
                    break;
            }
            if (view.isRestart()) {
                execute();
                this.cancel();
            }
        }
    }

    private void startGame() {
        switch (difficulty = view.getDifficulty()) {
            case NOTSET:
                return;
            default:
                view.setGameState(GameState.RUNNING);
                view.repaint();
        }
    }

    private void checkClick() throws InterruptedException {
        int xClicked = view.getxClicked();
        int yClicked = view.getyClicked();
        GameState gameState;
        if (xClicked != -1 && yClicked != -1) {
            view.clickRegistered();
            Pair<Integer, Integer> cellClicked = model.whichCellIsClicked(xClicked, yClicked);
            if (cellClicked != null) {
                try {
                    view.updateCellState(cellClicked.getKey(), cellClicked.getValue(), CellType.PLAYER);
                    view.repaint();
                    switch (gameState = model.checkEndOfGame()) {
                        case RUNNING:
                            makeComputerTurn();
                            gameState = model.checkEndOfGame();
                            if (gameState == GameState.RUNNING)
                                break;
                        case LOSS:
                        case VICTORY:
                        case DRAW:
                            TimeUnit.SECONDS.sleep(1);
                            break;
                    }
                    view.setGameState(gameState);
                    view.repaint();
                } catch (IOException e) {
                }
            }
        }
    }

    private void makeComputerTurn() {
        Pair<Integer, Integer> computerTurn = new Pair<Integer, Integer>(0, 0);
        switch (difficulty) {
            case EASY:
                computerTurn = computer.makeRandomTurn(model.getCellStates());
                break;
            case DIFFICULT:
                computerTurn = computer.makeSmartTurn(model.getCellStates());
                break;
            case NOTSET:
                return;
        }
        try {
            model.updateCellState(computerTurn.getKey(), computerTurn.getValue(), CellType.COMPUTER);
            view.updateCellState(computerTurn.getKey(), computerTurn.getValue(), CellType.COMPUTER);
            view.repaint();
        } catch (IOException e) {
        }
    }
}

