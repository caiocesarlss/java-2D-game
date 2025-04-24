package main;

import java.awt.Graphics2D;

import object.GameObject;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class ObjectManager {
	GamePanel gamePanel;
	public GameObject[] object;
	
	public ObjectManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		object = new GameObject[10];
	}

	public void setObject() {
		gamePanel.objectManager.object[0] = new OBJ_Key();
		gamePanel.objectManager.object[0].worldX = 23 * gamePanel.tileSize;
		gamePanel.objectManager.object[0].worldY = 7 * gamePanel.tileSize;
		
		gamePanel.objectManager.object[1] = new OBJ_Key();
		gamePanel.objectManager.object[1].worldX = 23 * gamePanel.tileSize;
		gamePanel.objectManager.object[1].worldY = 40 * gamePanel.tileSize;
		
		gamePanel.objectManager.object[2] = new OBJ_Key();
		gamePanel.objectManager.object[2].worldX = 38 * gamePanel.tileSize;
		gamePanel.objectManager.object[2].worldY = 8 * gamePanel.tileSize;
		
		gamePanel.objectManager.object[3] = new OBJ_Door();
		gamePanel.objectManager.object[3].worldX = 10 * gamePanel.tileSize;
		gamePanel.objectManager.object[3].worldY = 11 * gamePanel.tileSize;
		
		gamePanel.objectManager.object[4] = new OBJ_Door();
		gamePanel.objectManager.object[4].worldX = 8 * gamePanel.tileSize;
		gamePanel.objectManager.object[4].worldY = 28 * gamePanel.tileSize;
		
		gamePanel.objectManager.object[5] = new OBJ_Door();
		gamePanel.objectManager.object[5].worldX = 12 * gamePanel.tileSize;
		gamePanel.objectManager.object[5].worldY = 22 * gamePanel.tileSize;
		
		gamePanel.objectManager.object[6] = new OBJ_Chest();
		gamePanel.objectManager.object[6].worldX = 10 * gamePanel.tileSize;
		gamePanel.objectManager.object[6].worldY = 7 * gamePanel.tileSize;
		
		gamePanel.objectManager.object[7] = new OBJ_Boots();
		gamePanel.objectManager.object[7].worldX = 37 * gamePanel.tileSize;
		gamePanel.objectManager.object[7].worldY = 42 * gamePanel.tileSize;
	}
	
	public void draw(Graphics2D g2D, int index) {
		int screenX = object[index].worldX - gamePanel.player.worldX + gamePanel.player.screenX;
		int screenY = object[index].worldY - gamePanel.player.worldY + gamePanel.player.screenY;
		
		if (isObjectWithinBounds(object[index].worldX, object[index].worldY, gamePanel)) {
			g2D.drawImage(object[index].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
		}
	}
	
	public boolean isObjectWithinBounds(int worldX, int worldY, GamePanel gamePanel) {
		if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
				worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
				worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
				worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
				
			return true;
		}
		
		return false;
	}

}
