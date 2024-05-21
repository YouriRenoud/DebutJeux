package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.Ecran;

public class Coffre extends JeuObject {
	
	Ecran ecran;

	public Coffre(Ecran ecran) {
		nom = "Coffre";
		this.ecran = ecran;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/CoffreFerme.png"));
			fct.miseAEchelle(image, ecran.tailleFinale, ecran.tailleFinale);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
	}
}
