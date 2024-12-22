package object;

import Entites.Entite;
import main.Ecran;

public class BouclierBasique extends Entite {

	public static final String objnom = "Bouclier de base";

	public BouclierBasique(Ecran ecran) {
		super(ecran);
		nom = objnom;
		typeEntite = bouclierType;
		arriere = initialiser("/items/BouclierBase.png", ecran.tailleFinale, ecran.tailleFinale);
		defVal = 1;
		description = "[" + nom + "]\nFait main en bois.\nDéfense = " + defVal;
		prix = 5;
		prixForge = 10;
		nbForgeReussi = 0;
	}
}
