package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Locale;

import object.OBJ_Key;

public class UI {
	GamePanel gamePanel;
	Font arial_40, arial_80B;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message;
	int messageCounter;
	public boolean gameFinished = false;
	double playTime;
	
	public UI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		OBJ_Key key = new OBJ_Key(gamePanel);
		keyImage = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public int centerText(String text, Graphics2D g2d) {
	    int textLength = (int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
	    return (gamePanel.screenWidth / 2) - (textLength / 2);
	}
	
	public void drawGameOver(Graphics2D g2d) {
	    String text;
	    int x, y;

	    g2d.setFont(arial_40);
	    g2d.setColor(Color.WHITE);

	    text = "You found the Treasure!";
	    x = centerText(text, g2d);
	    y = (gamePanel.screenHeight / 2) - (gamePanel.tileSize * 3);
	    g2d.drawString(text, x, y);

	    text = "Your time is: " + String.format(Locale.US, "%.2f", playTime) + "!";
	    x = centerText(text, g2d);
	    y = (gamePanel.screenHeight / 2) + (gamePanel.tileSize * 4);
	    g2d.drawString(text, x, y);

	    g2d.setFont(arial_80B);
	    g2d.setColor(Color.YELLOW);
	    text = "Congratulations!";
	    x = centerText(text, g2d);
	    y = (gamePanel.screenHeight / 2) + (gamePanel.tileSize * 2);
	    g2d.drawString(text, x, y);

	    gamePanel.gameThread = null;
	}
	
	public void drawHUD(Graphics2D g2d) {
	    g2d.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
	    g2d.drawString("x " + gamePanel.player.keyCount, 74, 65);
	    playTime += 1.0 / 60;
	    g2d.drawString("Time: " + String.format(Locale.US, "%.2f", playTime), gamePanel.tileSize * 11, 65);
	}
	
	public void drawMessage(Graphics2D g2d) {
	    if (messageOn) {
	        g2d.setFont(g2d.getFont().deriveFont(30F));
	        g2d.drawString(message, gamePanel.tileSize / 2, gamePanel.tileSize * 5);
	        messageCounter++;

	        if (messageCounter > 120) {
	            messageCounter = 0;
	            messageOn = false;
	        }
	    }
	}

	public void drawFPS(Graphics2D g2d) {
	    if (gamePanel.keyHandler.showFPS == true) {
	        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 20F));
	        g2d.setColor(Color.YELLOW);
	        g2d.drawString("FPS: " + gamePanel.getCurrentFPS(), 10, gamePanel.screenHeight - 10);
	    }
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setFont(arial_40);
		g2d.setColor(Color.WHITE);
		
		if (gameFinished) {
			drawGameOver(g2d);
			
		} else {
			drawHUD(g2d);
			drawMessage(g2d);
			drawFPS(g2d);
		}
	}
}
