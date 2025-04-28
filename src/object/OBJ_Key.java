package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Key extends GameObject {

	 public OBJ_Key(GamePanel gamePanel) {
	        super(gamePanel);
	        name = "Key";
	        loadObjectImage();
	 }

	@Override
	public void loadObjectImage() {
		try { 
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));

		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}

}
