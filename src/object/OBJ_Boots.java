package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Boots extends GameObject {

	public OBJ_Boots(GamePanel gamePanel) {
        super(gamePanel);
        name = "Boots";
		loadObjectImage();
		
	}

	@Override
	public void loadObjectImage() {
		try { 
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));

		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}

}
