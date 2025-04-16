package object;

import Entites.Entite;
import main.Ecran;

public class EclairZeus extends Entite {

	public static final String objnom = "La foudre";

	public EclairZeus(Ecran ecran) {
		super(ecran);
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/eclairzeus.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 15;
		attArea.width = 25;
		attArea.height = 48;
		description = "[" + nom + "]\nLa colère du ciel\nAttaque = " + attVal + "\nMagie + 15";
		prix = 5000;
		prixForge = 5000;
		nbForgeReussi = 0;
		reculForce = 20;
		dureeAttFrame1 = 100;
		dureeAttFrame2 = 130;
		etourdi = true;
		etourdirDuree = 180;
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
