package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity { // stores variables to be used in player, monster, and npc classes
    
    public int worldX, worldY;
    public int speed;
    
    // describes an image with an accessible buffer of image data (storing image files)
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
