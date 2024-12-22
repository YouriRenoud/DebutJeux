package object;

import Entites.Entite;
import main.Ecran;

public class BouclierFer extends Entite {
	
	public static final String objnom = "Bouclier en fer";

	public BouclierFer(Ecran ecran) {
		super(ecran);
		
		nom = objnom;
		typeEntite = bouclierType;
		arriere = initialiser("/items/bouclierFer.png", ecran.tailleFinale, ecran.tailleFinale);		
		defVal = 5;
		description = "[" + nom + "]\nUne vraie défense solide.\nDéfense = " + defVal;
		prix = 75;
		prixForge = 30;
		nbForgeReussi = 0;
	}
}
