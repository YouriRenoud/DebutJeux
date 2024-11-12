package object;

import Entites.Entite;
import main.Ecran;

public class EpeeNormale extends Entite {

	public EpeeNormale(Ecran ecran) {
		super(ecran);
		nom = "Epee de base";
		typeEntite = epeeType;
		arriere = initialiser("/items/EpeeBase.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 1;
		attArea.width = 30;
		attArea.height = 30;
		description = "[" + nom + "]\nUne vieille épée.\nAttaque = " + attVal;
	}
}
