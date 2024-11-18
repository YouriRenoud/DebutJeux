package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class Pioche extends Entite {
	
	public static final String objnom = "Pioche";

	public Pioche(Ecran ecran) {
		super(ecran);
		
		nom = objnom;
		typeEntite = piocheType;
		arriere = initialiser("/items/pioche.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 2;
		attArea.width = 35;
		attArea.height = 35;
		description = "[" + nom + "]\nArme de barbares,\nPeut casser certains murs.\nDégats = " + attVal;
		prix = 500;
		reculForce = 3;
		dureeAttFrame1 = 10;
		dureeAttFrame2 = 20;
	}
}
