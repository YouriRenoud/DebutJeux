package object;

import Entites.Entite;
import main.Ecran;

public class Gourdin extends Entite {

	public static final String objnom = "Une arme d'orc";

	public Gourdin(Ecran ecran) {
		super(ecran);
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/gourdin.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 11;
		attArea.width = 30;
		attArea.height = 30;
		description = "[" + nom + "]\nPour la destruction\nAttaque = " + attVal;
		prix = 220;
		prixForge = 60;
		nbForgeReussi = 0;
		reculForce = 12;
		dureeAttFrame1 = 40;
		dureeAttFrame2 = 70;

		setDialogues();
	}

	public void setDialogues() {
		dialogue[0][0] = "Vous ne pouvez pas déposer votre\néquipement actuel.";
	}

	public void deposer(Entite e, int i) {
		if (e.armeActuelle == this) {
			commencerDialogue(this, 0);
		}
		else {
			deposerItem(e, i);
		}
	}
}
