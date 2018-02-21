package ru.nsu.fit.g14201.semushenko.TicTacToe.units;

/**
 * Created by Semushenko Elena on 21.02.2018.
 */
public class Move {
    private int x;
    private int y;
    private int score;

    public Move() {
    }

    public Move(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
