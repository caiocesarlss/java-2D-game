package object;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public abstract class GameObject {
	public BufferedImage image;
	public GamePanel gamePanel = new GamePanel();
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, gamePanel.tileSize, gamePanel.tileSize);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;

	public abstract void loadObjectImage();
}
