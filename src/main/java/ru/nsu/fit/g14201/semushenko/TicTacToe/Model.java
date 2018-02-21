package ru.nsu.fit.g14201.semushenko.TicTacToe;

import javafx.util.Pair;
import ru.nsu.fit.g14201.semushenko.TicTacToe.units.Cell;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.CellType;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.GameState;

import static ru.nsu.fit.g14201.semushenko.TicTacToe.util.CellType.*;

/**
 * Created by Semushenko Elena on 19.02.2018.
 */
public class Model implements GameInfo {
    private Cell[][] cells;
    private CellType[][] cellTypes;

    public Model() {
        cells = new Cell[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                cells[i][j] = new Cell(i * cellSize, j * cellSize);
            }
        }
        cellTypes = getCellStates();
    }

    public Pair<Integer, Integer> whichCellIsClicked(int xClicked, int yClicked) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (cells[i][j].getRectangle().contains(xClicked, yClicked) &&
                        cells[i][j].getCellType() == CellType.FREE) {
                    updateCellState(i, j, PLAYER);
                    return new Pair<Integer, Integer>(i, j);
                }
            }
        }
        return null;
    }

    public void updateCellState(int i, int j, CellType cellType) {
        cells[i][j].setCellType(cellType);
        cellTypes = getCellStates();
    }

    public int[][] getCellsX() {
        int[][] cellsX = new int[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                cellsX[i][j] = cells[i][j].getX();
            }
        }
        return cellsX;
    }

    public int[][] getCellsY() {
        int[][] cellsY = new int[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                cellsY[i][j] = cells[i][j].getY();
            }
        }
        return cellsY;
    }

    public CellType[][] getCellStates() {
        CellType[][] cellTypes = new CellType[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                cellTypes[i][j] = cells[i][j].getCellType();
            }
        }
        return cellTypes;
    }

    public GameState checkEndOfGame() {
        GameJudge gameJudge = new GameJudge();
        return gameJudge.checkEndOfGame(cellTypes);
    }
}
