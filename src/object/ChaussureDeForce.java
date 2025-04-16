package object;

import Entites.Entite;
import main.Ecran;

public class ChaussureDeForce extends Entite {
	
	public static final String objnom = "La chaussure qui impose";
	public Ecran ecran;

	public ChaussureDeForce(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		nom = objnom;
		typeEntite = chaussureType;
		arriere = initialiser("/items/chaussuredeforce.png", ecran.tailleFinale, ecran.tailleFinale);
		prix = 3000;
		prixForge = 6000;
        ecran.joueur.ralentissement = true;
		vitesse = 2;
        rayonLumiere = 4;
		description = "[" + nom + "]\nRalentissement global.\nVitesse + " + vitesse;

		setDialogues();
	}

	public void setDialogues() {
		dialogue[0][0] = "Vous ne pouvez pas déposer votre\néquipement actuel.";
	}

	public void deposer(Entite e, int i) {
		if (e.chaussuresActuelles == this) {
			commencerDialogue(this, 0);
		}
		else {
			deposerItem(e, i);
            ecran.joueur.ralentissement = false;
		}
	}
}
