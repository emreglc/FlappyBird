/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.flappybird;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author emre
 */
public class Screen extends JFrame {

    public Screen(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args) {

        Screen ekran = new Screen("Flappy Bird");
        Game oyun = new Game();
        oyun.requestFocus();
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        ekran.setResizable(false);
        ekran.setFocusable(false);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ekran.add(oyun);
        ekran.getContentPane().setPreferredSize(new Dimension(800, 600));
        ekran.pack();
        ekran.setLocationRelativeTo(null);
        ekran.setVisible(true);

    }

}
