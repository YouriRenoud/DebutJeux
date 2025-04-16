package object;

import Entites.Entite;
import main.Ecran;

public class CapeInvisible extends Entite {
	
	public static final String objnom = "Cape d'invisiblité";

	public CapeInvisible(Ecran ecran) {
		super(ecran);
		
		nom = objnom;
		typeEntite = bouclierType;
		arriere = initialiser("/items/capeinvisible.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 10;
		description = "[" + nom + "]\nDeviens introuvable.\nAttaque = " + attVal;
		prix = 5000;
		prixForge = 5000;
		nbForgeReussi = 0;
        invisible = true;
        dureeInvisible = 120;
        resetInvisible = 180;
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
