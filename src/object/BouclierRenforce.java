package object;

import Entites.Entite;
import main.Ecran;

public class BouclierRenforce extends Entite {
	
	public static final String objnom = "Bouclier en bois renforcé";

	public BouclierRenforce(Ecran ecran) {
		super(ecran);
		
		nom = objnom;
		typeEntite = bouclierType;
		arriere = initialiser("/items/BouclierRenforce.png", ecran.tailleFinale, ecran.tailleFinale);		
		defVal = 3;
		description = "[" + nom + "]\nDéjà mieux que le bois.\nDéfense = " + defVal;
		prix = 35;
		prixForge = 20;
		nbForgeReussi = 0;
	}
}
