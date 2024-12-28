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
