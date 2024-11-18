package object;

import Entites.Entite;
import main.Ecran;

public class EpeeNormale extends Entite {

	public static final String objnom = "Epée de base";

	public EpeeNormale(Ecran ecran) {
		super(ecran);
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/EpeeBase.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 1;
		attArea.width = 30;
		attArea.height = 30;
		description = "[" + nom + "]\nUne vieille épée.\nAttaque = " + attVal;
		prix = 5;
		reculForce = 7;
		dureeAttFrame1 = 5;
		dureeAttFrame2 = 25;
	}
}
