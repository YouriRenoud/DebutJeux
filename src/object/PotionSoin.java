package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class PotionSoin extends Entite {
	
	Ecran ecran;
	int compteur = 0;
	public static final String objnom = "Potion de soin";

	public PotionSoin(Ecran ecran) {
		super(ecran);
		
		this.ecran = ecran;
		nom = objnom;
		typeEntite = utilitaireType;
		valeur = 2;
		arriere = initialiser("/items/PotionSoin.png", ecran.tailleFinale, ecran.tailleFinale);
		description = "[" + nom + "]\nRécupère 1 coeur.";
		prix = 55;
		empillable = true;

		setDialogues();
	}

	public void setDialogues() {
		dialogue[0][0] = "Vous buvez une potion de soin,\nvous récupérez 1 coeur !";
		dialogue[1][0] = "Votre vie est déjà au maximum.";

	}

	
	public void utiliser(Entite entite, int index) {
		ecran.etatJeu = ecran.parler;
		if (entite.vie < entite.vieMax) {
			entite.vie += valeur;
			commencerDialogue(this, 0);
			ecran.jouerSE(2);
			ecran.obj[index] = null;
			if (this.possedes > 1) {
				this.possedes--;
			}
			else {
				ecran.joueur.inventaire.remove(index);
			}
		}
		else {
			if (this.compteur == 0) {
				commencerDialogue(this, 1);
			}
			this.compteur++;
			if (this.compteur > 60) {
				this.compteur = 0;
			}
		}
		
	}

	public void deposer(Entite e, int i) {
		deposerItem(e, i);
	}
}
