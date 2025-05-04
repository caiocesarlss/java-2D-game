package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3; 
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	// FRAMES PER SECOND
	int FPS = 60;
	long timer = 0;
	int frameCount = 0;
	int currentFPS = 0;
	
	TileManager tileManager = new TileManager(this);
	KeyHandler keyHandler = new KeyHandler();
	Sound music = new Sound();
	Sound soundEffect = new Sound();
	Thread gameThread;
	public UI ui = new UI(this);
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public ObjectManager objectManager = new ObjectManager(this);
	public Player player = new Player(this, keyHandler);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		objectManager.setObject();
		playMusic(0);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void trackFPS(long elapsedTime) {
		timer += elapsedTime;

		if (timer >= 1_000_000_000) {
			currentFPS = frameCount;
			//printFPS(frameCount);
			frameCount = 0;
			timer = 0;
		}
	}

	public void printFPS(int fps) {
		System.out.println("FPS: " + fps);
	}
	
	public int getCurrentFPS() {
		return currentFPS;
	}
	
	@Override
//	public void run() {
//		double drawInterval = 1000000000/FPS;
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while(gameThread != null) {
//			update();
//			repaint();
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000; // converts to milliseconds
//				
//				if (remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}

	public void run() {
		double drawInterval = 1_000_000_000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long elapsedTime;
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			elapsedTime = currentTime - lastTime;
			delta += elapsedTime / drawInterval;
			lastTime = currentTime;
			trackFPS(elapsedTime);
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				frameCount++;
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void drawTiles(Graphics2D g2d) {
		tileManager.draw(g2d);
	}
	
	public void drawObjects(Graphics2D g2d) {
		for (int i = 0; i < objectManager.object.length; i++) {
			if (objectManager.object[i] != null) {
				objectManager.draw(g2d, i);
			}
		}
	}
	
	public void drawPlayer(Graphics2D g2d) {
		player.draw(g2d);
	}
	
	public void drawUI(Graphics2D g2d) {
		ui.draw(g2d);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		drawTiles(g2d);
		drawObjects(g2d);
		drawPlayer(g2d);
		drawUI(g2d);
		g2d.dispose();
	}
	
	public void playMusic(int index) {
		music.setFile(index);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}

	public void playSoundEffect(int index) {
		soundEffect.setFile(index);
		soundEffect.play();
	}
}
