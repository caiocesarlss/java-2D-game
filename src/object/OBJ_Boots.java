package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Boots extends GameObject {

	public OBJ_Boots() {
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
