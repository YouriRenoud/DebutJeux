package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class HacheEnPierre extends Entite {
	
	public static final String objnom = "Hache en pierre";

	public HacheEnPierre(Ecran ecran) {
		super(ecran);
		
		nom = objnom;
		typeEntite = hacheType;
		arriere = initialiser("/items/HacheEnPierre.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 5;
		attArea.width = 35;
		attArea.height = 22;
		description = "[" + nom + "]\nArme de barbares,\nPeut couper des arbres.\nDégats = " + attVal;
		prix = 50;
		reculForce = 3;
		dureeAttFrame1 = 10;
		dureeAttFrame2 = 30;
	}
}
