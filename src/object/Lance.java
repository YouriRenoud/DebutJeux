package object;

import Entites.Entite;
import main.Ecran;

public class Lance extends Entite {

	public static final String objnom = "Lance en or";

	public Lance(Ecran ecran) {
		super(ecran);
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/lance.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 5;
		attArea.width = 48;
		attArea.height = 30;
		description = "[" + nom + "]\nPour garder l'ennemi\nà distance\nAttaque = " + attVal;
		prix = 150;
		prixForge = 45;
		nbForgeReussi = 0;
		reculForce = 3;
		dureeAttFrame1 = 10;
		dureeAttFrame2 = 30;
	}
}
