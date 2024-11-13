package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class HacheEnPierre extends Entite {
	
	public HacheEnPierre(Ecran ecran) {
		super(ecran);
		
		nom = "Hache en pierre";
		typeEntite = hacheType;
		arriere = initialiser("/items/HacheEnPierre.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 5;
		attArea.width = 35;
		attArea.height = 22;
		description = "[" + nom + "]\nArme de barbares,\nPeut couper des arbres.\nDégats = " + attVal;
		prix = 50;
	}
}
