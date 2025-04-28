package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Chest extends GameObject {
	
	public OBJ_Chest(GamePanel gamePanel) {
		super(gamePanel);
		name = "Chest";
		loadObjectImage();
		collision = true;
	}

	@Override
	public void loadObjectImage() {
		try { 
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));

		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
}
