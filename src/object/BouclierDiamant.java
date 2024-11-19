package object;

import Entites.Entite;
import main.Ecran;

public class BouclierDiamant extends Entite {
	
	public static final String objnom = "Bouclier en diamant";

	public BouclierDiamant(Ecran ecran) {
		super(ecran);
		
		nom = objnom;
		typeEntite = bouclierType;
		arriere = initialiser("/items/bouclierDiamant.png", ecran.tailleFinale, ecran.tailleFinale);		
		defVal = 8;
		description = "[" + nom + "]\nLa protection ultime.\nDéfense = " + defVal;
		prix = 200;

	}
}
