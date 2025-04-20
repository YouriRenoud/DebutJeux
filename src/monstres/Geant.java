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
	public static final String monstreNom = "Geant";

	public Geant (Ecran ecran, int carte) {
		super(ecran);
		this.ecran = ecran;
		
		nom = monstreNom;
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

			verifierChasse(ecran.joueur, 15, 40);

			chercherChemin(getColArrivee(ecran.joueur), getLignArrivee(ecran.joueur));

            verifierTirer(200, 20);


		}
		else {
			arreterChasse(ecran.joueur, 20, 100);

			getRandomDirection(150);
		}
	}
	
	public void verifierDrop() {
		int i = new Random().nextInt(100)+1;
		
		if (i < 50) {
			drop.add(new Coeur(ecran));
			drop.add(new Coeur(ecran));
			drop.add(new Coeur(ecran));	
		}
		if (i >= 50 && i < 80) {
			drop.add(new Pieces(ecran, 2));
		}
		if (i >= 80 && i < 99) {
			drop.add(new BouclierRenforce(ecran));
			drop.add(new Mana(ecran));
		}
		if (i == 99) {
			drop.add(new EpeeLourde(ecran));
		}
		dropItem();
	}
	
	public void attaqueReaction() {
		attente = 0;
		//direction = ecran.joueur.direction;
		enChemin = true;
	}

}
