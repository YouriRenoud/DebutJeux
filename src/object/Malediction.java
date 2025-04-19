package object;

import Entites.Entite;
import main.Ecran;

public class Malediction extends Entite {

	public static final String objnom = "Malediction";

	public Malediction(Ecran ecran) {
		super(ecran);
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/malediction.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 6;
        malediction = true;
		attArea.width = 30;
		attArea.height = 48;
		description = "[" + nom + "]\nDétruire l'adversaire ? Oui.\nAttaque = " + attVal;
		prix = 2000;
		prixForge = 3000;
		nbForgeReussi = 0;
		reculForce = 3;
		dureeAttFrame1 = 25;
		dureeAttFrame2 = 45;
	}

    public void setDialogues() {

        dialogue[0][0] = "Vous avez trouvé le sceptre de mage !";
        dialogue[0][1] = "La meilleure arme pour un magicien !";
        dialogue[0][2] = "Votre puissance magique sera grandement augmentée !";
        dialogue[0][3] = "Fais en bon usage !";

		dialogue[1][0] = "Vous ne pouvez pas déposer votre\néquipement actuel.";
    }

	public void deposer(Entite e, int i) {
		if (e.armeActuelle == this) {
			commencerDialogue(this, 1);
		}
		else {
			deposerItem(e, i);
		}
	}
}
