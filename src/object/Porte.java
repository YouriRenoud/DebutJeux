package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.Ecran;

public class Porte extends JeuObject {
	
	public Ecran ecran;

	public Porte(Ecran ecran) {
		nom = "Porte";
		this.ecran = ecran;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/PorteFermee.png"));
			fct.miseAEchelle(image, ecran.tailleFinale, ecran.tailleFinale);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
	}
}