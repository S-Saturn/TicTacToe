package ru.nsu.fit.g14201.semushenko.TicTacToe.view;

import ru.nsu.fit.g14201.semushenko.TicTacToe.GameInfo;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.CellType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Semushenko Elena on 19.02.2018.
 */
public class CellView implements GameInfo {
    private Image image;
    private int imgWidth;
    private int imgHeight;

    public CellView() throws IOException {
        image = ImageIO.read(getClass().getResource(freeCellImg));
        imgWidth = image.getWidth(null);
        imgHeight = image.getHeight(null);
    }

    public void draw(Graphics2D graphics2D, int x, int y) {
        graphics2D.drawImage(image, x, y, imgWidth, imgHeight, null);
    }

    public void updateState(CellType cellType) throws IOException {
        switch (cellType) {
            case PLAYER:
                image = ImageIO.read(getClass().getResource(playerCellImg));
                break;
            case COMPUTER:
                image = ImageIO.read(getClass().getResource(computerCellImg));
                break;
        }
    }
}
