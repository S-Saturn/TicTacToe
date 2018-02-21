package ru.nsu.fit.g14201.semushenko.TicTacToe;

import javafx.util.Pair;
import ru.nsu.fit.g14201.semushenko.TicTacToe.units.Move;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.CellType;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.GameState;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Semushenko Elena on 19.02.2018.
 */
public class Computer {
    public Pair<Integer, Integer> makeRandomTurn(CellType[][] cellsTypes) {
        ArrayList<Pair<Integer, Integer>> freeCells = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (cellsTypes[i][j] == CellType.FREE) {
                    freeCells.add(new Pair<Integer, Integer>(i, j));
                }
            }
        }
        Random random = new Random();
        int n = random.nextInt(freeCells.size());
        return freeCells.get(n);
    }

    public Pair<Integer, Integer> makeSmartTurn(CellType[][] cellsTypes) {
        Move bestMove = minimax(cellsTypes, CellType.COMPUTER);
        return new Pair<Integer, Integer>(bestMove.getX(), bestMove.getY());
    }

    private Move minimax(CellType[][] cellsTypes, CellType cellType) {
        ArrayList<Pair<Integer, Integer>> freeCells = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (cellsTypes[i][j] == CellType.FREE) {
                    freeCells.add(new Pair<Integer, Integer>(i, j));
                }
            }
        }
        GameJudge gameJudge = new GameJudge();
        if (gameJudge.checkEndOfGame(cellsTypes) == GameState.VICTORY) {
            return new Move(-10);
        } else if (gameJudge.checkEndOfGame(cellsTypes) == GameState.LOSS) {
            return new Move(10);
        } else if (gameJudge.checkEndOfGame(cellsTypes) == GameState.DRAW) {
            return new Move(0);
        }

        ArrayList<Move> moves = new ArrayList<Move>();

        for (Pair<Integer, Integer> cell : freeCells) {
            Move move = new Move();
            move.setX(cell.getKey());
            move.setY(cell.getValue());
            cellsTypes[cell.getKey()][cell.getValue()] = cellType;

            if (cellType == CellType.COMPUTER) {
                move.setScore(minimax(cellsTypes, CellType.PLAYER).getScore());
            } else {
                move.setScore(minimax(cellsTypes, CellType.COMPUTER).getScore());
            }

            cellsTypes[move.getX()][move.getY()] = CellType.FREE;
            moves.add(move);
        }

        int bestMove = 0;
        if (cellType == CellType.COMPUTER) {
            int bestScore = -10000;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).getScore() > bestScore) {
                    bestScore = moves.get(i).getScore();
                    bestMove = i;
                }
            }
        } else {
            int bestScore = 10000;
            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).getScore() < bestScore) {
                    bestScore = moves.get(i).getScore();
                    bestMove = i;
                }
            }
        }
        return moves.get(bestMove);
    }
}

