package ru.nsu.fit.g14201.semushenko.TicTacToe;

import ru.nsu.fit.g14201.semushenko.TicTacToe.util.CellType;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.GameState;

import static ru.nsu.fit.g14201.semushenko.TicTacToe.util.CellType.COMPUTER;
import static ru.nsu.fit.g14201.semushenko.TicTacToe.util.CellType.FREE;
import static ru.nsu.fit.g14201.semushenko.TicTacToe.util.CellType.PLAYER;

/**
 * Created by Semushenko Elena on 21.02.2018.
 */
public  class GameJudge {
    private CellType[][] cellTypes;

    public GameState checkEndOfGame(CellType[][] cellTypes) {
        this.cellTypes = cellTypes;
        if (checkForWin(PLAYER))
            return GameState.VICTORY;
        else if (checkForWin(COMPUTER))
            return GameState.LOSS;
        else if (checkAllCellsTaken())
            return GameState.DRAW;
        else
            return GameState.RUNNING;
    }

    private boolean checkAllCellsTaken() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (cellTypes[i][j] == FREE)
                    return false;
            }
        }
        return true;
    }

    private boolean checkForWin(CellType cellType) {
        boolean victory = false;
        for (int i = 0; i < 3; ++i) {
            victory = victory || checkHorizontalMatch(0, i, 0, cellType);
            victory = victory || checkVerticalMatch(i, 0, 0, cellType);
        }
        victory = victory || checkDiagonalMatch(1, 1, 0, cellType);
        victory = victory || checkSymmetricDiagonalMatch(1, 1, 0, cellType);

        return victory;
    }

    private boolean checkHorizontalMatch(int i, int j, int counter, CellType requestedType) {
        if (counter >= 3)
            return true;
        if (cellTypes[i][j] == requestedType) {
            counter++;
            return checkHorizontalMatch(walkAroundField(i), j, counter, requestedType);
        } else
            return false;
    }

    private boolean checkVerticalMatch(int i, int j, int counter, CellType requestedType) {
        if (counter >= 3)
            return true;
        if (cellTypes[i][j] == requestedType) {
            counter++;
            return checkVerticalMatch(i, walkAroundField(j), counter, requestedType);
        } else
            return false;
    }

    private boolean checkDiagonalMatch(int i, int j, int counter, CellType requestedType) {
        if (counter >= 3)
            return true;
        if (cellTypes[i][j] == requestedType) {
            counter++;
            return checkDiagonalMatch(walkAroundField(i), walkAroundField(j), counter, requestedType);
        } else
            return false;
    }

    private boolean checkSymmetricDiagonalMatch(int i, int j, int counter, CellType requestedType) {
        if (counter >= 3)
            return true;
        if (cellTypes[i][j] == requestedType) {
            counter++;
            return checkSymmetricDiagonalMatch(walkAroundField(i), walkAroundFieldBackwards(j), counter, requestedType);
        } else
            return false;
    }


    private int walkAroundField(int i) {
        i++;
        i = i % 3;
        return i;
    }

    private int walkAroundFieldBackwards(int i) {
        i += 2;
        i = i % 3;
        return Math.abs(i);
    }
}
