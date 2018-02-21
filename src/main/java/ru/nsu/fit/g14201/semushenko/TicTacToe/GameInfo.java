package ru.nsu.fit.g14201.semushenko.TicTacToe;

/**
 * Created by Semushenko Elena on 19.02.2018.
 */
public interface GameInfo {
    int cellSize = 50;
    String frameTitle = "Elena's TicTacToe";
    int frameWidth = 200;
    int frameHeight = 200;
    String freeCellImg = "/FreeCell.jpg";
    String playerCellImg = "/PlayerCell.jpg";
    String computerCellImg = "/ComputerCell.jpg";
    String welcomeImg = "/Welcome.jpg";
    String lossImg = "/Loss.jpg";
    String victoryImg = "/Victory.jpg";
    String drawImg = "/Draw.jpg";
    int fieldPositionX = (frameWidth - 3 - cellSize * 3) / 2;
    int fieldPositionY = (frameHeight - 35 - cellSize * 3) / 2;
    int imgPositionX = 35;
    int imgPositionY = 5;
    int restartPositionX = 45;
    int restartPositionY = 50;
    int restartWidth = 100;
    int restartHeight = 40;
    int easyPositionX = 45;
    int easyPositionY = 50;
    int easyWidth = 100;
    int easyHeight = 40;
    int difficultPositionX = 45;
    int difficultPositionY = 100;
    int difficultWidth = 100;
    int difficultHeight = 40;
}
