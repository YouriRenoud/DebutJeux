package monstres;

import java.util.Random;

import Entites.Entite;
import main.Ecran;
import object.BouclierRenforce;
import object.Coeur;
import object.EpeeLourde;
import object.Mana;
import object.Pieces;
import object.Pierre;

public class Geant extends Entite {
	public Ecran ecran;

	public Geant (Ecran ecran, int carte) {
		super(ecran);
		this.ecran = ecran;
		
		nom = "Geant";
		typeEntite = monstreType;
		vitesseDefaut = 1;
		vitesse = vitesseDefaut;
		vieMax = 30*carte;
		vie = vieMax;
		attaquer = 2*carte;
		defendre = 10*carte;
		experience = 12*carte;
		magie = 10;

        projectile = new Pierre(ecran);
		
		aireCollision.x = 3;
		aireCollision.y = 7;
		aireCollision.width = 42;
		aireCollision.height = 41;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
		
		getImage();
	}
	
	public void getImage() {
		avant = initialiser("/Monsters/geantAvant.png", ecran.tailleFinale, ecran.tailleFinale);
		avant1 = initialiser("/Monsters/geantAvant1.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere = initialiser("/Monsters/geantArriere.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/Monsters/geantArriere1.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche = initialiser("/Monsters/geantGauche.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche1 = initialiser("/Monsters/geantGauche1.png", ecran.tailleFinale, ecran.tailleFinale);
		droite = initialiser("/Monsters/geantDroite.png", ecran.tailleFinale, ecran.tailleFinale);
		droite1 = initialiser("/Monsters/geantDroite1.png", ecran.tailleFinale, ecran.tailleFinale);

	}
	
	public void actions() {

		if (enChemin) {

			verifierChasse(ecran.joueur, 20, 50);

			chercherChemin(getColArrivee(ecran.joueur), getLignArrivee(ecran.joueur));

            verifierTirer(200, 20);


		}
		else {
			arreterChasse(ecran.joueur, 25, 100);

			getRandomDirection(150);
		}
	}
	
	public void verifierDrop() {
		int i = new Random().nextInt(100)+1;
		
		if (i < 50) {
			dropItem(new Coeur(ecran));
			dropItem(new Coeur(ecran));
			dropItem(new Coeur(ecran));	
		}
		if (i >= 50 && i < 80) {
			dropItem(new Pieces(ecran, 2));
		}
		if (i >= 80 && i < 99) {
			dropItem(new BouclierRenforce(ecran));
			dropItem(new Mana(ecran));
		}
		if (i == 99) {
			dropItem(new EpeeLourde(ecran));
		}
	}
	
	public void attaqueReaction() {
		attente = 0;
		//direction = ecran.joueur.direction;
		enChemin = true;
	}

}
