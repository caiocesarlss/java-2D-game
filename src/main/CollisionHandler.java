package main;

import entity.Entity;

public class CollisionHandler {
	GamePanel gamePanel;
	
	public CollisionHandler(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gamePanel.tileSize;
		int entityRightCol = entityRightWorldX/gamePanel.tileSize;
		int entityTopRow = entityTopWorldY/gamePanel.tileSize;
		int entityBottomRow = entityBottomWorldY/gamePanel.tileSize;
		
		int tileNum1 = 0;
		int tileNum2 = 0;
		
		switch (entity.direction) {
		case "UP":
			entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
			break;
		case "DOWN":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
			break;
		case "LEFT":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
			break;
		case "RIGHT":
			entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
			break;
		default:
			break;
		}
		
		if (gamePanel.tileManager.tile[tileNum1].collision == true || 
			gamePanel.tileManager.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
		}
	}
}
