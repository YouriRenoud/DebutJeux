package object;

import Entites.Entite;
import main.Ecran;

public class Poings extends Entite {

	public static final String objnom = "Poings";

	public Poings(Ecran ecran) {
		super(ecran);
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/poings.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 1;
		attArea.width = 20;
		attArea.height = 20;
		description = "[" + nom + "]\nQuand on a vraiment rien.\nAttaque = " + attVal;
		reculForce = 1;
		dureeAttFrame1 = 5;
		dureeAttFrame2 = 20;
	}
}
