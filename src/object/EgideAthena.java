package object;

import Entites.Entite;
import main.Ecran;

public class EgideAthena extends Entite {
	
	public static final String objnom = "L'Egide d'Athena";

	public EgideAthena(Ecran ecran) {
		super(ecran);
		
		nom = objnom;
		typeEntite = bouclierType;
		arriere = initialiser("/items/egideAthena.png", ecran.tailleFinale, ecran.tailleFinale);		
		defVal = 15;
		description = "[" + nom + "]\nImpénétrable.\nDéfense = " + defVal;
		prix = 2000;
		prixForge = 500;
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
