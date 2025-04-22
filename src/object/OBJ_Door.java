package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends GameObject {

	public OBJ_Door() {
		name = "Door";
		loadObjectImage();
		collision = true;
	}

	@Override
	public void loadObjectImage() {
		try { 
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));

		} catch (IOException e) { 
			e.printStackTrace(); 
		}
	}
}
