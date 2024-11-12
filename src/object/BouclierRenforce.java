package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class BouclierRenforce extends Entite {
	
	public BouclierRenforce(Ecran ecran) {
		super(ecran);
		
		nom = "Bouclier en bois renforcé";
		typeEntite = bouclierType;
		arriere = initialiser("/items/BouclierRenforce.png", ecran.tailleFinale, ecran.tailleFinale);		
		defVal = 3;
		description = "[" + nom + "]\nDéjà mieux que le bois.\nDéfense = " + defVal;

	}
}
