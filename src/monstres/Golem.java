package monstres;

import java.awt.Rectangle;
import java.util.Random;

import Entites.Entite;
import main.Ecran;
import object.Cle;
import object.Coeur;
import object.Lance;
import object.Mana;
import object.Pieces;

public class Golem extends Entite {

	public Ecran ecran;
	public static final String monstreNom = "Golem";

	public Golem (Ecran ecran, int carte) {
		super(ecran);
		this.ecran = ecran;
		
		nom = monstreNom;
		typeEntite = monstreType;
		vitesseDefaut = 1;
		vitesse = vitesseDefaut;
		vieMax = 70*carte;
		vie = vieMax;
		attaquer = 1*carte;
        attVal = 10*carte;
		defendre = 10*carte;
		experience = 15*carte;
		magie = 0;
		reculForce = 6;
        dureeAttFrame1 = 45;
        dureeAttFrame2 = 80;
		
		aireCollision.x = 6;
		aireCollision.y = 10;
		aireCollision.width = 36;
		aireCollision.height = 34;
		aireSolideDefautX = aireCollision.x;
		aireSolideDefautY = aireCollision.y;
        attArea.width = 35;
        attArea.height = 35;
		
		getImage();
        getAttImage();
	}
	
	public void getImage() {
		avant = initialiser("/Monsters/GolemAvant.png", ecran.tailleFinale, ecran.tailleFinale);
		avant1 = initialiser("/Monsters/GolemAvant1.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere = initialiser("/Monsters/GolemArriere.png", ecran.tailleFinale, ecran.tailleFinale);
		arriere1 = initialiser("/Monsters/GolemArriere1.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche = initialiser("/Monsters/GolemGauche.png", ecran.tailleFinale, ecran.tailleFinale);
		gauche1 = initialiser("/Monsters/GolemGauche1.png", ecran.tailleFinale, ecran.tailleFinale);
		droite = initialiser("/Monsters/GolemDroite.png", ecran.tailleFinale, ecran.tailleFinale);
		droite1 = initialiser("/Monsters/GolemDroite1.png", ecran.tailleFinale, ecran.tailleFinale);

	}

    public void getAttImage() {
        attAvant = initialiser("/Monsters/GolemAttAvant.png", ecran.tailleFinale, ecran.tailleFinale*2);
        attArriere = initialiser("/Monsters/GolemAttArriere.png", ecran.tailleFinale, ecran.tailleFinale*2);
        attGauche = initialiser("/Monsters/GolemAttGauche.png", ecran.tailleFinale*2, ecran.tailleFinale);
        attDroite = initialiser("/Monsters/GolemAttDroite.png", ecran.tailleFinale*2, ecran.tailleFinale);
        attArriere1 = initialiser("/Monsters/GolemAttArriere1.png", ecran.tailleFinale, ecran.tailleFinale*2);
        attAvant1 = initialiser("/Monsters/GolemAttAvant1.png", ecran.tailleFinale, ecran.tailleFinale*2);
        attGauche1 = initialiser("/Monsters/GolemAttGauche1.png", ecran.tailleFinale*2, ecran.tailleFinale);
        attDroite1 = initialiser("/Monsters/GolemAttDroite1.png", ecran.tailleFinale*2, ecran.tailleFinale);
    }
	
	public void actions() {

		if (enChemin) {

			verifierChasse(ecran.joueur, 12, 80);
			chercherChemin(getColArrivee(ecran.joueur), getLignArrivee(ecran.joueur));

		}
		else {
			arreterChasse(ecran.joueur, 1, 10);

			getRandomDirection(170);
		}

        if (!attaque) {
            verifierAttaque( 30, ecran.tailleFinale*4, ecran.tailleFinale);
        }
	}
	
	public void verifierDrop() {
		int i = new Random().nextInt(100)+1;
		
		if (i < 50) {
			drop.add(new Mana(ecran));
            drop.add(new Mana(ecran));
            drop.add(new Mana(ecran));
		}
		if (i >= 50 && i < 80) {
			drop.add(new Pieces(ecran, 2));
            drop.add(new Pieces(ecran, 3));
			drop.add(new Pieces(ecran, 1));
		}
		if (i >= 80 && i < 99) {
			drop.add(new Cle(ecran));
            drop.add(new Pieces(ecran, 2));
		}
        dropItem();
	}
	
	public void attaqueReaction() {
		attente = 0;
		//direction = ecran.joueur.direction;
		enChemin = true;
	}

}

