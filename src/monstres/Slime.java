package monstres;

import java.awt.Rectangle;
import java.util.Random;

import Entites.Entite;
import main.Ecran;
import object.Coeur;
import object.Mana;
import object.Pieces;
import object.Pierre;

public class Slime extends Entite {
	public Ecran ecran;
	public Slime (Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		
		nom = "Slime";
		typeEntite = monstreType;
		vitesseDefaut = 1;
		vitesse = vitesseDefaut;
		vieMax = 20;
		vie = vieMax;
		attaquer = 3;
		defendre = 0;
		experience = 2;
		magie = 8;
		projectile = new Pierre(ecran);
		
		aireCollision.x = 3;
		aireCollision.y = 10;
		aireCollision.width = 42;
		aireCollision.height = 20;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
		
		getImage();
	}
	
	public void getImage() {
		avant = initialiser("/Monsters/Slime1.png", ecran.tailleFinale, ecran.tailleFinale);
		avant1 = initialiser("/Monsters/Slime2.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere = initialiser("/Monsters/Slime1.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/Monsters/Slime2.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche = initialiser("/Monsters/Slime1.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche1 = initialiser("/Monsters/Slime2.png", ecran.tailleFinale, ecran.tailleFinale);
		droite = initialiser("/Monsters/Slime1.png", ecran.tailleFinale, ecran.tailleFinale);
		droite1 = initialiser("/Monsters/Slime2.png", ecran.tailleFinale, ecran.tailleFinale);

	}
	
	public void actions() {

		if (enChemin) {

			verifierChasse(ecran.joueur, 10, 100);

			chercherChemin(getColArrivee(ecran.joueur), getLignArrivee(ecran.joueur));

			verifierTirer(200, 20);
		}
		else {
			arreterChasse(ecran.joueur, 10, 100);

			getRandomDirection();
		}
	}
	
	public void verifierDrop() {
		int i = new Random().nextInt(100)+1;
		
		if (i < 50) {
			dropItem(new Pieces(ecran, 1));
		}
		if (i >= 50 && i < 80) {
			dropItem(new Pieces(ecran, 2));
			dropItem(new Coeur(ecran));
		}
		if (i >= 80 && i < 99) {
			dropItem(new Pieces(ecran, 2));
			dropItem(new Mana(ecran));
		}
		if (i == 99) {
			dropItem(new Pieces(ecran, 3));
		}
	}
	
	public void attaqueReaction() {
		attente = 0;
		//direction = ecran.joueur.direction;
		enChemin = true;
	}

}
