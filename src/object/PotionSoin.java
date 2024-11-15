package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class PotionSoin extends Entite {
	
	Ecran ecran;
	int compteur = 0;
	public PotionSoin(Ecran ecran) {
		super(ecran);
		
		this.ecran = ecran;
		nom = "Potion de soin";
		typeEntite = utilitaireType;
		valeur = 2;
		arriere = initialiser("/items/PotionSoin.png", ecran.tailleFinale, ecran.tailleFinale);
		description = "[" + nom + "]\nRécupère 1 coeur.";
		prix = 55;
		empillable = true;
	}
	
	public void utiliser(Entite entite, int index) {
		ecran.etatJeu = ecran.parler;
		if (entite.vie < entite.vieMax) {
			entite.vie += valeur;
			ecran.interfaceJoueur.dialogueCourant = "Vous buvez une potion de soin,\nvous récupérez 1 coeur !";
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
				ecran.interfaceJoueur.dialogueCourant = "Votre vie est déjà au maximum.";

			}
			this.compteur++;
			if (this.compteur > 60) {
				this.compteur = 0;
			}
		}
		
	}
}
