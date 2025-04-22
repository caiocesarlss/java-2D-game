package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends GameObject {

	public OBJ_Key() {
		name = "Key";

		try { 
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));

		} catch (IOException e) { 
			e.printStackTrace(); 
		}

	}

	@Override
	public void loadObjectImage() {
		// TODO Auto-generated method stub
		
	}

}
