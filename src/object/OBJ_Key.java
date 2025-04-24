package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends GameObject {

	public OBJ_Key() {
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
