package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends GameObject {
	
	public OBJ_Chest() {
		name = "Door";
		loadObjectImage();
		
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
