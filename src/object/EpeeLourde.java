package object;

import Entites.Entite;
import main.Ecran;

public class EpeeLourde extends Entite {

	public static final String objnom = "Epée en fer lourd";

	public EpeeLourde(Ecran ecran) {
		super(ecran);
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/epeeLourde.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 7;
		attArea.width = 44;
		attArea.height = 44;
		description = "[" + nom + "]\nDe gros dégats implique\nune faible vitesse.\nAttaque = " + attVal;
		prix = 180;
		prixForge = 50;
		nbForgeReussi = 0;
		reculForce = 12;
		dureeAttFrame1 = 30;
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
