package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Entity {
	public int worldX, worldY;
	public int speed;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	
	public abstract void update();
    public abstract void draw(Graphics2D g2d);
    
    public BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(getClass().getResourceAsStream(path));
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
