package object;

import Entites.Entite;
import main.Ecran;

public class Coeur extends Entite {
	
	Ecran ecran;
	int compteur = 0;
	
	public Coeur(Ecran ecran) {
		
		super(ecran);
		this.ecran = ecran;
		typeEntite = ramasserType;
		nom = "Coeur";
		valeur = 2;
		arriere = initialiser("/items/Coeur.png", ecran.tailleFinale, ecran.tailleFinale);
		image = initialiser("/items/Coeur.png", ecran.tailleFinale, ecran.tailleFinale);
		image1 = initialiser("/items/CoeurMoitie.png", ecran.tailleFinale, ecran.tailleFinale);
		image2 = initialiser("/items/CoeurVide.png", ecran.tailleFinale, ecran.tailleFinale);	
	}
	
	public void utiliser(Entite entite, int i) {
		if (entite.vie < entite.vieMax) {
			entite.vie += valeur;
			ecran.interfaceJoueur.ajouterMessage("Vous gagner " + valeur + " vies !");
			ecran.jouerSE(2);
			ecran.obj[ecran.carteActuelle][i] = null;
		}
		else {
			if (this.compteur == 0) {
				ecran.interfaceJoueur.ajouterMessage("Votre vie est déjà au maximum.");

			}
			this.compteur++;
			if (this.compteur > 60) {
				this.compteur = 0;
			}
		}
	}

}
