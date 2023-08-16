package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import shmain.GamePanel;
import java.awt.Graphics2D;

public class TileManager {
    
    GamePanel gp;
    public Tile [] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/maze_map.txt");
    }

    public void getTileImage() { // loading in each tile image

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water_tile.png"));
            tile[0].collision = true;


            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand_tile.png"));
            tile[1].collision = false;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone_wall_tile.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/scary_face_tile.png"));
            tile[3].collision = false;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/cactus_tile.png"));
            tile[4].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reads maps written in notepad 
    public void loadMap(String filePath) { // reads in the map

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine(); // reads line of text from note file

                while (col < gp.maxWorldCol) {

                    String numbers [] = line.split(" "); // splits the string around matches of the given number. will split the line and get tile numbers one by one

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col ++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row ++;
                }
            }
            br.close();

        } catch (Exception e) {
        }
    }

    public void draw (Graphics2D g2) {

        // math to draw each tile in

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow]; // extract a tile number which is stored in mapTileNum[col][row]

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // if-test allows for only visible tiles to be displayed to screen (for efficiency purposes)
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)

                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            
            worldCol ++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow ++;
            }
        }
    }
}
