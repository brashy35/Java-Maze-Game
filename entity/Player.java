package entity;

import shmain.GamePanel;
import shmain.KeyHandler;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
// import java.awt.Color;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;

    // these indicate where we draw the player on the screen. (helps with keeping sprite at screen-center)
    public final int screenX; 
    public final int screenY;

    public Player (GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        // finds center of screen
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        // (same as putting in parameters above ^...)
        // the following marks the solid area of the player
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        // starting coordinates for player
        worldX = gp.tileSize * 49;
        worldY = gp.tileSize * 45;
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage() {

        try {

            // writing all image locations to load them in
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/main_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/main_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/main_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/main_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/main_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/main_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/main_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/main_right_2.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // for key inputs
    public void update() {  // this update method gets called 60x per sec.

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) { // to disable constant running

            if (keyH.upPressed == true)
                direction = "up";

            else if (keyH.downPressed == true) 
                direction = "down"; 
            
            else if (keyH.leftPressed == true) 
                direction = "left";
            
            else if (keyH.rightPressed == true) 
                direction = "right";
                
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cCheck.checkTile(this);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {

                switch(direction) {
                    case "up": worldY -= speed; break;

                    case "down": worldY += speed; break;

                    case "left": worldX -= speed; break;

                    case "right": worldX += speed; break;
                }
            }

            spriteCounter ++;
            if (spriteCounter > 8) { // after how many frames does the sprite change movement
                if (spriteNum == 1)
                    spriteNum = 2;

                else if (spriteNum == 2)
                    spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {

        // g2.setColor(Color.pink); // color of object
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize); // draws the basic rectangular

        BufferedImage image = null;

        // must have if statements to give the illusion of walking
        switch(direction) {
            case "up":
                if (spriteNum == 1)
                    image = up1;
                if (spriteNum == 2)
                    image = up2;
                break;
            case "down":
                if (spriteNum == 1)
                    image = down1;
                if (spriteNum == 2)
                    image = down2;                
                break;
            case "left":
                if (spriteNum == 1)
                    image = left1;
                if (spriteNum == 2)
                    image = left2;                
                break;
            case "right":
                if (spriteNum == 1)
                    image = right1;
                if (spriteNum == 2)
                    image = right2;                
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
