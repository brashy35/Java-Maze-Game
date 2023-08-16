package shmain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable { // runnable used to run 'Thread'
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile (default size)
    final int scale = 3; // to scale up (to compensate for larger monitors)

    public final int tileSize = originalTileSize * scale; // 16 * 3, so 48x48 tile (this is the actual tile size)
    public final int maxScreenCol = 16; // vertical tiles
    public final int maxScreenRow = 12; // horizontal tiles
    public final int screenWidth = tileSize * maxScreenCol; // 48 * 16, so 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 48 * 12, so 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60; // FPS

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // 'game clock'. something you can start/stop. once it's started, it keeps the program running (until you stop it).
    public CollisionCheck cCheck = new CollisionCheck(this);
    public Player player = new Player(this,keyH); // creates new player

    public GamePanel() { 

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // sets the size of this class (JPanel)
        this.setBackground(Color.lightGray); // color of background
        this.setDoubleBuffered(true); // when true, drawing from this component is done on offscreen painting buffer
        this.addKeyListener(keyH); // to recognize key input
        this.setFocusable(true); // GamePanel can be "focused" to receive key input
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // GAME LOOP - core of the game

        // for 'system time'. keeps track of nanoseconds
        double drawInterval = 1000000000 / FPS; // 1 bil nanoseconds in 1 sec
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            // must have the update-repaint loop to animate objects
            if (delta >= 1) {
                update();
                repaint(); // this is how you call paintComponent for some reason
                delta --;
                drawCount ++;
            }

            // prints fps to terminal
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() { // for key inputs

        player.update();
    }

    public void paintComponent(Graphics g) { // Graphics is a class with many methods to draw objects on the screen

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // extends graphics class. provides more control over geometry, color management, text layout, etc.
        
        tileM.draw(g2); // draw tile first...

        player.draw(g2);    // then player. that's why it is ordered like this.

        g2.dispose(); // good to use. releases system resources that it is using.
    }
}
