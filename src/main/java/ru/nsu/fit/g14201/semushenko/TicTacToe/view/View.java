package ru.nsu.fit.g14201.semushenko.TicTacToe.view;

import ru.nsu.fit.g14201.semushenko.TicTacToe.GameInfo;
import ru.nsu.fit.g14201.semushenko.TicTacToe.Model;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.CellType;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.Difficulty;
import ru.nsu.fit.g14201.semushenko.TicTacToe.util.GameState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import static ru.nsu.fit.g14201.semushenko.TicTacToe.util.GameState.*;

public class View extends JPanel implements GameInfo {
    private Model model;
    private JFrame frame;
    private int xClicked;
    private int yClicked;

    private boolean restart;
    private Difficulty difficulty;
    private GameState gameState;
    private CellView[][] cellViews;

    public View(Model model) throws IOException {
        clickRegistered();
        this.model = model;
        difficulty = Difficulty.NOTSET;
        restart = false;
        gameState = START;
        cellViews = new CellView[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                cellViews[i][j] = new CellView();
            }
        }
        initFrame();
    }

    private void initFrame() {
        frame = new JFrame(frameTitle);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xClicked = e.getX();
                yClicked = e.getY();
                if (xClicked > fieldPositionX && yClicked > fieldPositionY) {
                    xClicked -= fieldPositionX;
                    yClicked -= fieldPositionY;
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                xClicked = e.getX();
                yClicked = e.getY();
                if (xClicked > fieldPositionX && yClicked > fieldPositionY) {
                    xClicked -= fieldPositionX;
                    yClicked -= fieldPositionY;
                }
            }
        });

        frame.add(this);
        frame.setLocation(150, 150);
        frame.setBackground(Color.white);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        removeAll();

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.white);
        g2D.fillRect(0, 0, frameWidth, frameHeight);
        try {
            switch (gameState) {
                case START:
                    startOfGame(g2D);
                    break;
                case RUNNING:
                    drawObjects(g2D);
                    break;
                case VICTORY:
                    victory(g2D);
                    break;
                case LOSS:
                    loss(g2D);
                    break;
                case DRAW:
                    draw(g2D);
                    break;
            }
        } catch (IOException e) {
        }

    }

    private void drawObjects(Graphics2D g2D) {
        int[][] cellsX = model.getCellsX();
        int[][] cellsY = model.getCellsY();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                cellViews[i][j].draw(g2D, fieldPositionX + cellsX[i][j], fieldPositionY + cellsY[i][j]);
            }
        }
    }

    private void startOfGame(Graphics2D g2D) throws IOException {
        Image welcome = ImageIO.read(getClass().getResource(welcomeImg));
        g2D.drawImage(welcome, imgPositionX, imgPositionY, welcome.getWidth(null),
                welcome.getHeight(null), this);
        easyButton();
        difficultButton();
    }

    private void loss(Graphics2D g2D) throws IOException {
        Image loss = ImageIO.read(getClass().getResource(lossImg));
        g2D.drawImage(loss, imgPositionX, imgPositionY, loss.getWidth(null),
                loss.getHeight(null), this);
        restartButton();
    }

    private void victory(Graphics2D g2D) throws IOException {
        Image victory = ImageIO.read(getClass().getResource(victoryImg));
        g2D.drawImage(victory, imgPositionX, imgPositionY, victory.getWidth(null),
                victory.getHeight(null), this);
        restartButton();
    }

    private void draw(Graphics2D g2D) throws IOException {
        Image draw = ImageIO.read(getClass().getResource(drawImg));
        g2D.drawImage(draw, imgPositionX, imgPositionY, draw.getWidth(null),
                draw.getHeight(null), this);
        restartButton();
    }

    public void updateCellState(int i, int j, CellType cellType) throws IOException {
        cellViews[i][j].updateState(cellType);
    }

    private void easyButton() {
        JButton easyButton = new JButton();
        easyButton.setBounds(easyPositionX, easyPositionY, easyWidth, easyHeight);
        easyButton.setText("Easy");
        easyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficulty = Difficulty.EASY;
            }
        });
        add(easyButton);
    }

    private void difficultButton() {
        JButton difficultButton = new JButton();
        difficultButton.setBounds(difficultPositionX, difficultPositionY, difficultWidth, difficultHeight);
        difficultButton.setText("Difficult");
        difficultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficulty = Difficulty.DIFFICULT;
            }
        });
        add(difficultButton);
    }

    private void restartButton() {
        restart = false;
        JButton restartButton = new JButton();
        restartButton.setBounds(restartPositionX, restartPositionY, restartWidth, restartHeight);
        restartButton.setText("Restart");
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                restart = true;
            }
        });
        add(restartButton);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getxClicked() {
        return xClicked;
    }

    public int getyClicked() {
        return yClicked;
    }

    public void clickRegistered() {
        xClicked = -1;
        yClicked = -1;
    }

    public boolean isRestart() {
        return restart;
    }
}
