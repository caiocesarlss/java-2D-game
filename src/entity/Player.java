package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gamePanel;
	KeyHandler keyHandler;
	public final int screenX;
	public final int screenY;
	public int keyCount = 0;
	int maxSpriteCount = 12;
	int standCounter = 0;

	public Player(GamePanel gamePanel, KeyHandler keyHandler) {
		this.gamePanel = gamePanel;
		this.keyHandler = keyHandler;
		screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
		screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);
		solidArea = new Rectangle(8, 16, gamePanel.tileSize - 16, gamePanel.tileSize - 16);
		solidAreaDefaultX = solidArea.x; 
		solidAreaDefaultY = solidArea.y;
		setDefaultValues();
		loadPlayerImage();
	}

	public void setDefaultValues() {
		worldX = gamePanel.tileSize * 23;
		worldY = gamePanel.tileSize * 21;
		speed = 4;
		direction = "DOWN";
	}

	public void loadPlayerImage() {
		up1 = loadImage("/player/boy_up_1.png");
	    up2 = loadImage("/player/boy_up_2.png");
	    down1 = loadImage("/player/boy_down_1.png");
	    down2 = loadImage("/player/boy_down_2.png");
	    left1 = loadImage("/player/boy_left_1.png");
	    left2 = loadImage("/player/boy_left_2.png");
	    right1 = loadImage("/player/boy_right_1.png");
	    right2 = loadImage("/player/boy_right_2.png");
	}

	public boolean isAnyKeyPressed() {
		return keyHandler.upPressed || keyHandler.downPressed ||
				keyHandler.leftPressed || keyHandler.rightPressed;
	}

	private void updateDirection() {
		if (keyHandler.upPressed) {
			direction = "UP";
		} else if (keyHandler.downPressed) {
			direction = "DOWN";
		} else if (keyHandler.leftPressed) {
			direction = "LEFT";
		} else if (keyHandler.rightPressed) {
			direction = "RIGHT";
		}
	}

	private void handleCollision() {
		collisionOn = false;
		gamePanel.collisionChecker.checkTileCollision(this);

		int objectIndex = gamePanel.collisionChecker.checkObjectCollision(this, true);
		collectObject(objectIndex);
	}

	private void moveIfNoCollision() {
		if (!collisionOn) {
			switch (direction) {
			case "UP":
				worldY -= speed;
				break;
			case "DOWN":
				worldY += speed;
				break;
			case "LEFT":
				worldX -= speed;
				break;
			case "RIGHT":
				worldX += speed;
				break;
			}
		}
	}

	private void updateAnimation() {
		spriteCounter++;

		if (spriteCounter > maxSpriteCount) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else {
				spriteNum = 1;
			}

			spriteCounter = 0;
		}
	}

	private void handleStandingAnimation() {
		standCounter++;

		if (standCounter == 15) {
			spriteNum = 1;
			standCounter = 0;
		}
	}

	public void collectObject(int index) {
		if (index != 999) {
			String objectName = gamePanel.objectManager.object[index].name;

			switch (objectName) {
			case "Key":
				gamePanel.playSoundEffect(1);
				gamePanel.objectManager.object[index] = null;
				gamePanel.ui.showMessage("You got a key!");
				keyCount++;
				break;
			case "Door":
				if (keyCount > 0) {
					gamePanel.playSoundEffect(3);
					gamePanel.objectManager.object[index] = null;
					gamePanel.ui.showMessage("You opened the door!");
					keyCount--;

				} else {
					gamePanel.ui.showMessage("You need a key!");
				}
				break;
			case "Boots":
				gamePanel.playSoundEffect(2);
				gamePanel.objectManager.object[index] = null;
				gamePanel.ui.showMessage("Speed up!");
				speed += 2;
				maxSpriteCount -= 4;
				break;
			case "Chest":
				gamePanel.ui.gameFinished = true;
				gamePanel.stopMusic();
				gamePanel.playSoundEffect(4);
				break;
			}

		}
	}

	@Override
	public void update() {
		if (isAnyKeyPressed()) {
			updateDirection();
			handleCollision();
			moveIfNoCollision();
			updateAnimation();
		} else {
			handleStandingAnimation();
		}
	}

	public BufferedImage getCurrentSprite() {
		BufferedImage image = null;

		switch (direction) {
		case "UP":
			if (spriteNum == 1) {
				image = up1;
			} else {
				image = up2;
			}
			break;
		case "DOWN":
			if (spriteNum == 1) {
				image = down1;
			} else {
				image = down2;
			}
			break;
		case "LEFT":
			if (spriteNum == 1) {
				image = left1;
			} else {
				image = left2;
			}
			break;
		case "RIGHT":
			if (spriteNum == 1) {
				image = right1;
			} else {
				image = right2;
			}
			break;
		default:
			image = down1;
			break;
		}

		return image;
	}
	
	public void drawHitbox(Graphics2D g2d) {
		if (keyHandler.showHitbox) {
			g2d.setColor(new Color(255, 0, 0, 128));
			g2d.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
			g2d.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		BufferedImage image = getCurrentSprite();
		g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
		drawHitbox(g2d);
	}
}
