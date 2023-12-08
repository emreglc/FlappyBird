/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.flappybird;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author emre
 */

class Pipes {

    Random random = new Random();
    private int x = 800;
    private int y = 0;
    private int pipeHeight;

    public Pipes(int x, int y) {
        pipeHeight = 150 + random.nextInt(200);
        this.x = x;
        this.y = y;
    }

    public int getPipeHeight() {
        return pipeHeight;
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

public class Game extends JPanel implements ActionListener, KeyListener {

    BufferedImage bird;
    BufferedImage background;
    ArrayList<Pipes> pipes = new ArrayList<>();

    Random random = new Random();
    Timer timer = new Timer(5, this);

    private int birdX = 400;
    private int birdY = 250;
    private int pipeV = 2;
    private int pipeSpace = 200;
    private int pipeWidth = 100;
    private int birdV = 6;
    private int gameOver = 0;

    public Game() {

        pipes.add(new Pipes(800, 0));
        pipes.add(new Pipes(1000, 0));
        pipes.add(new Pipes(1200, 0));
        pipes.add(new Pipes(1400, 0));

        setBackground(Color.GRAY);
        try {
            bird = ImageIO.read(new File("img/bird.png"));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(bird, birdX, birdY, 50, 40, this);
        for (Pipes pipe : pipes) {
            if (pipe.getX() <= 0) {
                pipes.remove(pipe);
                pipes.add(new Pipes(800, 0));
            }
            g.setColor(Color.green);
            g.fillRect(pipe.getX(), 0, pipeWidth, pipe.getPipeHeight());
            g.fillRect(pipe.getX(), pipe.getPipeHeight() + pipeSpace, pipeWidth,
                    600 - pipe.getPipeHeight() - pipeSpace);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameOver == 1) {
            timer.stop();
        }

        if (birdY < 0) {
            birdY = 0;
        }
        if (birdV != 6) {
            birdV++;
        }
        if (birdY <= 560) {
            birdY += birdV;
        } else {
            gameOver = 1;
        }

        for (Pipes pipe : pipes) {

            if ((birdX + 45 >= pipe.getX() && birdY - 5 <= pipe.getPipeHeight())
                    || (birdY + 45 >= pipeSpace + pipe.getPipeHeight() && birdX + 55 >= pipe.getX())) {
                if (birdX <= pipe.getX() + pipeWidth) {
                    gameOver = 1;
                }
            }

            pipe.setX(pipe.getX() - pipeV);

        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int c = e.getKeyCode();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        if (c == KeyEvent.VK_SPACE) {
            if (gameOver == 0) {
                timer.start();
            }
            birdV = -12;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
