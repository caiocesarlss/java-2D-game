package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gamePanel;
	public Tile[] tile;
	public int[][] mapTileNum;

	public TileManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		tile = new Tile[10];
		mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
		loadTileImage();
		loadMap("/maps/world01.txt");
	}

	public void setupTile(int index, String imagePath, boolean collision) throws IOException {
		tile[index] = new Tile();
		tile[index].image = ImageIO.read(getClass().getResourceAsStream(imagePath));
		tile[index].collision = collision;
	}
	
	public void loadTileImage() {
		try {
			setupTile(0, "/tiles/grass.png", false);
			setupTile(1, "/tiles/wall.png", true);
			setupTile(2, "/tiles/water.png", true);
			setupTile(3, "/tiles/earth.png", false);
			setupTile(4, "/tiles/tree.png", true);
			setupTile(5, "/tiles/sand.png", false);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
				String line = br.readLine();

				while (col < gamePanel.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}

				if (col == gamePanel.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D g2d) {
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {
			int tileNum = mapTileNum[worldCol][worldRow];
			int worldX = worldCol * gamePanel.tileSize;
			int worldY = worldRow * gamePanel.tileSize;
			int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
			int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

			if (isTileOnScreen(worldX, worldY)) {
				g2d.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
			}

			worldCol++;

			if (worldCol == gamePanel.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			} 
		}
	}

	public boolean isTileOnScreen(int worldX, int worldY) {
		return worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
			   worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
			   worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
			   worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY;
	}

}
