package object;

import Entites.Entite;
import main.Ecran;

public class ChaussuresAttaque extends Entite {
	
	public static final String objnom = "La furtive";
	public Ecran ecran;

	public ChaussuresAttaque(Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		nom = objnom;
		typeEntite = chaussureType;
		arriere = initialiser("/items/chaussureattaque.png", ecran.tailleFinale, ecran.tailleFinale);
		prix = 3000;
		prixForge = 6000;
		vitesse = 5;
		ecran.joueur.attaquer += 5;
		description = "[" + nom + "]\nChausson de l'espion.\nVitesse + " + vitesse
														+"\nAttaque + 5";

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
			ecran.joueur.attaquer -= 5;
		}
	}
}
