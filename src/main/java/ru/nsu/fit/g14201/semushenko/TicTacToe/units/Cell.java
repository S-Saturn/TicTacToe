package ru.nsu.fit.g14201.semushenko.TicTacToe.units;

import ru.nsu.fit.g14201.semushenko.TicTacToe.GameInfo;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.CellType;

import java.awt.*;

/**
 * Created by Semushenko Elena on 19.02.2018.
 */
public class Cell implements GameInfo {
    private CellType cellType;

    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        cellType = CellType.FREE;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, cellSize, cellSize);
    }
}
