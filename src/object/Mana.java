package object;

import java.io.IOException;
import javax.imageio.ImageIO;

import Entites.Entite;
import main.Ecran;

public class Mana extends Entite {
	
	Ecran ecran;
	int compteur = 0;
	
	public Mana(Ecran ecran) {
		
		super(ecran);
		this.ecran = ecran;
		
		nom = "Crystal de mana";
		typeEntite = ramasserType;
		valeur = 1;
		arriere = initialiser("/items/CrystalMana.png", ecran.tailleFinale, ecran.tailleFinale);
		image = initialiser("/items/ManaPlein.png", ecran.tailleFinale, ecran.tailleFinale);
		image1 = initialiser("/items/ManaVide.png", ecran.tailleFinale, ecran.tailleFinale);
		description = "[" + nom + "]\nPour lancer des sorts.";
	}
	
	public void utiliser(Entite entite, int i) {
		if (entite.mana < entite.maxMana) {
			entite.mana += valeur;
			ecran.interfaceJoueur.ajouterMessage("Vous gagner " + valeur + " mana !");
			ecran.jouerSE(2);
			ecran.obj[ecran.carteActuelle][i] = null;
		}
		else {
			if (this.compteur == 0) {
				ecran.interfaceJoueur.ajouterMessage("Votre mana est déjà au maximum.");
			}
			this.compteur++;
			if (this.compteur > 60) {
				this.compteur = 0;
			}
		}
	}
}
