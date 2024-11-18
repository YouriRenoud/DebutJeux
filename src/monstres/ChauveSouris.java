package monstres;

import java.awt.Rectangle;
import java.util.Random;

import Entites.Entite;
import main.Ecran;
import object.Coeur;
import object.Mana;
import object.Pieces;

public class ChauveSouris extends Entite {
	public Ecran ecran;
	public ChauveSouris (Ecran ecran) {
		super(ecran);
		this.ecran = ecran;
		
		nom = "Slime";
		typeEntite = monstreType;
		vitesseDefaut = 5;
		vitesse = vitesseDefaut;
		vieMax = 28;
		vie = vieMax;
		attaquer = 12;
		defendre = 0;
		experience = 9;
		magie = 0;
		
		aireCollision.x = 6;
		aireCollision.y = 14;
		aireCollision.width = 36;
		aireCollision.height = 20;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
		
		getImage();
	}
	
	public void getImage() {
		avant = initialiser("/Monsters/chauveSouris.png", ecran.tailleFinale, ecran.tailleFinale);
		avant1 = initialiser("/Monsters/chauveSouris1.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere = initialiser("/Monsters/chauveSouris.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/Monsters/chauveSouris1.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche = initialiser("/Monsters/chauveSouris.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche1 = initialiser("/Monsters/chauveSouris1.png", ecran.tailleFinale, ecran.tailleFinale);
		droite = initialiser("/Monsters/chauveSouris.png", ecran.tailleFinale, ecran.tailleFinale);
		droite1 = initialiser("/Monsters/chauveSouris1.png", ecran.tailleFinale, ecran.tailleFinale);

	}
	
	public void actions() {

        enChemin = false;
		if (enChemin) {

			verifierChasse(ecran.joueur, 10, 100);

			chercherChemin(getColArrivee(ecran.joueur), getLignArrivee(ecran.joueur));

		}
		else {
			//arreterChasse(ecran.joueur, 10, 100);

			getRandomDirection(70);
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
