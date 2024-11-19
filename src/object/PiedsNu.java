package object;

import Entites.Entite;
import main.Ecran;

public class PiedsNu extends Entite {
	
	public static final String objnom = "Pieds nus";

	public PiedsNu(Ecran ecran) {
		super(ecran);
		
		nom = objnom;
		typeEntite = chaussureType;
		arriere = initialiser("/items/piedsNu.png", ecran.tailleFinale, ecran.tailleFinale);
		vitesse = 0;
		description = "[" + nom + "]\nPas vraiment rapide.\nVitesse + 1";
	}
}
