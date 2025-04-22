package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gamePanel;

	public CollisionChecker(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public void checkTileCollision(Entity entity) {
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

	public int checkObject(Entity entity, boolean isPlayer) {
		int index = 999;

		for (int i = 0; i < gamePanel.objectManager.object.length; i++) {
			if (gamePanel.objectManager.object[i] != null) {
				entity.solidArea.x += entity.worldX;
				entity.solidArea.y += entity.worldY;
				gamePanel.objectManager.object[i].solidArea.x += gamePanel.objectManager.object[i].worldX;
				gamePanel.objectManager.object[i].solidArea.y += gamePanel.objectManager.object[i].worldY;
				
				switch (entity.direction) {
				case "UP":
					entity.solidArea.y -= entity.speed;
					break;
				case "DOWN":
					entity.solidArea.y += entity.speed;
					break;
				case "LEFT":
					entity.solidArea.x -= entity.speed;
					break;
				case "RIGTH":
					entity.solidArea.x += entity.speed;
					break;
				}
				
				if (entity.solidArea.intersects(gamePanel.objectManager.object[i].solidArea)) {
					if (gamePanel.objectManager.object[i].collision == true) {
						entity.collisionOn = true;
					}
					
					if (isPlayer == true) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gamePanel.objectManager.object[i].solidArea.x = gamePanel.objectManager.object[i].solidAreaDefaultX;
				gamePanel.objectManager.object[i].solidArea.y = gamePanel.objectManager.object[i].solidAreaDefaultY;
			}
			
		}

		return index;
	}
}
