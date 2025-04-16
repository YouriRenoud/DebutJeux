package object;

import Entites.Entite;
import main.Ecran;

public class OrbeMagique extends Entite {

	public static final String objnom = "L'Orbe de puissance'";
	public Ecran ecran;

	public OrbeMagique(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		nom = objnom;
		typeEntite = epeeType;
		arriere = initialiser("/items/orbe.png", ecran.tailleFinale, ecran.tailleFinale);
		attVal = 3;
        ecran.joueur.magie += 8;
		attArea.width = 30;
		attArea.height = 38;
		description = "[" + nom + "]\nDevenir plus puissant ?\nAttaque = " + attVal + "\nMagie + 16";
		prix = 2000;
		prixForge = 500;
		nbForgeReussi = 0;
		reculForce = 1;
		dureeAttFrame1 = 30;
		dureeAttFrame2 = 50;
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
			ecran.joueur.magie -= 16;
		}
	}
}
