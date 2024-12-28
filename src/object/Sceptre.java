package object;

import Entites.Entite;
import main.Ecran;

public class Sceptre extends Entite {

	public static final String objnom = "Le sceptre ultime";

	public Sceptre(Ecran ecran) {
		super(ecran);
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/sceptre.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 5;
        ecran.joueur.magie += 20;
		attArea.width = 30;
		attArea.height = 48;
		description = "[" + nom + "]\nLa plus grande force magique.\nAttaque = " + attVal;
		prix = 20000;
		prixForge = 800;
		nbForgeReussi = 0;
		reculForce = 5;
		dureeAttFrame1 = 20;
		dureeAttFrame2 = 40;
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
