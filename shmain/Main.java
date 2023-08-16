package shmain;

import javax.swing.JFrame;

public class Main {

    public static void main (String[] args) {

        JFrame window  = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allows window to properly close when hitting 'X'

        window.setResizable(true); // so we cannot resize window

        window.setTitle("Desert Escape: The Bearded Odyssey"); // title of the game

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // window will be sized to fit the preferred size/layouts of its subcomponents

        window.setLocationRelativeTo(null); // window will be at center of screen
        window.setVisible(true); // to make window visible

        gamePanel.startGameThread();
    }
}