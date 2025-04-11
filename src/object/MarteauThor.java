package object;

import Entites.Entite;
import main.Ecran;

public class MarteauThor extends Entite {

	public static final String objnom = "Marteau de Thor";

	public MarteauThor(Ecran ecran) {
		super(ecran);
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/MarteauThor.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 22;
		attArea.width = 48;
		attArea.height = 48;
		description = "[" + nom + "]\nLa puissance incarnée\nAttaque = " + attVal;
		prix = 3000;
		prixForge = 6000;
		nbForgeReussi = 0;
		reculForce = 18;
		dureeAttFrame1 = 60;
		dureeAttFrame2 = 80;

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
