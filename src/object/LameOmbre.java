package object;

import Entites.Entite;
import main.Ecran;

public class LameOmbre extends Entite {

	public static final String objnom = "Lame de l'ombre";

	public LameOmbre(Ecran ecran) {
		super(ecran);
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/lameOmbre.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 7;
		attArea.width = 30;
		attArea.height = 30;
		description = "[" + nom + "]\nL'empoisonneuse\nAttaque = " + attVal +"\n(empoisonne)";
		prix = 2000;
		prixForge = 500;
		nbForgeReussi = 0;
		reculForce = 1;
		dureeAttFrame1 = 5;
		dureeAttFrame2 = 20;
        poison = true;

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
