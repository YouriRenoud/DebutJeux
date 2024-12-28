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
		prixForge = 50;
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
