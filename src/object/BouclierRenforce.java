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

		setDialogues();
	}

	public void setDialogues() {
		dialogue[0][0] = "Vous ne pouvez pas déposer votre\néquipement actuel.";
	}

	public void deposer(Entite e, int i) {
		if (e.bouclierActuel == this) {
			commencerDialogue(this, 0);
		}
		else {
			deposerItem(e, i);
		}
	}
}
