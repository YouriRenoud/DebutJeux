package object;

import Entites.Entite;
import main.Ecran;

public class BouclierBasique extends Entite {

	public BouclierBasique(Ecran ecran) {
		super(ecran);
		nom = "Bouclier de base";
		typeEntite = bouclierType;
		arriere = initialiser("/items/BouclierBase.png", ecran.tailleFinale, ecran.tailleFinale);
		defVal = 1;
		description = "[" + nom + "]\nFait main en bois.\nDéfense = " + defVal;
		prix = 5;
	}
}
